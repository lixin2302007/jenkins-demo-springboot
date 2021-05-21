package com.elane.learning.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {
  //当前在线连接数
  private static int onlineCount = 0;
  //存放每个客户端对应的MyWebSocket对象
  private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

  private Session session;
  //接收sid
  private String sid;

  /**
   * 连接建立成功调用的方法
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("sid") String sid) {
    this.session = session;
    this.sid = sid;
    webSocketSet.add(this);
    addOnlineCount();

    try {
      sendMessage("conn_success");
      log.info("有新窗口开始监听：" + sid + ", 当前在线人数为：" + onlineCount);
    } catch (IOException e) {
      log.error("websocket IO Exception");
    }
  }

  /**
   * 收到客户端消息后调用的方法
   * @param message
   * @param session
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("收到来自窗口" + sid + "的消息：" + message);

  }

  public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException{
    log.info("推送消息到窗口：" + sid + ",推送内容：" + message);
    for (WebSocketServer item : webSocketSet) {
      try {
        // 为null则全部推送
        if (sid == null) {
          item.sendMessage(message);
        } else if (item.sid.equals(sid)) {
          item.sendMessage(message);
        }
      } catch (IOException e) {
        continue;
      }

    }
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose() {
    webSocketSet.remove(this);
    subOnlineCount();
    log.info("释放的sid为：" + sid);
  }

  public void sendMessage(String message) throws IOException {
    this.session.getBasicRemote().sendText(message);
  }

  public static synchronized int getOnlineCount() {
    return WebSocketServer.onlineCount;
  }

  public static synchronized void addOnlineCount() {
    WebSocketServer.onlineCount++;
  }

  public static synchronized void subOnlineCount() {
    WebSocketServer.onlineCount--;
  }
}

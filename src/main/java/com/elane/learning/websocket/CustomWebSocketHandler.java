//package com.elane.learning.websocket;
//
//import com.elane.learning.security.CustomUserDetails;
//import java.net.URI;
//import java.security.InvalidParameterException;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentMap;
//import javax.websocket.RemoteEndpoint;
//import javax.websocket.SendHandler;
//import javax.websocket.SendResult;
//import javax.websocket.Session;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.adapter.NativeWebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Component
//public class CustomWebSocketHandler extends TextWebSocketHandler {
//
//  private static final String WS_PLUGIN_PREFIX = "/api/ws/plugins/";
//
////  private static final ConcurrentMap<String, >
//  /**
//   * 在 socket 连接成功后被触发，同原生注解里的 @OnOpen 功能
//   * @param session
//   * @throws Exception
//   */
//  @Override
//  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    super.afterConnectionEstablished(session);
//  }
//
//  /**
//   * 在客户端发送信息时触发，同原生注解里的 @OnMessage 功能
//   * @param session
//   * @param message
//   * @throws Exception
//   */
//  @Override
//  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//    super.handleTextMessage(session, message);
//  }
//
//  /**
//   * 在socket 连接异常时被触发
//   * @param session
//   * @param exception
//   * @throws Exception
//   */
//  @Override
//  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//    super.handleTransportError(session, exception);
//  }
//
//  /**
//   * 在 socket 连接关闭后被触发，同原生注解里的 @OnClose 功能
//   * @param session
//   * @param status
//   * @throws Exception
//   */
//  @Override
//  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    super.afterConnectionClosed(session, status);
//  }
//
//  private class SessionMetaData implements SendHandler {
//
//    private final WebSocketSession session;
//    private final RemoteEndpoint.Async asyncRemote;
//    private final CustomWebSocketSessionRef sessionRef;
//
//    SessionMetaData(WebSocketSession session, CustomWebSocketSessionRef sessionRef) {
//      super();
//      this.session = session;
//      Session nativeSession = ((NativeWebSocketSession)session).getNativeSession(Session.class);
//      this.asyncRemote = nativeSession.getAsyncRemote();
//      this.sessionRef = sessionRef;
//    }
//
//    @Override
//    public void onResult(SendResult sendResult) {
//
//    }
//  }
//
//  private CustomWebSocketSessionRef toRef(WebSocketSession session) {
//    URI sessionUri = session.getUri();
//    String path = sessionUri.getPath();
//    // 判断请求url是否是指定地址
//    path = path.substring(WS_PLUGIN_PREFIX.length());
//    if (path.length() == 0) {
//      throw new IllegalArgumentException("URL should contain plugin token !");
//    }
//    String[] paths = path.split("/");
//    String serviceToken = paths[0];
//    if (!"telemetry".equalsIgnoreCase(serviceToken)) {
//      throw new InvalidParameterException("Can't find plugin with specified Token !");
//    } else {
//      CustomUserDetails currentUser = (CustomUserDetails) ((Authentication)session.getPrincipal()).getPrincipal();
//      return new CustomWebSocketSessionRef(UUID.randomUUID().toString(), currentUser, session.getLocalAddress(), session.getRemoteAddress());
//    }
//  }
//}

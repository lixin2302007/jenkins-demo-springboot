//package com.elane.learning.websocket;
//
//import com.elane.learning.security.CustomUserDetails;
//import java.net.InetSocketAddress;
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicInteger;
//import lombok.Getter;
//
///**
// * 封装web socket session与用户关联的对象
// */
//public class CustomWebSocketSessionRef {
//
//  @Getter
//  private final String sessionId;
//  @Getter
//  private final CustomUserDetails details;
//  @Getter
//  private final InetSocketAddress localAddress;
//  @Getter
//  private final InetSocketAddress remoteAddress;
//  @Getter
//  private final AtomicInteger sessionSubIdSeq;
//
//
//  public CustomWebSocketSessionRef(String sessionId, CustomUserDetails details, InetSocketAddress localAddress, InetSocketAddress remoteAddress) {
//    this.sessionId = sessionId;
//    this.details = details;
//    this.localAddress = localAddress;
//    this.remoteAddress = remoteAddress;
//    sessionSubIdSeq = new AtomicInteger();
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    CustomWebSocketSessionRef that = (CustomWebSocketSessionRef) o;
//    return Objects.equals(sessionId, that.sessionId);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(sessionId);
//  }
//}

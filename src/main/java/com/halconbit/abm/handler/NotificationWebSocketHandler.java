package com.halconbit.abm.handler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
//@WebSocketHandler
public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Logic after establishing the WebSocket connection
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Logic to handle received messages in the WebSocket
    }
}
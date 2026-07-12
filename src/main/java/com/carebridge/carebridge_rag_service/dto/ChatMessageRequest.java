package com.carebridge.carebridge_rag_service.dto;

import lombok.Data;

@Data
public class ChatMessageRequest {
     private String role;      // user or assistant

    private String content;
}

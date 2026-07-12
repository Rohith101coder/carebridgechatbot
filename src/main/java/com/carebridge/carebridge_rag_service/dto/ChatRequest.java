package com.carebridge.carebridge_rag_service.dto;

import java.util.List;

// import com.carebridge.carebridge_rag_service.dto.openrouter.ChatMessage;

import lombok.Data;

@Data
public class ChatRequest {
    
    private String message;
     private List<ChatMessageRequest> history;
}

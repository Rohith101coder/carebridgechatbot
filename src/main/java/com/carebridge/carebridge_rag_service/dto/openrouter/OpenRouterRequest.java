package com.carebridge.carebridge_rag_service.dto.openrouter;

import java.util.List;

public record OpenRouterRequest(

        String model,

        List<ChatMessage> messages


) {}

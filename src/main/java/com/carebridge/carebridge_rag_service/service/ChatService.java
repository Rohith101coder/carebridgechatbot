package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_rag_service.dto.ChatRequest;
import com.carebridge.carebridge_rag_service.util.PromptBuilder;

import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
     private final RetrievalService retrievalService;
    private final OpenRouterService openRouterService;
    private final PromptBuilder promptBuilder;

    public String chat(ChatRequest  request) {

        List<String> contexts =
                retrievalService.retrieve(request.getMessage());

        String prompt =
                promptBuilder.build(request.getMessage(),request.getHistory(),contexts);

        return openRouterService.chat(prompt);
    }
}

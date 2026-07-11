package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_rag_service.util.PromptBuilder;

import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
     private final RetrievalService retrievalService;
    private final OpenRouterService openRouterService;
    private final PromptBuilder promptBuilder;

    public String chat(String question) {

        List<String> contexts =
                retrievalService.retrieve(question);

        String prompt =
                promptBuilder.build(question, contexts);

        return openRouterService.chat(prompt);
    }
}

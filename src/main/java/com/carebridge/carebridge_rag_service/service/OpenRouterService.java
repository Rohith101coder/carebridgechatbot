package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.carebridge.carebridge_rag_service.dto.openrouter.ChatMessage;
import com.carebridge.carebridge_rag_service.dto.openrouter.OpenRouterRequest;
import com.carebridge.carebridge_rag_service.dto.openrouter.OpenRouterResponse;
// import com.carebridge.carebridge_rag_service.dto.openrouter.stream.StreamResponse;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import reactor.core.publisher.Flux;

// import lombok.RequiredArgsConstructor;
// import lombok.Value;

@Service
public class OpenRouterService {

    private final WebClient openRouterClient;

    public OpenRouterService(@Qualifier("openRouterClient") WebClient openRouterClient) {
        this.openRouterClient = openRouterClient;
    }

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${ai.model}")
    private String model;

    public String chat(String prompt) {
        
        // System.out.println("apikey:"+apiKey);

        OpenRouterRequest request = new OpenRouterRequest(

                model,

                List.of(
                        new ChatMessage("user", prompt)
                )
        );

        OpenRouterResponse response = openRouterClient.post()

                .uri("/chat/completions")

                .header("Authorization", "Bearer " + apiKey)

                .header("Content-Type", "application/json")

                .bodyValue(request)

                .retrieve()

                .bodyToMono(OpenRouterResponse.class)

                .block();

        return response
                .choices()
                .getFirst()
                .message()
                .content();
    }
}
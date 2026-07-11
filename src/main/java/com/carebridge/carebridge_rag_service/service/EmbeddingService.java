package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.carebridge.carebridge_rag_service.dto.embedding.EmbeddingRequest;
import com.carebridge.carebridge_rag_service.dto.embedding.EmbeddingResponse;

// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class EmbeddingService {

         private final WebClient openRouterClient;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${embedding.model}")
    private String embeddingModel;

    public EmbeddingService(@Qualifier("openRouterClient") WebClient openRouterClient){
        this.openRouterClient = openRouterClient;
    }

    public List<Float> generateEmbedding(String text){

        EmbeddingRequest request =

                new EmbeddingRequest(
                        embeddingModel,
                        text
                );

        EmbeddingResponse response =

                openRouterClient.post()

                        .uri("/embeddings")

                        .header("Authorization","Bearer "+apiKey)

                        .header("Content-Type","application/json")

                        .bodyValue(request)

                        .retrieve()

                        .bodyToMono(EmbeddingResponse.class)

                        .block();

        return response.data()
                .getFirst()
                .embedding();
    }
}

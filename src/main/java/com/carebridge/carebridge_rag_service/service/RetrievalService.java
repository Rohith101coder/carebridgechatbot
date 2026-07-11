package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetrievalService {
     private final EmbeddingService embeddingService;

    private final VectorStoreService vectorStoreService;

    public List<String> retrieve(String question){

        List<Float> embedding =

                embeddingService.generateEmbedding(question);

        return vectorStoreService.search(embedding);

    }
}

package com.carebridge.carebridge_rag_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_rag_service.model.Chunk;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngestionService {
     private final PdfService pdfService;

    private final TextChunkService textChunkService;

    private final EmbeddingService embeddingService;

    private final VectorStoreService vectorStoreService;

    public String ingest(){

        String text = pdfService.extractText();

        List<Chunk> chunks = textChunkService.chunk(text);

       int total = chunks.size();
int index = 1;

for (Chunk chunk : chunks) {

    try {

        // System.out.println("Embedding chunk " + index + " / " + total);

        chunk.setEmbedding(
                embeddingService.generateEmbedding(chunk.getContent())
        );

        vectorStoreService.saveChunk(chunk);

    } catch (Exception e) {

        System.out.println("Failed chunk " + index);

        e.printStackTrace();

    }

    index++;
}

        return "Successfully Indexed " + chunks.size() + " Chunks";

    }
}

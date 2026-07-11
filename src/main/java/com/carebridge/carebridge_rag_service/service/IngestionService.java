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

        for(Chunk chunk : chunks){

            chunk.setEmbedding(

                    embeddingService.generateEmbedding(

                            chunk.getContent()
                    )
            );

            vectorStoreService.saveChunk(chunk);

        }

        return "Successfully Indexed " + chunks.size() + " Chunks";

    }
}

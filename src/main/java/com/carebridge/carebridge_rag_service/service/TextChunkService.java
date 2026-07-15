package com.carebridge.carebridge_rag_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_rag_service.model.Chunk;

@Service
public class TextChunkService {
    
     private static final int MAX_CHUNK_SIZE = 1200;

    public List<Chunk> chunk(String text) {

        List<Chunk> chunks = new ArrayList<>();

        String[] paragraphs = text.split("\\R\\s*\\R");

        StringBuilder current = new StringBuilder();

        int count = 1;

        for (String paragraph : paragraphs) {

            if (current.length() + paragraph.length() > MAX_CHUNK_SIZE) {

                chunks.add(new Chunk(
                        UUID.randomUUID().toString(),
                        "Chunk " + count++,
                        current.toString().trim(), null
                ));

                current.setLength(0);
            }

            current.append(paragraph).append("\n\n");
        }

        if (!current.isEmpty()) {

            chunks.add(new Chunk(
                    UUID.randomUUID().toString(),
                    "Chunk " + count,
                    current.toString().trim(), null
            ));
        }

        return chunks;
    }

}

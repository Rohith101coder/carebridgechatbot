package com.carebridge.carebridge_rag_service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chunk {

    private String id;

    private String title;

    private String content;

     private List<Float> embedding;
    
}

package com.carebridge.carebridge_rag_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_rag_service.service.IngestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    
     private final IngestionService ingestionService;

    @PostMapping("/ingest")
    public String ingest(){

        return ingestionService.ingest();

    }
}

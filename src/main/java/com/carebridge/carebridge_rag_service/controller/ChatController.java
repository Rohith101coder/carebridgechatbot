package com.carebridge.carebridge_rag_service.controller;

import java.util.List;

// import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_rag_service.dto.ChatRequest;
import com.carebridge.carebridge_rag_service.dto.ChatResponse;
import com.carebridge.carebridge_rag_service.model.Chunk;
import com.carebridge.carebridge_rag_service.service.ChatService;
import com.carebridge.carebridge_rag_service.service.EmbeddingService;
// import com.carebridge.carebridge_rag_service.service.ChatService;
import com.carebridge.carebridge_rag_service.service.OpenRouterService;
import com.carebridge.carebridge_rag_service.service.PdfService;
import com.carebridge.carebridge_rag_service.service.RetrievalService;
import com.carebridge.carebridge_rag_service.service.TextChunkService;

import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    
   private final OpenRouterService service;
   private final PdfService pdfService;
   private final TextChunkService textChunkService;
   private final EmbeddingService embeddingService;
   private final RetrievalService retrievalService;
   private final ChatService chatService;
//    private final OpenRouterService openRouterService;

   @GetMapping("/test")
    public String test(){
        return service.chat("say hello from openrouter!");
    }

    @GetMapping("/pdf")
    public String pdf(){
        return pdfService.extractText();
    }

    @GetMapping("/chunks")
public List<Chunk> chunks() {

    String text = pdfService.extractText();

    return textChunkService.chunk(text);
}

@GetMapping("/embedding")
public Integer embedding(){

    String text = pdfService.extractText();

    List<Chunk> chunks = textChunkService.chunk(text);

    return embeddingService
            .generateEmbedding(chunks.getFirst().getContent())
            .size();

}

@GetMapping("/retrieve")
public List<String> retrieve(

        @RequestParam String question
){

    return retrievalService.retrieve(question);

}

@PostMapping
public ChatResponse chat(@RequestBody ChatRequest request){

    return new ChatResponse(
            chatService.chat(request)
    );

}

// @PostMapping(value="/stream-test",
// produces = MediaType.TEXT_EVENT_STREAM_VALUE)
// public Flux<String> test(@RequestBody ChatRequest request){

//     return openRouterService.chat(request.getMessage());

// }
}

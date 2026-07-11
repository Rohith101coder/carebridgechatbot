package com.carebridge.carebridge_rag_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
// import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenRouterConfig {
    
      @Value("${qdrant.url}")
    private String qdrantUrl;

    @Bean
    public WebClient openRouterClient(){
        return WebClient.builder()
        .baseUrl("https://openrouter.ai/api/v1")
        .build();
    }

   @Bean
    public WebClient qdrantClient() {
        return WebClient.builder()
                .baseUrl(qdrantUrl)
                .build();
    }


}

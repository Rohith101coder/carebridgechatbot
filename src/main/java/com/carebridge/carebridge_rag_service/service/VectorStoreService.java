package com.carebridge.carebridge_rag_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.carebridge.carebridge_rag_service.dto.qdrant.Point;
import com.carebridge.carebridge_rag_service.dto.qdrant.PointPayload;
import com.carebridge.carebridge_rag_service.dto.qdrant.UpsertRequest;
import com.carebridge.carebridge_rag_service.dto.qdrant.search.QueryRequest;
import com.carebridge.carebridge_rag_service.dto.qdrant.search.QueryResponse;
import com.carebridge.carebridge_rag_service.model.Chunk;

// import lombok.RequiredArgsConstructor;
// import lombok.Value;

@Service
public class VectorStoreService {
      private final WebClient qdrantClient;

    public VectorStoreService(@Qualifier("qdrantClient") WebClient qdrantClient) {
        this.qdrantClient = qdrantClient;
    }

    @Value("${qdrant.api.key}")
    private String apiKey;

    @Value("${qdrant.collection}")
    private String collection;

    public void saveChunk(Chunk chunk){
                Point point = new Point(

                        chunk.getId(),

                Map.of(
                "",
                chunk.getEmbedding()
                ),

        new PointPayload(

                chunk.getTitle(),

                chunk.getContent()

        )

);

        UpsertRequest request =

                new UpsertRequest(
                        List.of(point)
                );

       @SuppressWarnings("unused")
       String response =
        qdrantClient.put()

                .uri("/collections/" + collection + "/points")

                .header("api-key", apiKey)

                .contentType(MediaType.APPLICATION_JSON)

                .bodyValue(request)

                .exchangeToMono(r -> r.bodyToMono(String.class))

                .block();

// System.out.println(response);

    }

    public List<String> search(List<Float> embedding){

  QueryRequest request =

        new QueryRequest(

                embedding,

                "",

                4,

                true

        );

    QueryResponse response =

            qdrantClient.post()

                    .uri("/collections/" + collection + "/points/query")

                    .header("api-key", apiKey)

                    .bodyValue(request)

                    .retrieve()

                    .bodyToMono(QueryResponse.class)

                    .block();

   return response.result()
        .points()
        .stream()
        .map(point -> point.payload().get("content").toString())
        .toList();

}


}

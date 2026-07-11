package com.carebridge.carebridge_rag_service.dto.qdrant.search;

import java.util.List;

public record QueryRequest(

        List<Float> query,

        String using,

        Integer limit,

        Boolean with_payload

) {}

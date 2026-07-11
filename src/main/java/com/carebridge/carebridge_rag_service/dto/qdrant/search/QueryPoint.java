package com.carebridge.carebridge_rag_service.dto.qdrant.search;

import java.util.Map;

public record QueryPoint(   String id,

        Float score,

        Map<String,Object> payload) {

}

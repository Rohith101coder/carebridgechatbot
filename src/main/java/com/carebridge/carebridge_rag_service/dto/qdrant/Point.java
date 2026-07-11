package com.carebridge.carebridge_rag_service.dto.qdrant;

import java.util.List;
import java.util.Map;

public record Point( String id,

        Map<String, List<Float>> vector,

        PointPayload payload) {

}

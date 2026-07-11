package com.carebridge.carebridge_rag_service.util;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String build(String question, List<String> contexts) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are CareBridge AI, a friendly and professional assistant for the CareBridge platform.

                Your job is to help users understand how CareBridge works.

                Rules:
                - Answer in a natural, friendly, and conversational tone.
                - Use only the information available in the provided context.
                - Do not invent or assume information.
                - Do not mention phrases like "according to the context" or "based on the provided context."
                - Do not add  slash n any where
                - If the answer is not available, politely reply:
                  "I'm sorry, I couldn't find that information in the CareBridge knowledge base."

                Knowledge Base:
                ----------------------------------------
                """);

        contexts.forEach(chunk ->
                prompt.append(chunk).append("\n\n"));

        prompt.append("""
                ----------------------------------------

                User Question:
                """);

        prompt.append(question);

        prompt.append("""

                Helpful Answer:
                """);

        return prompt.toString();
    }
}
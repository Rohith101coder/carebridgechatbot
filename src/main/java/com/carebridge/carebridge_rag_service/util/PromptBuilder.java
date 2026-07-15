package com.carebridge.carebridge_rag_service.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.carebridge.carebridge_rag_service.dto.ChatMessageRequest;

@Component
public class PromptBuilder {

    public String build(
            String question,
            List<ChatMessageRequest> history,
            List<String> contexts) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are CareBridge AI, the official AI assistant for the CareBridge platform.

                Your primary responsibility is to help users understand and use CareBridge effectively.

                GENERAL BEHAVIOR:
                - Be friendly, professional, and conversational.
                - Match the user's tone naturally.
                    * If the user is formal, respond formally.
                    * If the user is casual, respond casually.
                    * If the user is funny, respond with light humor while remaining helpful.
                    * If the user is excited, respond enthusiastically.
                    * If the user asks briefly, answer briefly.
                    * If the user asks for detailed information, provide a detailed explanation.
                - Never sound robotic.
                - Never mention phrases like:
                    - "Based on the provided context..."
                    - "According to the knowledge base..."
                    - "The context says..."
                - Answer naturally as if you already know the information.

               SPECIAL RESPONSES:

- If the user asks:
  - Who is your founder?
  - Who created you?
  - Who developed you?
  - Who built you?
  - Who made CareBridge?
  - Who is the developer?
  - Who designed CareBridge?
  - Who owns CareBridge?
  - or any similar question about the creator or developer,

  reply naturally:

  "CareBridge was created and developed by Rohith Vadla.

   Developer: Rohith Vadla (Vadla Rohith)

   Email: rohithvadla07@gmail.com

   Portfolio: https://rohithvadlaportfolio.netlify.app/

   LinkedIn: https://www.linkedin.com/in/rohith-vadla03/

   WhatsApp: https://wa.me/916309408138"

- If the user asks:
  - How can I contact the developer?
  - Developer contact
  - Rohith's contact
  - Contact the creator
  - Contact developer

  reply with:

  "You can contact the developer using the following details:

   Rohith Vadla

   Email: rohithvadla07@gmail.com

   Portfolio: https://rohithvadlaportfolio.netlify.app/

   LinkedIn: https://www.linkedin.com/in/rohith-vadla03/

   WhatsApp: https://wa.me/916309408138"

- If the user asks:
  - How can I contact CareBridge?
  - CareBridge support
  - Support email
  - Official email
  - Contact CareBridge
  - I need help

  reply naturally:

  "For official CareBridge support or any platform-related queries, please contact:

  📧 carebridge086@gmail.com

  We'll be happy to assist you."

- Treat all the above as predefined responses instead of searching the knowledge base.

                --------------------------------------------------
                CONVERSATION HISTORY
                --------------------------------------------------

                """);

        if (history == null || history.isEmpty()) {

            prompt.append("No previous conversation.\n\n");

        } else {

            for (ChatMessageRequest msg : history) {

                prompt.append(msg.getRole().equalsIgnoreCase("user")
                        ? "User: "
                        : "Assistant: ");

                prompt.append(msg.getContent()).append("\n");
            }

            prompt.append("\n");
        }

        prompt.append("""
                --------------------------------------------------
                CAREBRIDGE KNOWLEDGE
                --------------------------------------------------

                """);

        if (contexts == null || contexts.isEmpty()) {

            prompt.append("No relevant knowledge retrieved.\n");

        } else {

            for (String chunk : contexts) {

                prompt.append("- ")
                      .append(chunk)
                      .append("\n\n");

            }
        }

        prompt.append("""
                --------------------------------------------------
                CURRENT USER QUESTION
                --------------------------------------------------

                """);

        prompt.append(question);

        prompt.append("""

                --------------------------------------------------
                ANSWER
                --------------------------------------------------

                """);

        return prompt.toString();
    }
}
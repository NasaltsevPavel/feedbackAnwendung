package htw.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import htw.ai.model.AiInteraction;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {


    //private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private static final String PROMPT_CHECK_FEEDBACK = "Unten findest du einen Feedbacktext. " +
            "Du musst überprüfen, ob der Text konstruktiv geschrieben ist und keine Beleidigungen enthält. Falls dies nicht der Fall ist, schreibe 'false' gefolgt von dem umgeschriebenen, konstruktiven Text. Falls der eingegebene Text passt, schreibe einfach 'true'." +
            "Ein konstruktiver Text sollte:" + "1. Spezifisch und klar sein." +
            "2. Verbesserungsvorschläge enthalten." +
            "3. Respektvoll und höflich formuliert sein." +
            "Beachte: Deine Antwort sollte nur 'true' oder 'false' gefolgt vom umgeschriebenen Text enthalten, ohne zusätzliche Texte oder Erklärungen." +
            "Hier ist das Feedback:";

    private static final String PROMPT_CREATE_FEEDBACK = "";

    public AiInteraction aiCheck(AiInteraction aiInteraction) throws IOException {
        String APIanswer = callChatGPT(aiInteraction.feedback);
        if (APIanswer.length() == 4) {
            aiInteraction.aiGenerated = false;
        } else {
            String[] parts = APIanswer.split("\n", 2);
            aiInteraction.feedback = parts[1];
            aiInteraction.aiGenerated = true;
        }
        return aiInteraction;
    }

    private static String callChatGPT(String prompt) throws IOException {

        String userPrompt = prompt.replace("\n", "");

        System.out.println(userPrompt);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + API_KEY);

            String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": " +
                    "\" " + PROMPT_CHECK_FEEDBACK + "\"}, {\"role\": \"user\", \"content\": \"" + userPrompt + "\"}]}";
            post.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));

            return client.execute(post, httpResponse -> {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(httpResponse.getEntity());
                System.out.println("HTTP Status Code: " + statusCode);
                System.out.println("API Response: " + responseString);

                if (statusCode != 200) {
                    System.out.println("Fehler bei der Anfrage: " + statusCode);
                    return "Fehler bei der Anfrage: " + statusCode + ". Überprüfen Sie die Anfrage und versuchen Sie es erneut.";
                }

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(responseString);
                if (jsonResponse.has("choices") && !jsonResponse.get("choices").isEmpty()) {
                    JsonNode choices = jsonResponse.get("choices");
                    if (choices.get(0).has("message") && choices.get(0).get("message").has("content")) {
                        return choices.get(0).get("message").get("content").asText();
                    }
                }
                return "Keine Antwort erhalten oder die Antwort war nicht im erwarteten Format.";
            });
        }
    }

    public static String convertTextToJson(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", text.replace("\n", ""));

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package htw.feedback;

import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

@Service
public class FeedbackService {

    private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private static final String PROMPT_CHECK_FEEDBACK = "Unten ist einen Feedbacktext, du musst überprüfen ob der Text konstruktiv geschrieben ist und keinen Beleidigt. Falls es der Fall ist,dann schreib false und dann umgeschriebenen Text. Falls den eingegebenen Text passt dann schreib einfach true. Hier ist das Feedback: .";

    private static final String PROMPT_CREATE_FEEDBACK = "";

    private final FeedbackMapper feedbackMapper;

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackMapper feedbackMapper, FeedbackRepository feedbackRepository) {
        this.feedbackMapper = feedbackMapper;
        this.feedbackRepository = feedbackRepository;
    }


    public List<FeedbackEntity> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    public void deleteFeedbackId(String id){
        feedbackRepository.deleteById(id);
    }

    public FeedbackEntity create(FeedbackDTO feedbackDTO){
        return feedbackRepository.save(feedbackMapper.dtoToEntity(feedbackDTO));
    }


    private static String callChatGPT(String prompt) throws IOException {
        System.out.println(prompt);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + API_KEY);

            String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": " +
                    "\" "+ PROMPT_CHECK_FEEDBACK + "\"}, {\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
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

}

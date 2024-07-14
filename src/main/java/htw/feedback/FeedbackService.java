package htw.feedback;

import htw.feedback.enums.Role;
import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

@Service
public class FeedbackService {

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

    public FeedbackEntity changeStatus(String id) {
        Optional<FeedbackEntity> feedbackEntity = feedbackRepository.findById(id);

        if (feedbackEntity.isPresent()) {
            FeedbackEntity entity = feedbackEntity.get();
            entity.setActive(!entity.isActive());
            return feedbackRepository.save(entity);
        } else {
            throw new EntityNotFoundException("FeedbackEntity mit ID " + id + " wurde nicht gefunden");
        }
    }

    public List<FeedbackEntity> getByReceiver(String role){
        return feedbackRepository.findByReceiver(role);
    }


}

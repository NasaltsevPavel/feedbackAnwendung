package htw.feedback;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    public List<FeedbackDTO> findFeedbackByUserId(String userId){
        return new ArrayList<>();
    }

    public void deleteFeedbackByUserId(String userId){

    }

    public FeedbackCheck checkFeedbackText(String message){

        String APIanswer = callOpenAi(message);


        return new FeedbackCheck();

    }

    private String callOpenAi(String message){
        return "true; Blablabla";
    }

    public FeedbackDTO createFeedback(String userId1, String userId2, String message){

        return new FeedbackDTO();
    }

}

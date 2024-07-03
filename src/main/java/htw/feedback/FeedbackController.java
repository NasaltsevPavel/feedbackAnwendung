package htw.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("feedback/")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }

    @PostMapping("check")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<FeedbackCheck> checkFeedback(@RequestBody FeedbackCheck feedbackCheck) throws IOException {
        return ResponseEntity.ok(feedbackService.checkFeedbackText(feedbackCheck));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id) {
        feedbackService.deleteFeedbackId(id);
        return ResponseEntity.noContent().build();
    }

}

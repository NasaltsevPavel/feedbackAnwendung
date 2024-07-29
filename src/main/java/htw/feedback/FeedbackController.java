package htw.feedback;

import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("feedback/")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("feedbacks")
    public ResponseEntity<List<FeedbackEntity>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());

    }

    @PostMapping("create")
    public ResponseEntity<FeedbackEntity> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.create(feedbackDTO), HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<FeedbackEntity> changeStatus(@PathVariable String id) {
        return new ResponseEntity<>(feedbackService.changeStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id) {
        feedbackService.deleteFeedbackId(id);
        return ResponseEntity.noContent().build();
    }

}

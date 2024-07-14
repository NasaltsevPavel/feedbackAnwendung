package htw.ai;

import htw.ai.model.AiInteraction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("ai/")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("check")
    public ResponseEntity<AiInteraction> checkFeedback(@RequestBody AiInteraction aiInteraction) throws IOException {
        return ResponseEntity.ok(aiService.aiCheck(aiInteraction));
    }

    @PostMapping("create")
    public ResponseEntity<AiInteraction> createFeedback(@RequestBody AiInteraction aiInteraction) throws IOException {
        return ResponseEntity.ok(aiService.aiCreate(aiInteraction));
    }

}

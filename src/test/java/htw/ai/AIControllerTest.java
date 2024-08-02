package htw.ai;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import htw.ai.model.AiInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AIControllerTest {

    @Mock
    AIService aiService;

    @InjectMocks
    AIController aiController;

    @Mock
    AiInteraction mockedAiInteraction;

    @BeforeEach
    void setup() {
        aiController = new AIController(aiService);
    }

    @Test
    void checkFeedback() throws IOException {
        // given
        AiInteraction aiInteraction = new AiInteraction("Text", false);
        when(aiService.aiCheck(aiInteraction)).thenReturn(mockedAiInteraction);

        // when
        ResponseEntity<AiInteraction> responseEntity = aiController.checkFeedback(aiInteraction);

        // then
        assertEquals(mockedAiInteraction, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createFeedback() throws IOException {
        // given
        AiInteraction aiInteraction = new AiInteraction("Text", false);
        when(aiService.aiCreate(aiInteraction)).thenReturn(mockedAiInteraction);

        // when
        ResponseEntity<AiInteraction> responseEntity = aiController.createFeedback(aiInteraction);

        // then
        assertEquals(mockedAiInteraction, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

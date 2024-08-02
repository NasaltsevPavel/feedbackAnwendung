package htw.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {

    @Mock
    FeedbackService feedbackService;

    @InjectMocks
    FeedbackController feedbackController;

    @Mock
    FeedbackEntity mockedFeedbackEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        feedbackController = new FeedbackController(feedbackService);
    }

    @Test
    void getAllFeedback() {
        // given
        List<FeedbackEntity> feedbackList = new ArrayList<>();
        feedbackList.add(mockedFeedbackEntity);
        when(feedbackService.getAllFeedback()).thenReturn(feedbackList);

        // when
        ResponseEntity<List<FeedbackEntity>> responseEntity = feedbackController.getAllFeedback();

        // then
        assertEquals(feedbackList, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createFeedback() {
        // given
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        when(feedbackService.create(feedbackDTO)).thenReturn(mockedFeedbackEntity);

        // when
        ResponseEntity<FeedbackEntity> responseEntity = feedbackController.createFeedback(feedbackDTO);

        // then
        assertEquals(mockedFeedbackEntity, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void changeStatus() {
        // given
        String id = "1";
        when(feedbackService.changeStatus(id)).thenReturn(mockedFeedbackEntity);

        // when
        ResponseEntity<FeedbackEntity> responseEntity = feedbackController.changeStatus(id);

        // then
        assertEquals(mockedFeedbackEntity, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteFeedback() {
        // given
        String id = "1";

        // when
        ResponseEntity<Void> responseEntity = feedbackController.deleteFeedback(id);

        // then
        verify(feedbackService).deleteFeedbackId(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

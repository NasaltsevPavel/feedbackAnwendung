package htw.feedback;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    FeedbackRepository feedbackRepository;

    @Mock
    FeedbackMapper feedbackMapper;

    @InjectMocks
    FeedbackService feedbackService;

    @Mock
    FeedbackEntity mockedFeedbackEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        feedbackService = new FeedbackService(feedbackMapper, feedbackRepository);
    }

    @Test
    void getAllFeedback() {
        // given
        List<FeedbackEntity> feedbackList = Arrays.asList(mockedFeedbackEntity);
        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        // when
        List<FeedbackEntity> result = feedbackService.getAllFeedback();

        // then
        assertEquals(feedbackList, result);
    }

    @Test
    void deleteFeedbackId() {
        // given
        String id = "1";

        // when
        feedbackService.deleteFeedbackId(id);

        // then
        verify(feedbackRepository).deleteById(id);
    }

    @Test
    void create() {
        // given
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        when(feedbackMapper.dtoToEntity(any(FeedbackDTO.class))).thenReturn(mockedFeedbackEntity);
        when(feedbackRepository.save(any(FeedbackEntity.class))).thenReturn(mockedFeedbackEntity);

        // when
        FeedbackEntity result = feedbackService.create(feedbackDTO);

        // then
        assertEquals(mockedFeedbackEntity, result);
    }

    @Test
    void changeStatus() {
        // given
        String id = "1";
        when(feedbackRepository.findById(id)).thenReturn(Optional.of(mockedFeedbackEntity));
        when(mockedFeedbackEntity.isActive()).thenReturn(true);
        when(feedbackRepository.save(any(FeedbackEntity.class))).thenReturn(mockedFeedbackEntity);

        // when
        FeedbackEntity result = feedbackService.changeStatus(id);

        // then
        assertEquals(mockedFeedbackEntity, result);
        verify(mockedFeedbackEntity).setActive(false);
    }

    @Test
    void changeStatus_EntityNotFound() {
        // given
        String id = "1";
        when(feedbackRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class, () -> feedbackService.changeStatus(id));
    }
}

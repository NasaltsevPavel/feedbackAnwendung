package htw.feedback;

import htw.feedback.model.FeedbackDTO;
import htw.feedback.model.FeedbackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
public  class FeedbackMapper  {


    public FeedbackEntity dtoToEntity(FeedbackDTO dto) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setMeeting(dto.isMeeting());
        feedbackEntity.setAnonym(dto.isAnonym());
        feedbackEntity.setActive(dto.isActive());
        feedbackEntity.setReceiver(dto.getReceiver().name());
        feedbackEntity.setSender(dto.getSender().name());
        feedbackEntity.setText(dto.getText());

        return feedbackEntity;
    }
}

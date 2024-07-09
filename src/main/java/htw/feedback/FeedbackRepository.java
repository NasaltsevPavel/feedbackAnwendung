package htw.feedback;

import htw.feedback.model.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,String> {


    FeedbackEntity findByReceiver(String receiver);

}

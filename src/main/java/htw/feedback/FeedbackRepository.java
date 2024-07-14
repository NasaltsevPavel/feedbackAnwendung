package htw.feedback;

import htw.feedback.model.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,String> {


    List<FeedbackEntity> findByReceiver(String receiver);


}

package htw.feedback;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class FeedbackEntity {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name ="system-uuid", strategy = "uuid2")
    private String id;
    private String message;

}

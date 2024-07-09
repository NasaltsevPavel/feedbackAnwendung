package htw.feedback.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class FeedbackEntity {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name ="system-uuid", strategy = "uuid2")
    private String id;
    private String sender;
    private String receiver;
    @Column(columnDefinition = "TEXT")
    private String text;
    private boolean meeting;
    private boolean anonym;
    private boolean active;

}

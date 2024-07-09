package htw.feedback.model;

import htw.feedback.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {

    private String id;
    private Role sender;
    private Role receiver;
    private String text;
    private boolean meeting;
    private boolean anonym;
    private boolean active;


}

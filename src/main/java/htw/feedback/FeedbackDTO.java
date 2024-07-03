package htw.feedback;

import htw.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {

    private String id;
    private Role sender;
    private Role receiver;
    private String message;
    private boolean meeting;
    private boolean anonym;

    public FeedbackDTO(String id, Role sender, Role receiver, String message, boolean meeting, boolean anonym) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.meeting = meeting;
        this.anonym = anonym;
    }
}

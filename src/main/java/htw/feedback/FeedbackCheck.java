package htw.feedback;

import htw.user.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeedbackCheck {

    public Role from;

    public Role to;

    public String text;
    public boolean aiGenerated;

    public String message;


    public FeedbackCheck(Role from, Role to, String text, boolean aiGenerated, String message) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.aiGenerated = aiGenerated;
        this.message = message;
    }
}

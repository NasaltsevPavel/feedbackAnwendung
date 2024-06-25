package htw.feedback;

import htw.user.UserDTO;

public class FeedbackDTO {

    public Long id;

    public UserDTO from;

    public UserDTO to;

    public String message;

    public boolean meeting;
}

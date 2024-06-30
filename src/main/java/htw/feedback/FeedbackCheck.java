package htw.feedback;

public class FeedbackCheck {
    public String text;
    public boolean aiGenerated;

    public String message;

    public FeedbackCheck(String text, boolean aiGenerated, String message) {
        this.text = text;
        this.aiGenerated = aiGenerated;
        this.message = message;
    }

}

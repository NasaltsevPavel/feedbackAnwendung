package htw.ai.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiInteraction {

    public String feedback;
    public boolean aiGenerated;

    public AiInteraction(String feedback, boolean aiGenerated) {
        this.feedback = feedback;
        this.aiGenerated = aiGenerated;
    }
}

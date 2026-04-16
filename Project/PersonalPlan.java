public class PersonalPlan extends AIModel {
    private int promptsRemaining;

    public PersonalPlan(String modelName, double price, int parameterCount, int contextWindow, int promptsRemaining) {
        super(modelName, price, parameterCount, contextWindow);
        this.promptsRemaining = promptsRemaining;
    }

    public String buyPrompts(int count) {
        if (count < 0) {
            return "User must enter positive value or user must upgrade to pro plan.";
        }
        promptsRemaining += count;
        return "Prompts added successfully. New quota: " + promptsRemaining;
    }

    public int getPromptsRemaining() {
        return promptsRemaining;
    }

    @Override
    public String display() {
        return "Personal Plan:\n" + super.display() +
                "\nRemaining Prompts: " + promptsRemaining;
    }

    @Override
    public String enterPrompt(String promptText, int responseLength) {
        if (promptsRemaining > 0) {
            promptsRemaining--;
            return "Prompt Details: " + promptText +
                    "\nToken Usage: " + responseLength +
                    "\nRemaining Prompts: " + promptsRemaining;
        } else {
            return "Monthly plan has been reached.";
        }
    }
}

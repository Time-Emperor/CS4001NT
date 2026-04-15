public class PersonalPlan extends AIModel {
    private int promptsRemaining;

    public PersonalPlan(String modelName, double price, int parameterCount, int contextWindow) {
        super(modelName, price, parameterCount, contextWindow);
    }

    public void buyPrompts(int count) {
        if (count > 0) {
            promptsRemaining += count;

        }
    }

    public int getPromptsRemaining() {
        return promptsRemaining;
    }

    @Override
    public String display() {
        return "Personal: \n" +
                "Model Name: " + getModelName() + "\n" +
                "Price: $" + getPrice() + " per month\n" +
                "Parameter Count: " + getParameterCount() + "\n" +
                "Context Window: " + getContextWindow() + " tokens\n" +
                "Remaining Prompts: " + promptsRemaining;
    }

    @Override
    public String enterPrompt(String prompt, int tokens) {
        if (promptsRemaining > 0 && tokens <= getContextWindow()) {
            promptsRemaining--;
            return "Prompt accepted: Tokens " + tokens + " Prompts left " + promptsRemaining;
        } else if (promptsRemaining <= 0) {
            return "Error: No remaining prompts. Please buy more prompts to continue.";
        } else {
            return "Error: Prompt exceeds the context window of " + getContextWindow() + " tokens.";
        }
    }
}

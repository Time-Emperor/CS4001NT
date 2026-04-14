public class PersonalPlan extends AIModel {
    private int promptRemaining;

    public PersonalPlan(String modelName, double price, int parameterCount, int contextWindow) {
        super(modelName, price, parameterCount, contextWindow);
    }

    public void buyPrompts(int count) {
        if (count > 0) {
            promptRemaining += count;

        }
    }

    public int getPromptRemaining() {
        return promptRemaining;
    }

    @Override
    public String display() {
        return "Personal: \n" +
                "Model Name: " + getModelName() + "\n" +
                "Price: $" + getPrice() + " per month\n" +
                "Parameter Count: " + getParameterCount() + "\n" +
                "Context Window: " + getContextWindow() + " tokens\n" +
                "Remaining Prompts: " + promptRemaining;
    }

    @Override
    public String enterPrompt(String prompt, int expectedTokensCount) {
        int tokens = calculateTokens(prompt);
        if (promptRemaining > 0 && tokens <= getContextWindow()) {
            promptRemaining--;
            return "Prompt accepted: Tokens " + tokens + " Prompts left " + promptRemaining;
        } else if (promptRemaining <=0) {
            return "Error: No remaining prompts. Please buy more prompts to continue.";
        } else {
            return "Error: Prompt exceeds the context window of " + getContextWindow() + " tokens.";
        }

    }
}

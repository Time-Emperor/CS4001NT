/**
 * It is the personal subscription plkan for the AI.
 * It tracks a monthly Quota that decreases with every prompt.
 * Prompts can be bought via buy prompt button.
 */
public class PersonalPlan extends AIModel {
    private int promptsRemaining;

    public PersonalPlan(String modelName, double price, int parameterCount, String contextWindow,
            int promptsRemaining) {
        super(modelName, price, parameterCount, contextWindow);
        this.promptsRemaining = promptsRemaining;
    }

    public String buyPrompts(int count) {
        // making sure the user doesn't enter a negative number
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
        // check if user has exhausted their monthly quota
        if (promptsRemaining <= 0) {
            return "Monthly plan has been reached. Please buy more prompts or upgrade to a Pro plan.";
        }

        int maxTokens = 0;
        try {
            // handle inputs like "128K" or "64k" by removing K and multiplying by 1000
            String cw = getContextWindow().trim().toUpperCase();
            if (cw.endsWith("K")) {
                maxTokens = Integer.parseInt(cw.replace("K", "")) * 1000;
            } else {
                maxTokens = Integer.parseInt(cw);
            }
        } catch (NumberFormatException e) {
            // fallback in case they typed something weird instead of a number
            maxTokens = Integer.MAX_VALUE;
        }
        if (responseLength > maxTokens) {
            return "Failed to enter prompt. Prompt exceeds context window.";
        }
        promptsRemaining--;
        return "Prompt Details: " + promptText +
                "\nToken Usage: " + responseLength +
                "\nRemaining Prompts: " + promptsRemaining;
    }
}

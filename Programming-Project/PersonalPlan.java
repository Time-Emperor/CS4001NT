
public class PersonalPlan extends AIModel {

    // Monthly Prompt Quota:
    private int promptsRemaining;

    /**
     * Constructor to initialize PersonalPlan.
     */
    public PersonalPlan(String modelName,
                        double price,
                        int parameterCount,
                        int contextWindow,
                        int initialMonthlyQuota) {
        super(modelName, price, parameterCount, contextWindow);
        this.promptsRemaining = initialMonthlyQuota;
    }

    /**
     * Adds prompts to promptsRemaining.
     */
    public String purchasePrompts(int amount) {
        if (amount <= 0) {
            return "Error: amount must be positive.";
        }
        promptsRemaining += amount;
        return "Successfully purchased " + amount
                + " prompts. New balance: " + promptsRemaining + ".";
    }

    /**
     * Dual Validation Logic for using a prompt:
     * 1. Quota Check
     * 2. Context Check
     */
    public String usePrompt() {
        // 1. Quota Check
        if (promptsRemaining <= 0) {
            return "Monthly quota exhausted";
        }

        // 2. Context Check
        boolean withinContext = calculateTokenUsage();
        if (!withinContext) {
            return "Context limit exceeded";
        }

        // 3. Success:
        promptsRemaining--;
        return "Prompt processed successfully. Prompts remaining: " + promptsRemaining;
    }

    @Override
    public String display() {
        return "Personal Plan\n"
                + "Model: " + getModelName() + "\n"
                + "Price (per 100K tokens): " + getPrice() + " NPR\n"
                + "Parameters: " + getParameterCount() + "B\n"
                + "Context window: " + getContextWindow() + " tokens\n"
                + "Prompts remaining (Monthly Quota): " + promptsRemaining;
    }

    // Getter for promptsRemaining
    public int getPromptsRemaining() {
        return promptsRemaining;
    }
}


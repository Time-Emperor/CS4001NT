/**
 * Personal plan for recreational users.
 * Enforces both a monthly request quota and context window limits.
 */
public class PersonalPlan extends AIModel {

    // Monthly Prompt Quota: tracks remaining requests for the month.
    private int promptsRemaining;

    /**
     * Constructor
     * Accepts 5 parameters: passes 4 to super, initializes promptsRemaining.
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
     * Validation: amount must be positive.
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
     * 2. Context Check via AIModel.calculateTokenUsage()
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

        // 3. Success: decrement quota
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

    // Getter to help demonstration in Main (not required by spec but simple and harmless)
    public int getPromptsRemaining() {
        return promptsRemaining;
    }
}


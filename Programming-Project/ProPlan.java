public class ProPlan extends AIModel {

    private int availableSlots;

    /**
     * Constructor to initialize ProPlan 
     */
    public ProPlan(String modelName,
                   double price,
                   int parameterCount,
                   int contextWindow,
                   int initialSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = initialSlots;
    }

    /**
     * Decrements availableSlots if space exists.
     */
    public String addTeamMember() {
        if (availableSlots <= 0) {
            return "No available slots for new team members.";
        }
        availableSlots--;
        return "Team member added. Available slots: " + availableSlots;
    }

    /**
     * Increments availableSlots.
     */
    public String removeTeamMember() {
        availableSlots++;
        return "Team member removed. Available slots: " + availableSlots;
    }

    /**
     * Single validation logic:
        * Only checks context limits since Pro plan has no quota restrictions.
        */
    public String usePrompt() {
        boolean withinContext = calculateTokenUsage();
        if (withinContext) {
            return "Prompt processed successfully for Pro plan (no quota limit).";
        }
        return "Context limit exceeded";
    }

    @Override
    public String display() {
        return "Pro Plan\n"
                + "Model: " + getModelName() + "\n"
                + "Price (per 100K tokens): " + getPrice() + " NPR\n"
                + "Parameters: " + getParameterCount() + "B\n"
                + "Context window: " + getContextWindow() + " tokens\n"
                + "Available team member slots: " + availableSlots;
    }

    // Helper for demonstration in Main
    public int getAvailableSlots() {
        return availableSlots;
    }
}


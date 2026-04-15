public class ProPlan extends AIModel {
    private int availableSlots;

    public ProPlan(String modelName, double price, int parameterCount, int contextWindow) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = 0;
    }

    public void addTeamMember() {
        availableSlots++;
    }

    public void removeTeamMember() {
        if (availableSlots > 0) {
            availableSlots--;
        } else {
            System.out.println("No team members to remove.");
        }
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public String display() {
        return "Plan: Pro\n" +
                "Model Name: " + getModelName() + "\n" +
                "Price: $" + getPrice() + " per month\n" +
                "Parameter Count: " + getParameterCount() + "\n" +
                "Context Window: " + getContextWindow() + " tokens\n" +
                "Available Team Slots: " + availableSlots;
    }

    @Override
    public String enterPrompt(String prompt, int tokens) {
        if (tokens <= getContextWindow()) {
            int remaining = getContextWindow() - tokens;
            return "Prompt accepted. Remaining tokens: " + remaining;
        } else {
            return "Failed to enter prompt. Prompt exceeds context window. Please shorten your prompt.";
        }

    }
}

public class ProPlan extends AIModel {
    private int availableSlots;

    public ProPlan(String modelName, double price, int parameterCount, int contextWindow, int availableSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = availableSlots;
    }

    public String addTeamMember(String name) {
        if (availableSlots > 0) {
            availableSlots--;
            return "Member " + name + " added. Remaining slots: " + availableSlots;
        } else {
            return "Error: No available slots and team member cannot be added.";
        }
    }

    public String removeTeamMember(String name) {
        availableSlots++;
        return "Member " + name + " removed. New available slots: " + availableSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public String display() {
        return "Pro Plan:\n" + super.display() +
                "\nAvailable Team Slots: " + availableSlots;
    }

    @Override
    public String enterPrompt(String prompt, int tokens) {
        if (tokens <= getContextWindow()) {
            return "Prompt accepted for Pro Plan. Token usage: " + tokens;
        } else {
            return "Failed to enter prompt. Prompt exceeds context window.";
        }
    }
}

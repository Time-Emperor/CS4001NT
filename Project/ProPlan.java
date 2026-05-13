
/**
 * It is a ProPlan for the AI model.
 * It supports team collaboration by managing available member slots.
 * Members can be added and removed and the code itself does the adjusting slots.
 * The proPlan are unlimited
 */
import java.util.ArrayList;

public class ProPlan extends AIModel {
    private int availableSlots;

    public ProPlan(String modelName, double price, int parameterCount, String contextWindow, int availableSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = availableSlots;
    }

    public String addTeamMember(String name) {
        // only add member if there is at least 1 slot free
        if (availableSlots > 0) {
            availableSlots--;
            return "Member " + name + " added. Remaining slots: " + availableSlots;
        } else {
            return "Error: No available slots and team member cannot be added.";
        }
    }

    public String removeTeamMember(String name) {
        // freeing up a slot when a team member is removed
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
        int maxTokens = 0;
        try {
            // parsing string context windows (e.g. 128k, 64k) properly
            String cw = getContextWindow().trim().toUpperCase();
            if (cw.endsWith("K")) {
                maxTokens = Integer.parseInt(cw.replace("K", "")) * 1000;
            } else {
                maxTokens = Integer.parseInt(cw);
            }
        } catch (NumberFormatException e) {
            maxTokens = Integer.MAX_VALUE; // ignore check if it's text like "Unlimited"
        }

        if (tokens <= maxTokens) {
            return "Prompt accepted for Pro Plan. Token usage: " + tokens;
        } else {
            return "Failed to enter prompt. Prompt exceeds context window.";
        }
    }
}

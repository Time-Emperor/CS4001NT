import java.util.ArrayList;

public class ProPlan extends AIModel {
    private int availableSlots;
    private ArrayList<String> teamMembers;

    public ProPlan(String modelName, double price, int parameterCount, int contextWindow, int availableSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = availableSlots;
        this.teamMembers = new ArrayList<>();
    }

    public String addTeamMember(String name) {
        if (availableSlots > 0) {
            availableSlots--;
            teamMembers.add(name);
            return "Member " + name + " added. Remaining slots: " + availableSlots;
        } else {
            return "Error: No available slots and team member cannot be added.";
        }
    }

    public String removeTeamMember(String name) {
        if (teamMembers.remove(name)) {
            availableSlots++;
            return "Member " + name + " removed. New available slots: " + availableSlots;
        } else {
            return "Error: Member " + name + " not found in the team.";
        }
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public String display() {
        String membersList = teamMembers.isEmpty() ? "No members yet" : String.join(", ", teamMembers);
        return "Pro Plan:\n" + super.display() +
                "\nAvailable Team Slots: " + availableSlots +
                "\nTeam Members: " + membersList;
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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SubscriptionGUI extends JFrame {

    private ArrayList<AIModel> modelList = new ArrayList<>();


    private JTextField modelNameField, priceField, parameterCountField, contextWindowField,
            promptsField, slotsField, promptTextField, responseLengthField,
            teamMemberNameField, indexField;
    private JTextArea outputArea;

    public SubscriptionGUI() {
        setTitle("AI Model Subscription Management System");
        setSize(850, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("AI Model Subscription Details"));

        inputPanel.add(new JLabel("1. Model Name:"));
        modelNameField = new JTextField();
        inputPanel.add(modelNameField);

        inputPanel.add(new JLabel("2. Pricing (per 1 Lakh tokens):"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("3. Parameter Count (billions):"));
        parameterCountField = new JTextField();
        inputPanel.add(parameterCountField);

        inputPanel.add(new JLabel("4. Context Window (K tokens):"));
        contextWindowField = new JTextField();
        inputPanel.add(contextWindowField);

        inputPanel.add(new JLabel("5. Initial Prompts Quota (Personal):"));
        promptsField = new JTextField();
        inputPanel.add(promptsField);

        inputPanel.add(new JLabel("6. Initial Team Slots (Pro):"));
        slotsField = new JTextField();
        inputPanel.add(slotsField);

        inputPanel.add(new JLabel("7. Prompt Text:"));
        promptTextField = new JTextField();
        inputPanel.add(promptTextField);

        inputPanel.add(new JLabel("8. Response Length (tokens):"));
        responseLengthField = new JTextField();
        inputPanel.add(responseLengthField);

        inputPanel.add(new JLabel("9. Team Member Name:"));
        teamMemberNameField = new JTextField();
        inputPanel.add(teamMemberNameField);

        inputPanel.add(new JLabel("10. Index Number:"));
        indexField = new JTextField();
        inputPanel.add(indexField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addPersonalBtn = new JButton("Add Personal Plan");
        JButton addProBtn = new JButton("Add Pro Plan");
        JButton displayAllBtn = new JButton("Display All");
        JButton clearBtn = new JButton("Clear");
        JButton givePromptBtn = new JButton("Give a Prompt");
        JButton addTeamMemberBtn = new JButton("Add Team Member");
        JButton removeMemberBtn = new JButton("Remove Team Member");
        JButton checkTypeBtn = new JButton("Check Plan Type");

        buttonPanel.add(addPersonalBtn);
        buttonPanel.add(addProBtn);
        buttonPanel.add(displayAllBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(givePromptBtn);
        buttonPanel.add(addTeamMemberBtn);
        buttonPanel.add(removeMemberBtn);
        buttonPanel.add(checkTypeBtn);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.NORTH);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // --- Button Listeners ---

        addPersonalBtn.addActionListener(e -> {
            try {
                String name = modelNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int params = Integer.parseInt(parameterCountField.getText());
                int window = Integer.parseInt(contextWindowField.getText());
                int prompts = Integer.parseInt(promptsField.getText());

                PersonalPlan plan = new PersonalPlan(name, price, params, window, prompts);
                modelList.add(plan);
                JOptionPane.showMessageDialog(this, "Personal Plan Added successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all required numeric fields for Personal Plan correctly.");
            }
        });

        addProBtn.addActionListener(e -> {
            try {
                String name = modelNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int params = Integer.parseInt(parameterCountField.getText());
                int window = Integer.parseInt(contextWindowField.getText());
                int slots = Integer.parseInt(slotsField.getText());

                ProPlan plan = new ProPlan(name, price, params, window, slots);
                modelList.add(plan);
                JOptionPane.showMessageDialog(this, "Pro Plan Added successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please fill all required numeric fields for Pro Plan correctly.");
            }
        });

        displayAllBtn.addActionListener(e -> {
            outputArea.setText("");
            for (int i = 0; i < modelList.size(); i++) {
                outputArea.append("Plan Index: " + i + "\n");
                outputArea.append(modelList.get(i).display() + "\n");
                outputArea.append("----------------------------\n");
            }
        });

        clearBtn.addActionListener(e -> {
            modelNameField.setText("");
            priceField.setText("");
            parameterCountField.setText("");
            contextWindowField.setText("");
            promptsField.setText("");
            slotsField.setText("");
            promptTextField.setText("");
            responseLengthField.setText("");
            teamMemberNameField.setText("");
            indexField.setText("");
        });

        givePromptBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    try {
                        String text = promptTextField.getText();
                        int length = Integer.parseInt(responseLengthField.getText());
                        String result = ((PersonalPlan) model).enterPrompt(text, length);
                        outputArea.append("Model Result:\n" + result + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid response length.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "This operation is only available for Personal Plan subscriptions.");
                }
            }
        });

        addTeamMemberBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof ProPlan) {
                    String name = teamMemberNameField.getText();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter a team member name.");
                        return;
                    }
                    String result = ((ProPlan) model).addTeamMember(name);
                    outputArea.append(result + "\n");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Team collaboration is only available for Pro Plan subscriptions.");
                }
            }
        });

        removeMemberBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof ProPlan) {
                    String name = teamMemberNameField.getText();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter a team member name to remove.");
                        return;
                    }
                    String result = ((ProPlan) model).removeTeamMember(name);
                    outputArea.append(result + "\n");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Team collaboration is only available for Pro Plan subscriptions.");
                }
            }
        });

        checkTypeBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    JOptionPane.showMessageDialog(this, "Plan Type: Personal Plan");
                } else if (model instanceof ProPlan) {
                    JOptionPane.showMessageDialog(this, "Plan Type: Pro Plan");
                } else {
                    JOptionPane.showMessageDialog(this, "Plan Type: Unknown");
                }
            }
        });
    }

    private int getValidIndex() {
        int displayNumber = -1;
        try {
            String inputText = indexField.getText();
            displayNumber = Integer.parseInt(inputText);
            if (displayNumber < 0 || displayNumber >= modelList.size()) {
                JOptionPane.showMessageDialog(this,
                        "Index falls outside the acceptable range (0 to " + (modelList.size() - 1) + ").");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input cannot be parsed as an integer. Please enter a valid number.");
            return -1;
        }
        return displayNumber;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SubscriptionGUI().setVisible(true));
    }
}
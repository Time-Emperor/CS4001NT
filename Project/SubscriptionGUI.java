import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

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

        inputPanel.add(new JLabel("2. Pricing (per 1 Lakh Tokens):"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("3. Parameter Count (Billions):"));
        parameterCountField = new JTextField();
        inputPanel.add(parameterCountField);

        inputPanel.add(new JLabel("4. Context Window (Tokens):"));
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

        JPanel buttonPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addPersonalBtn = new JButton("Add Personal Plan");
        JButton addProBtn = new JButton("Add Pro Plan");
        JButton displayAllBtn = new JButton("Display All");
        JButton clearBtn = new JButton("Clear");
        JButton givePromptBtn = new JButton("Give a Prompt");
        JButton addTeamMemberBtn = new JButton("Add Team Member");
        JButton removeMemberBtn = new JButton("Remove Team Member");
        JButton checkTypeBtn = new JButton("Check Plan Type");
        JButton exportBtn = new JButton("Export to File");
        JButton loadBtn = new JButton("Load from File");
        JButton buyPromptsBtn = new JButton("Buy Prompts");

        buttonPanel.add(addPersonalBtn);
        buttonPanel.add(addProBtn);
        buttonPanel.add(displayAllBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(givePromptBtn);
        buttonPanel.add(addTeamMemberBtn);
        buttonPanel.add(removeMemberBtn);
        buttonPanel.add(checkTypeBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(buyPromptsBtn);

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
                        "Please fill all required numeric fields (Price, Parameters, Context Window, Quota) correctly.");
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
                try {
                    String text = promptTextField.getText();
                    if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter prompt text.");
                        return;
                    }
                    int length = Integer.parseInt(responseLengthField.getText());
                    String result = model.enterPrompt(text, length);
                    outputArea.append("Model Result (Index " + index + "):\n" + result + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid numeric response length.");
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

        exportBtn.addActionListener(e -> exportToFile());
        loadBtn.addActionListener(e -> loadFromFile());

        buyPromptsBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    try {
                        int count = Integer.parseInt(promptsField.getText());
                        String result = ((PersonalPlan) model).buyPrompts(count);
                        outputArea.append(result + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Please enter a valid number of prompts to buy in the Quota field.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Buy Prompts is only available for Personal Plan subscriptions.");
                }
            }
        });
    }

    private void exportToFile() {
        if (modelList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No plans to export.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("subscriptions.dat"))) {
            oos.writeObject(modelList);
            JOptionPane.showMessageDialog(this, "Data exported successfully to subscriptions.dat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("subscriptions.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<AIModel> loaded = (ArrayList<AIModel>) ois.readObject();
            modelList = loaded;
            outputArea.setText("Data loaded successfully from subscriptions.dat\n");
            for (int i = 0; i < modelList.size(); i++) {
                outputArea.append("Plan Index: " + i + "\n");
                outputArea.append(modelList.get(i).display() + "\n");
                outputArea.append("----------------------------\n");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No saved data found (subscriptions.dat).");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Load failed: " + ex.getMessage());
        }
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
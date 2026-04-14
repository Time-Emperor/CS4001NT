import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SubscriptionGUI extends JFrame {

    private ArrayList<AIModel> modelList = new ArrayList<>();

    private JTextField modelNameField, priceField, parameterCountField, contextWindowField, promptsSlotsField,
            promptTextField, indexField;
    private JTextArea outputArea;

    public SubscriptionGUI() {
        setTitle("AI Model Subscription");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("AI Model Details"));

        inputPanel.add(new JLabel("Model Name:"));
        modelNameField = new JTextField();
        inputPanel.add(modelNameField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Parameter Count:"));
        parameterCountField = new JTextField();
        inputPanel.add(parameterCountField);

        inputPanel.add(new JLabel("Context Window:"));
        contextWindowField = new JTextField();
        inputPanel.add(contextWindowField);

        inputPanel.add(new JLabel("Prompts/Team Slots:"));
        promptsSlotsField = new JTextField();
        inputPanel.add(promptsSlotsField);

        inputPanel.add(new JLabel("Prompt Text:"));
        promptTextField = new JTextField();
        inputPanel.add(promptTextField);

        inputPanel.add(new JLabel("Model Index:"));
        indexField = new JTextField();
        inputPanel.add(indexField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
     
        JButton addPersonalBtn = new JButton("Add Personal Plan");
        JButton addProBtn = new JButton("Add Pro Plan");
        JButton enterPromptBtn = new JButton("Give Prompt");
        JButton addTeamBtn = new JButton("Add Team Member");
        JButton checkTypeBtn = new JButton("Check Plan Type");
        JButton displayBtn = new JButton("Display All");
        JButton clearBtn = new JButton("Clear Fields");

        JButton buyPromptsBtn = new JButton("Buy Prompts");
        JButton removeBtn = new JButton("Remove Model");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addPersonalBtn);
        buttonPanel.add(addProBtn);
        buttonPanel.add(enterPromptBtn);
        buttonPanel.add(addTeamBtn);
        buttonPanel.add(checkTypeBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(buyPromptsBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(exitBtn);


        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.NORTH);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        addPersonalBtn.addActionListener(e -> {
            try {
                String modelName = modelNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int parameterCount = Integer.parseInt(parameterCountField.getText());
                int contextWindow = Integer.parseInt(contextWindowField.getText());
                int prompts = Integer.parseInt(promptsSlotsField.getText());

                PersonalPlan personalPlan = new PersonalPlan(modelName, price, parameterCount, contextWindow);
                personalPlan.buyPrompts(prompts);
                modelList.add(personalPlan);
                outputArea.append("Personal Plan added: " + modelName + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Error: Please enter valid numeric values.\n");
            }
        });

        addProBtn.addActionListener(e -> {
            try {
                String modelName = modelNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int parameterCount = Integer.parseInt(parameterCountField.getText());
                int contextWindow = Integer.parseInt(contextWindowField.getText());
                int teamSlots = Integer.parseInt(promptsSlotsField.getText());

                ProPlan proPlan = new ProPlan(modelName, price, parameterCount, contextWindow);
                for (int i = 0; i < teamSlots; i++) {
                    proPlan.addTeamMember();
                }
                modelList.add(proPlan);
                outputArea.append("Pro Plan added: " + modelName + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Error: Please enter valid numeric values.\n");
            }
        });

        enterPromptBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                String prompt = promptTextField.getText();
                String result = model.enterPrompt(prompt, model.calculateTokens(prompt));
                outputArea.append("Model " + model.getModelName() + ": " + result + "\n");
            } else {
                outputArea.append("Error: Invalid model index or out of bounds.\n");
            }
        });

        addTeamBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof ProPlan) {
                    ProPlan proPlan = (ProPlan) model;
                    proPlan.addTeamMember();
                    outputArea.append("Added team member. Total slots: " + proPlan.getAvailableSlots() + "\n");
                } else {
                    outputArea.append("Error: Selected model is not a Pro Plan.\n");
                }
            } else {
                outputArea.append("Error: Invalid model index.\n");
            }
        });

        checkTypeBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    outputArea.append("Model at index " + index + " is a Personal Plan.\n");
                } else if (model instanceof ProPlan) {
                    outputArea.append("Model at index " + index + " is a Pro Plan.\n");
                }
            } else {
                outputArea.append("Error: Invalid model index.\n");
            }
        });

        displayBtn.addActionListener(e -> {
            outputArea.append("--- Current AI Models ---\n");
            for (int i = 0; i < modelList.size(); i++) {
                outputArea.append("Index " + i + ":\n" + modelList.get(i).display() + "\n");
            }
        });

        buyPromptsBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                try {
                    int prompts = Integer.parseInt(promptsSlotsField.getText());
                    AIModel model = modelList.get(index);
                    if (model instanceof PersonalPlan) {
                        PersonalPlan personalPlan = (PersonalPlan) model;
                        personalPlan.buyPrompts(prompts);
                        outputArea.append("Bought " + prompts + " prompts for model: " + personalPlan.getModelName() + "\n");
                    } else {
                        outputArea.append("Error: Selected model is not a Personal Plan.\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Error: Please enter a valid number of prompts in the Prompts field.\n");
                }
            } else {
                outputArea.append("Error: Invalid model index.\n");
            }
        });

        removeBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel removedModel = modelList.remove(index);
                outputArea.append("Removed model: " + removedModel.getModelName() + "\n");
            } else {
                outputArea.append("Error: Invalid model index.\n");
            }
        });

        clearBtn.addActionListener(e -> {
            modelNameField.setText("");
            priceField.setText("");
            parameterCountField.setText("");
            contextWindowField.setText("");
            promptsSlotsField.setText("");
            promptTextField.setText("");
            indexField.setText("");
            outputArea.setText("");
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    private int getValidIndex() {
        try {
            int index = Integer.parseInt(indexField.getText());
            if (index >= 0 && index < modelList.size()) {
                return index;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new SubscriptionGUI().setVisible(true));
    }
}
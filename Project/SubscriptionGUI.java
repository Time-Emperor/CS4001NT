
/**
 * It is the interface between user and the system.
 * It allows user with crating, displaying and interacting 
 * with Pro and Personal Plan using swing and awt
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

public class SubscriptionGUI extends JFrame {

    private ArrayList<AIModel> modelList = new ArrayList<>();

    private JTextField personalNameField, personalPriceField, personalParamsField,
            personalContextField, promptsField;

    private JTextField proNameField, proPriceField, proParamsField,
            proContextField, slotsField;

    private JTextField indexField, promptTextField, responseLengthField,
            teamMemberNameField, buyCountField;

    private JTextArea outputArea;

    public SubscriptionGUI() {
        setTitle("AI Model Subscription Management System");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // creating the main sections of the layout
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // setting up the personal plan inputs on the top left
        JPanel personalPanel = new JPanel(new BorderLayout(5, 5));
        personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Plan"));

        JPanel pFields = new JPanel(new GridLayout(5, 2, 5, 5));
        pFields.add(new JLabel("Model Name:"));
        personalNameField = new JTextField();
        pFields.add(personalNameField);

        pFields.add(new JLabel("Pricing (per 1 Lakh Tokens):"));
        personalPriceField = new JTextField();
        pFields.add(personalPriceField);

        pFields.add(new JLabel("Parameter Count (Billions):"));
        personalParamsField = new JTextField();
        pFields.add(personalParamsField);

        pFields.add(new JLabel("Context Window (Tokens):"));
        personalContextField = new JTextField();
        pFields.add(personalContextField);

        pFields.add(new JLabel("Initial Prompts Quota:"));
        promptsField = new JTextField();
        pFields.add(promptsField);

        JPanel personalBtnsArea = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton addPersonalBtn = new JButton("Add Personal Plan");
        personalBtnsArea.add(addPersonalBtn);

        JPanel bottomRowBtns = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton displayAllBtn = new JButton("Display All");
        bottomRowBtns.add(displayAllBtn);
        JButton clearBtn = new JButton("Clear");
        bottomRowBtns.add(clearBtn);
        personalBtnsArea.add(bottomRowBtns);

        personalPanel.add(pFields, BorderLayout.CENTER);
        personalPanel.add(personalBtnsArea, BorderLayout.SOUTH);

        // setting up the pro plan inputs on the top right
        JPanel proPanel = new JPanel(new BorderLayout(5, 5));
        proPanel.setBorder(BorderFactory.createTitledBorder("Pro Plan"));

        JPanel prFields = new JPanel(new GridLayout(5, 2, 5, 5));
        prFields.add(new JLabel("Model Name:"));
        proNameField = new JTextField();
        prFields.add(proNameField);

        prFields.add(new JLabel("Pricing (per 1 Lakh Tokens):"));
        proPriceField = new JTextField();
        prFields.add(proPriceField);

        prFields.add(new JLabel("Parameter Count (Billions):"));
        proParamsField = new JTextField();
        prFields.add(proParamsField);

        prFields.add(new JLabel("Context Window (Tokens):"));
        proContextField = new JTextField();
        prFields.add(proContextField);

        prFields.add(new JLabel("Initial Team Slots:"));
        slotsField = new JTextField();
        prFields.add(slotsField);

        JPanel proBtnsArea = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton addProBtn = new JButton("Add Pro Plan");
        proBtnsArea.add(addProBtn);

        JPanel teamButtonsRow = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton addTeamMemberBtn = new JButton("Add Team Member");
        teamButtonsRow.add(addTeamMemberBtn);
        JButton removeMemberBtn = new JButton("Remove Team Member");
        teamButtonsRow.add(removeMemberBtn);
        proBtnsArea.add(teamButtonsRow);

        proPanel.add(prFields, BorderLayout.CENTER);
        proPanel.add(proBtnsArea, BorderLayout.SOUTH);

        topPanel.add(personalPanel);
        topPanel.add(proPanel);

        // the bottom half contains our operations and the output text area
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        JPanel controlPanel = new JPanel(new BorderLayout(5, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Operations"));

        JPanel opFieldsPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        opFieldsPanel.add(new JLabel("Index Number:"));
        indexField = new JTextField();
        opFieldsPanel.add(indexField);

        opFieldsPanel.add(new JLabel("Prompt Text:"));
        promptTextField = new JTextField();
        opFieldsPanel.add(promptTextField);

        opFieldsPanel.add(new JLabel("Response Length (tokens):"));
        responseLengthField = new JTextField();
        opFieldsPanel.add(responseLengthField);

        opFieldsPanel.add(new JLabel("Team Member Name:"));
        teamMemberNameField = new JTextField();
        opFieldsPanel.add(teamMemberNameField);

        opFieldsPanel.add(new JLabel("Prompts to Buy:"));
        buyCountField = new JTextField();
        opFieldsPanel.add(buyCountField);

        JPanel opButtonsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        opButtonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JPanel r1 = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton givePromptBtn = new JButton("Give a Prompt");
        JButton buyPromptsBtn = new JButton("Buy Prompts");
        r1.add(givePromptBtn);
        r1.add(buyPromptsBtn);

        JPanel r2 = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton exportBtn = new JButton("Export to File");
        JButton loadBtn = new JButton("Import File");
        r2.add(exportBtn);
        r2.add(loadBtn);

        JButton checkTypeBtn = new JButton("Check Plan Type");

        opButtonsPanel.add(r1);
        opButtonsPanel.add(r2);
        opButtonsPanel.add(checkTypeBtn);

        JPanel controlWrapper = new JPanel(new BorderLayout(5, 5));
        controlWrapper.add(opFieldsPanel, BorderLayout.NORTH);
        controlWrapper.add(opButtonsPanel, BorderLayout.CENTER);

        controlPanel.add(controlWrapper, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        bottomPanel.add(controlPanel);
        bottomPanel.add(scrollPane);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        // --- attaching events to all the buttons down here ---

        addPersonalBtn.addActionListener(e -> {
            try {
                String name = personalNameField.getText();
                double price = Double.parseDouble(personalPriceField.getText());
                int params = Integer.parseInt(personalParamsField.getText());
                String window = personalContextField.getText();
                int prompts = Integer.parseInt(promptsField.getText());

                PersonalPlan plan = new PersonalPlan(name, price, params, window, prompts);
                modelList.add(plan);
                JOptionPane.showMessageDialog(this, "Personal Plan Added successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all required numeric fields (Price, Parameters, Quota) correctly.");
            }
        });

        addProBtn.addActionListener(e -> {
            try {
                String name = proNameField.getText();
                double price = Double.parseDouble(proPriceField.getText());
                int params = Integer.parseInt(proParamsField.getText());
                String window = proContextField.getText();
                int slots = Integer.parseInt(slotsField.getText());

                ProPlan plan = new ProPlan(name, price, params, window, slots);
                modelList.add(plan);
                JOptionPane.showMessageDialog(this, "Pro Plan Added successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all required numeric fields for Pro Plan correctly.");
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
            personalNameField.setText("");
            personalPriceField.setText("");
            personalParamsField.setText("");
            personalContextField.setText("");
            promptsField.setText("");
            proNameField.setText("");
            proPriceField.setText("");
            proParamsField.setText("");
            proContextField.setText("");
            slotsField.setText("");
            promptTextField.setText("");
            responseLengthField.setText("");
            teamMemberNameField.setText("");
            indexField.setText("");
            buyCountField.setText("");
        });

        givePromptBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    try {
                        String text = promptTextField.getText();
                        if (text.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Please enter prompt text.");
                            return;
                        }
                        int length = Integer.parseInt(responseLengthField.getText());
                        String result = ((PersonalPlan) model).enterPrompt(text, length);
                        outputArea.append("Model Result (Index " + index + "):\n" + result + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Please enter a valid numeric response length.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Give a Prompt is only available for Personal Plan subscriptions.");
                }
            }
        });

        buyPromptsBtn.addActionListener(e -> {
            int index = getValidIndex();
            if (index != -1) {
                AIModel model = modelList.get(index);
                if (model instanceof PersonalPlan) {
                    try {
                        int count = Integer.parseInt(buyCountField.getText());
                        String result = ((PersonalPlan) model).buyPrompts(count);
                        outputArea.append(result + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Please enter a valid number of prompts to buy in the 'Prompts to Buy' field.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Buy Prompts is only available for Personal Plan subscriptions.");
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
    }

    private void exportToFile() {
        if (modelList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No plans to export.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("subscriptions.dat"))) {
            oos.writeObject(modelList);
            JOptionPane.showMessageDialog(this, "Data exported successfully to subscriptions.dat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("subscriptions.dat"))) {
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
                        "Index falls outside the acceptable range (0 to "
                                + (modelList.size() - 1) + ").");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Input cannot be parsed as an integer. Please enter a valid number.");
            return -1;
        }
        return displayNumber;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SubscriptionGUI().setVisible(true));
    }
}
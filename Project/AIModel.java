/**
 * This file contains the code for the generic AI model with basic attributes
 * and methods. It serves as a base class for specific AI models that can be
 * implemented in the future.
 * The AIModel class includes attributes such as model name, version, and
 * parameters, as well as methods for training, predicting, and evaluating the
 * model. This structure allows for easy extension and customization of AI
 * models while maintaining a consistent interface for interaction.
 * The code is designed to be simple and easy to understand.
 */

public abstract class AIModel {
    private String modelName;
    private double price;
    private int parameterCount;
    private int contextWindow;

    public AIModel(String modelName, double price, int parameterCount, int contextWindow) {
        this.modelName = modelName;
        this.price = price;
        this.parameterCount = parameterCount;
        this.contextWindow = contextWindow;
    }

    public String getModelName() {
        return modelName;
    }

    public double getPrice() {
        return price;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public int getContextWindow() {
        return contextWindow;
    }

    public int calculateTokens(String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            return 0;
        }
        String[] tokens = prompt.split("\\s+");
        return tokens.length;
    }

    public String display() {
        return "Model Name: " + modelName +
                "\nPrice: " + price + " NPR per 1 Lakh tokens" +
                "\nParameter Count: " + parameterCount + "B" +
                "\nContext Window: " + contextWindow + "tokens";
    }

    public abstract String enterPrompt(String prompt, int tokens);
}
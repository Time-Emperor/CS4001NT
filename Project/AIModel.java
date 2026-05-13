
/**
 *This file contains the code for the generic AI model with basic attributes
 * and methods. It serves as a base class for specific AI models that can be
 * implemented in the future.
 * The AIModel class includes attributes such as model name, version, and
 * parameters, as well as methods for training, predicting, and evaluating the
 * model. This structure allows for easy extension and customization of AI
 * models while maintaining a consistent interface for interaction.
 * The code is designed to be simple and easy to understand.
 * 
 * @author (Divyamani Khawas)
 * 
 */

import java.io.Serializable;

public abstract class AIModel implements Serializable {
    private String modelName;
    private double price;
    private int parameterCount;
    private String contextWindow;

    public AIModel(String modelName, double price, int parameterCount, String contextWindow) {
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

    public String getContextWindow() {
        return contextWindow;
    }

    public String display() {
        return "Model Name: " + modelName +
                "\nPrice: " + price + " NPR per 1 Lakh tokens" +
                "\nParameter Count: " + parameterCount + "B" +
                "\nContext Window: " + contextWindow + " tokens";
    }

    public abstract String enterPrompt(String prompt, int tokens);
}
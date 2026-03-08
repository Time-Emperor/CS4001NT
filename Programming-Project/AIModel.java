public abstract class AIModel {

    // Attributes
    private String modelName;     // Name of the AI model
    private double price;        // Price in NPR per 1 Lakh tokens
    private int parameterCount;  // Parameters in billions
    private int contextWindow;   // Max tokens per request

    private static final int SYSTEM_TOKENS = 50;

    /**
     * Constructor
     */
    public AIModel(String modelName, double price, int parameterCount, int contextWindow) {
        this.modelName = modelName;
        this.price = price;
        this.parameterCount = parameterCount;
        this.contextWindow = contextWindow;
    }

    // Getters
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

    /**
     * Core token calculation logic.
     */
    public boolean calculateTokenUsage() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Enter prompt text:");
        String promptText = scanner.nextLine();

        // Very simple estimation: 1 token per 4 characters
        int inputTokens = Math.max(1, promptText.length() / 4);

        System.out.print("Enter expected output tokens: ");
        int outputTokens;
        try {
            outputTokens = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Treating output tokens as 0.");
            outputTokens = 0;
        }

        int totalTokens = inputTokens + outputTokens + SYSTEM_TOKENS;

        System.out.println("---- Token Usage Summary ----");
        System.out.println("Model: " + modelName);
        System.out.println("Input tokens (estimated): " + inputTokens);
        System.out.println("Output tokens: " + outputTokens);
        System.out.println("System tokens: " + SYSTEM_TOKENS);
        System.out.println("Total tokens: " + totalTokens);
        System.out.println("Context window: " + contextWindow);

        return totalTokens <= contextWindow;
    }

    /**
     * Abstract
     */
    public abstract String display();
}


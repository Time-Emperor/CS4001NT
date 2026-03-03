import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initializing objects for PersonalPlan and ProPlan
        PersonalPlan personal = new PersonalPlan(
                "Personal-GPT", 1000.0, 10, 2000, 5  // small quota (5) for demo
        );

        ProPlan pro = new ProPlan(
                "Pro-GPT", 5000.0, 50, 2000, 3
        );

        System.out.println("=== Initial Plans ===");
        System.out.println(personal.display());
        System.out.println();
        System.out.println(pro.display());
        System.out.println();

        // 2.PersonalPlan logic: exhaust monthly quota
        System.out.println("=== Testing PersonalPlan quota exhaustion ===");
        while (true) {
            System.out.println("Using PersonalPlan prompt...");
            String result = personal.usePrompt();
            System.out.println(result);
            if ("Monthly quota exhausted".equals(result)) {
                break;
            }
            System.out.println();
        }

        // Purchasing more prompts
        System.out.println();
        System.out.println("Purchasing 2 more prompts for PersonalPlan...");
        System.out.println(personal.purchasePrompts(2));
        System.out.println(personal.display());
        System.out.println();

        // 3. Context Limits:
        System.out.println("=== Testing Context Limits (PersonalPlan) ===");
        System.out.println("Now enter a very long prompt so that (Input + Output + System) > contextWindow.");
        System.out.println("This should cause: Context limit exceeded");
        String personalContextResult = personal.usePrompt();
        System.out.println("Result: " + personalContextResult);
        System.out.println();

        System.out.println("=== Testing Context Limits (ProPlan) ===");
        System.out.println("Now enter another very long prompt for ProPlan.");
        String proContextResult = pro.usePrompt();
        System.out.println("Result: " + proContextResult);
        System.out.println();

        // 4. ProPlan:
        System.out.println("=== Testing ProPlan with valid context ===");
        System.out.println("Enter a short prompt for ProPlan to stay within context window.");
        String proValidResult = pro.usePrompt();
        System.out.println("Result: " + proValidResult);

        System.out.println();
        System.out.println("=== Demo Complete ===");
        scanner.close();
    }
}


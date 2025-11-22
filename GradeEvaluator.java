import java.util.Scanner;
public class GradeEvaluator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the grade:");
        double grade=sc.nextDouble();
        String store=(grade>=40) ? "pass" : "fail";
        System.out.println(store);
    }
}

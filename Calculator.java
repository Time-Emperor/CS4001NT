import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args){
        Scanner dx= new Scanner(System.in); //call in method from library
        System.out.println("Enter first number:");
        int store=dx.nextInt();//stores numbers
        System.out.println("First number is: "+store);
            
        System.out.println("Enter second number:");//call in method from library
        int store1=dx.nextInt();//stores number
        System.out.println("Second number is: "+store);
        
        dx.close();
    }
}
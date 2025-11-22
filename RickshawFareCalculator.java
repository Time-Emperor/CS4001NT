import java.util.Scanner;

public class RickshawFareCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Distance (km): ");
        double distance = sc.nextDouble();

        System.out.print("Time (minutes): ");
        double time = sc.nextDouble();

        System.out.print("Local? (yes/no): ");
        boolean local = sc.next().equalsIgnoreCase("yes");

        System.out.print("Night? (yes/no): ");
        boolean night = sc.next().equalsIgnoreCase("yes");

        double fare = 100 + 20*distance + 2*time;

        if(local && distance > 20) fare *= 1;
        if(night) fare *= 2;

        System.out.println("Final Fare: Rs. " + (int)fare);
    }
}
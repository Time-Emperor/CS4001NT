public class MathOperations {
    public static void main(String[] args) {
       int a= 10;
       int b= 4;
       
       // arthemethic operators
        System.out.println("adiition: " +(a+b));
        System.out.println("subtraction: " +(a-b));
        System.out.println("multiplication: " +(a*b));
        System.out.println("divisition: " +(a/b));
        System.out.println("modulus:" +(a%b));
        System.out.println("increment:" +(a++));
        System.out.println("decrement:" +(b--));

        //relational operators
        System.out.println("equal to: " +(a==b));
        System.out.println("greater than: " +(a>b));
        System.out.println("less than: " +(a<b));
        System.out.println("greaterthan and equal to: " +(a>=b));
        System.out.println("less than and equal to:" +(a<=b));
        System.out.println("not equal to" +(a!=b));

        //logical operators
        boolean x=true;
        boolean y=false;

        System.out.println("and:" +(x&&y));
        System.out.println("or:" +(x||y));
        System.out.println("not:" +(!x));

        //assignment operators
        int c=15;

        System.out.println("=" +c);
        System.out.println("+=" +c);
        System.out.println("-=" +c);
        System.out.println("*=" +c);
        System.out.println("/=" +c);
        System.out.println("%=" +c);

        //ternary operators
        var num = 7;
        var type = (num % 2 == 0) ? "Even" : "Odd";
        System.out.println(num + " is " + type);

        //bitwise operators
        int d=4;
        int e=5;

        System.out.println("and:" +(d&e));
        System.out.println("or:" +(d|e));
        System.out.println("xor:" +(d^e));
    }
}

public class WaterLevelMonitor
{
    public static void main(String[] args){
        int waterlevel1=950;
        System.out.println("Current water level is: " + waterlevel1 + "liters");
        String Message = (waterlevel1 >= 1000) ? "WARNING: Water level has reached 1000l or more!" : "Status: Normal";
        System.out.println(Message);
        int waterlevel2=1000;
        System.out.println("Current water level is: " + waterlevel2 + "liters");
        String Message2 = (waterlevel2 >= 1000) ? "WARNING: Water level has reached 1000l or more!" : "Status: Normal";
        System.out.println(Message2);
        int waterlevel3=1200;
        System.out.println("Current water level is: " + waterlevel3 + "liters");
        String Message3 = (waterlevel3 >= 1000) ? "WARNING: Water level has reached 1000l or more!" : "Status: Normal";
        System.out.println(Message3);
    }
}
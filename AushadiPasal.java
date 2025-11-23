public class AushadiPasal{
    public static void main(String[] args){
        String englishName= "Sinex";
        String nepaliName= "साइनिक्स";
        double price= 10.0; // price per tablet
        int stock =120; //quantity in stock
        boolean prescription = false; // to check if it is required or not
        System.out.println("========= Aushadhi Pasal Inventory Report =======\n");
        
        System.out.println(" English Name       : " + englishName);
        System.out.println(" Nepali Name        : " + nepaliName);
        System.out.println(" Price per table    : NRP " + price);
        System.out.println(" Quantity in stock  : " + stock);
        System.out.println(" Prescription Req?  : " + (prescription ? "Yes" : "No"));
    }
}
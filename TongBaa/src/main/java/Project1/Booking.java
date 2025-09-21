package Project1;

/**
 *
 * @author thanakrit & purinut
 */
import java.util.*;
import java.io.*;

public class Booking 
{
    private List<Item> items;
    private List<Discount> discounts;
    private List<CustomerTotal> customerSum;
    
    public Booking() {
        discounts = new ArrayList<Discount>();
        customerSum = new ArrayList<CustomerTotal>();
        items = ItemMain.readItem("items.txt");
        Discount.input2("discounts.txt", discounts); 
    }
    
    public void bookingProcess() {
        int lineNum = 1;
        String info[];
        double totalRoomPrice = 0, totalMeal = 0, subtotal = 0, discount = 0, totalAmount = 0;
        Scanner scan;
        
        scan = getValidFileScanner();
        System.out.println("\n===== Booking Processing =====");
        while(true) {
            if((info = readBookingInput(lineNum, scan)) == null) break;
            totalRoomPrice = calculateTotalRoom(info[3], info[2]);
            totalMeal = calculateTotalMeal(info[5], info[2], info[4]);
            subtotal = totalRoomPrice + totalMeal;
            discount = calculateDiscount(subtotal);
            totalAmount = printBookingProcess(info, totalRoomPrice, totalMeal, discount);
           // customerSum.add(new CustomerTotal(info[1], totalAmount));
           customerSum.add(new CustomerTotal(info[1], subtotal));
            lineNum++;
        }
    }
    
    private Scanner getValidFileScanner() {
        String currentPath = "src/main/java/Project1/";
        Scanner input = new Scanner(System.in);
        Scanner scan = null;
        File bookingFile = null;

        while (true) {
            try {
                if (bookingFile == null) {
                    bookingFile = new File(currentPath + "bookings.txt");
                } else {
                    System.out.println("Enter correct file name =");
                    String fileName = input.next();
                    bookingFile = new File(currentPath + fileName);
                }
                scan = new Scanner(bookingFile);
                break; 
            } catch (Exception e) {
                System.out.println("\n" + e);
            }
        }
        return scan;
    }

    public String[] readBookingInput(int count, Scanner fileScan) {
        String wholeLine = null;
        String[] col = null;

        // get scanner for valid file
        try {
            if(count == 1) {
                fileScan.nextLine();
            }
            
            // has next line?
            if (!(fileScan.hasNext())) return null;

            wholeLine = fileScan.nextLine();
            col = wholeLine.split(",");
            // Trim spaces
            for (int i = 0; i < col.length; i++) {
                col[i] = col[i].trim();
            }
        } catch (Exception e) {
            System.out.println("\n" + e);
            System.out.println(wholeLine);
        }

        return col;
    }
    
    public double calculateTotalRoom(String room, String days) {
        String numberRoom[] = room.split(":");
        double amountRoom[] = new double[3];
        double totalRoomPrice = 0;
        double amountDay = Double.parseDouble(days);

                
        for(int i = 0; i < numberRoom.length; i++){
            amountRoom[i] = Double.parseDouble(numberRoom[i]);
            totalRoomPrice += amountRoom[i] * (items.get(i)).getRoom() * amountDay;
        }
        
        return totalRoomPrice;
    }
    
    public double calculateTotalMeal(String meal, String days, String person) {
        String numberMeal[] = meal.split(":");
        double amountMeal[] = new double[3];
        double totalMealPrice = 0;
        double amountDay = Double.parseDouble(days);
        double amountPerson = Double.parseDouble(person);
                
        for(int i = 0; i < numberMeal.length; i++){
            amountMeal[i] = Double.parseDouble(numberMeal[i]);
            totalMealPrice += amountMeal[i] * (items.get(i + 3)).getMeal() * amountDay * amountPerson;
        }
        
        return totalMealPrice;
    }
    
    public double calculateDiscount(double subtotal){
        Double percentDiscount, amountDiscount;
        HandleDiscountCriteria handleDiscount = new HandleDiscountCriteria(discounts);
        percentDiscount = handleDiscount.getPercentDiscount(subtotal);
        amountDiscount = (percentDiscount / 100) * subtotal;
        return amountDiscount;
    }
    
    public double printBookingProcess(String info[], double totalRoom, double totalMeal, double discount) {
        String room[] = info[3].split(":");
        String meal[] = info[5].split(":");
        String formatRoom = String.format("[%s, %s, %s]", room[0], room[1], room[2]);
        String formatMeal = String.format("[%s, %s, %s]", meal[0], meal[1], meal[2]);
        double subtotal = totalRoom + totalMeal;
        
        String bookingLine = String.format("Booking%4s, customer%4s  >>  days =%4s,  persons =%4s,  rooms = %s,  meals = %s",
                                            info[0], info[1], info[2], info[4], formatRoom, formatMeal);    
        
        System.out.println(bookingLine);
        System.out.printf("%13s%-20s =%,14.2f\n", " ", "total room price++", totalRoom);
        System.out.printf("%13s%-20s =%,14.2f\n", " ", "total meal price", totalMeal);
        System.out.printf("%13s%-20s =%,14.2f\n", " ", "subtotal", subtotal);
        System.out.printf("%13s%-20s =%,14.2f\n", " ", "discount", discount);
        System.out.printf("%13s%-20s =%,14.2f\n", " ", "total", (subtotal-discount));
        return subtotal-discount;
    }
    
    public List<CustomerTotal> getCustomerSummary() {
        return customerSum;
    }
}

package Project1;


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
    
    public void bookingProcess() throws InvalidFormatException {
        int lineNum = 1;
        String info[];
        double totalRoomPrice = 0, totalMeal = 0, subtotal = 0, discount = 0, totalAmount = 0;
        
        Scanner scan;
        scan = getValidFileScanner();
        boolean  headerPrinted = false;
        
        while(true) {
            if((info = readBookingInput(lineNum, scan)) == null) break;
             
            try
            {
            
                if (info.length != 6) {
            
                throw new NumberFormatException("For input string: \"" + info[4] + "\"");
                }
                
            
            totalRoomPrice = calculateTotalRoom(info[3], info[2]);
            totalMeal = calculateTotalMeal(info[5], info[2], info[4]);
            subtotal = totalRoomPrice + totalMeal;
            discount = calculateDiscount(subtotal);
            
            if (!headerPrinted) {
            System.out.println("\n===== Booking Processing =====");
            headerPrinted = true;
}
            totalAmount = printBookingProcess(info, totalRoomPrice, totalMeal, discount);//=================
            // customerSum.add(new CustomerTotal(info[1], totalAmount));
            customerSum.add(new CustomerTotal(info[1], subtotal, info[0]));
            
            }
            catch (InvalidFormatException e)
            {
                System.err.println("Project1.InvalidFormatException: " + e.getMessage());
                System.err.printf("%s, %8s, %8s, %9s, %16s,  %10s     skip\n",
                info[0], info[1], info[2], info[3], info[4], info[5]);
                lineNum++;
                continue;
            }
            catch (InvalidInputException e)
            {
                System.err.println("Project1.InvalidInputException: " + e.getMessage());
                System.err.printf("%s, %8s, %8s, %9s, %16s,  %10s     skip\n",
                info[0], info[1], info[2], info[3], info[4], info[5]);
                lineNum++;
                continue;
            }
            catch (Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    
            if (info.length != 6) {
    
                String[] newInfo = new String[6];   
                System.arraycopy(info, 0, newInfo, 0, 4);
                newInfo[4] = "   ";
                newInfo[5] = info[4];
                info = newInfo;
                System.err.printf("%s, %8s, %8s, %9s, %16s   %10s     skip\n",
                info[0], info[1], info[2], info[3], info[4], info[5]);
                lineNum++;
                continue;
                    }
            
                System.err.printf("%s, %8s, %8s, %9s, %16s,  %10s     skip\n",
                info[0], info[1], info[2], info[3], info[4], info[5]);
                lineNum++;
                continue;
            }
        } 
        
    }
    
    private Scanner getValidFileScanner() {
        String currentPath = "src/main/java/Project1/";
        Scanner input = new Scanner(System.in);
        Scanner scan = null;
        File bookingFile = null;
        //String readfrom = currentPath + filename;

        while (true) {
            try {
                
                
                if (bookingFile == null) {
                    bookingFile = new File(currentPath + "bookings_errors.txt");
                    String readfrom = currentPath + "bookings_errors.txt";
                    System.out.println();
                    System.out.println("Read from " + readfrom);
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
    
    public double calculateTotalRoom(String room, String days) 
            throws InvalidFormatException, InvalidInputException {
        
        String numberRoom[] = room.split(":");
        double amountRoom[] = new double[3];
        double totalRoomPrice = 0;
        double amountDay;
        try
        {
            amountDay = Double.parseDouble(days);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidFormatException("For days: \"" + days + "\"");
        }
        if (amountDay < 0) throw new InvalidInputException("For days: \"" + days + "\"");
        if (amountDay < 1) throw new NumberFormatException("For input string: \"" + days + "\"");
        
        
        if (numberRoom.length != 3) throw new InvalidFormatException("For rooms: \"" + room + "\"");       
        for(int i = 0; i < numberRoom.length; i++){
            
            try
            {
                amountRoom[i] = Double.parseDouble(numberRoom[i]);
            }
            catch(NumberFormatException e) 
            {
                throw new InvalidFormatException("For rooms: \"" + room + "\"");
            }
            if (amountRoom[i] < 0) {
            throw new InvalidInputException("For rooms: \"" + room + "\"");
            }
            
            totalRoomPrice += amountRoom[i] * (items.get(i)).getRoom() * amountDay;
        }
        
        return totalRoomPrice;
    }
    
    public double calculateTotalMeal(String meal, String days, String person) 
            throws InvalidFormatException, InvalidInputException {
        String numberMeal[] = meal.split(":");
        double amountMeal[] = new double[3];
        double totalMealPrice = 0;
        double amountDay;
        try
        {
            amountDay = Double.parseDouble(days);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidFormatException("For days: \"" + days + "\"");
        }
        if (amountDay < 1) throw new InvalidInputException("For days: \"" + days + "\"");
        
        int amountPerson; 
        try
        {
            amountPerson = Integer.parseInt(person);
        }
        catch (NumberFormatException e) {
        throw new NumberFormatException("For input string: \"" + person + "\"");
}
        
     
        if (numberMeal.length != 3) throw new InvalidFormatException("For meals: \"" + meal + "\"");

        for(int i = 0; i < numberMeal.length; i++){
            
            try
            {
                amountMeal[i] = Double.parseDouble(numberMeal[i]);
            }
               catch (NumberFormatException e)
                    {
                        throw new InvalidFormatException("For meals: \"" + meal + "\"");
                    }
               if (amountMeal[i] < 0)
               {
                   throw new InvalidInputException("For meals: \"" + meal + "\"");
               }
             
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
        System.out.println();
        return subtotal-discount;
    }
    
    public List<CustomerTotal> getCustomerSummary() {
        return customerSum;
    }
    
    public class InvalidFormatException extends Exception {
        public InvalidFormatException(String message)
    {
        super(message);
    }
}
    public class InvalidInputException extends Exception {
        public InvalidInputException (String message)
    {
        super(message);
    }  
}
    
    
}





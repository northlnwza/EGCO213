package Project1;

/**
 *
 * @author phurinutwongwatcharapaiboon
 */
import java.util.*;
import java.io.*;

public class bookingMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<CustomerTotal> customerSum; // ของ North
        Booking test = new Booking();
        test.bookingProcess();
        customerSum = test.getCustomerSummary();
        
        /*
        for(CustomerTotal ct: customerSum) {
            System.out.println(ct.getName());
            System.out.println(ct.getTotal());   
            count++;
        }
        
        System.out.println(count);
        */
    }
    
}

package Project1;

/**
 *
 * @author phurinutwongwatcharapaiboon
 */

public class CustomerTotal{
    public String customerName;
    public double totalAmount;
    
    public CustomerTotal(String customerName, double totalAmount) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }
    
    public String getName() {
        return customerName;
    }
    
    public double getTotal() {
        return totalAmount;
    }
}

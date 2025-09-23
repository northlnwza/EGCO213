
package Project1;

public class CustomerTotal{
    public String customerName;
    public String bid;
    public  String a_bid;
    public double totalAmount;
    
    public CustomerTotal(String customerName, double totalAmount, String b) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.bid = b;
    }
    
    public String getName() {
        return customerName;
    }
    
    public double getTotal() {
        return totalAmount;
    }
    public String getBid() {
        return bid;
    }
    public double newtp(double tp) {
        totalAmount = tp;
        return totalAmount;
    }
    public  String getAbid(String b)
    {
        a_bid = b;
        return a_bid;
    }
}

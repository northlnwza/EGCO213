package Project1;

/**
 *
 * @author NP
 */
public class Discount {
    private int subtotal;
    private double percent;
    
    public Discount(int subtotal, double percent)
    {
        this.subtotal = subtotal;
        this.percent = percent;
    }
    
  public int getSubtotal() {return subtotal;}
    public double getPercent() {return percent;}
    
    public void getDiscount()
    {
        System.out.println(String.format("If total bill >= %,10d   discount = %5.1f%%",
                getSubtotal(), getPercent()));
    }
     
}
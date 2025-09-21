package Project1;

import java.util.*;
import java.io.*;

/**
 *
 * @author NP
 */
public class HandleDiscountCriteria {
    public List<Discount> discounts;
    
    public HandleDiscountCriteria(List<Discount> discounts)
    {
        this.discounts = discounts;
    }
    
    public double getPercentDiscount(double subtotal)
    {
        double result = 0.0;
        for (Discount d : discounts)
        {
            if (subtotal >= d.getSubtotal())
            {
                result = d.getPercent();
            }
        }
        return result;
    }
    
}

package Project1;

import java.io.*;
import java.util.*;

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
    public void input2(String filename, List<Discount> discounts)
    {
        
        String path = "src/main/Java/Project1/";
        File infile = new File(path + filename);
        String readfrom = path + filename;
        Scanner scanfile;
        Scanner scannewfilename = new Scanner(System.in);
        boolean canfind = false;
        String[] cols;
        String line;
           
        while(!canfind)
        try
        {
            scanfile = new Scanner(infile);
            canfind = true;
            System.out.println("Read from " + readfrom);
            System.out.println();
            cols = new String[2];
            
            if(scanfile.hasNextLine()) scanfile.nextLine(); //header
            
            
            while(scanfile.hasNext())
            {
                line = scanfile.nextLine();
                cols = line.split(",");
                
                int bill = Integer.parseInt(cols[0].trim());
                Double percent = Double.parseDouble(cols[1].trim());
                Discount d = new Discount(bill, percent);
                discounts.add(d);
                d.getDiscount(); 
            }
            
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex);
            System.out.println("Enter correct file name = ");
            filename = scannewfilename.next();
            System.out.println();
            infile = new File(path + filename);
        }
    }
    
    
}


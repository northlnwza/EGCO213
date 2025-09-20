package Project1;

import java.io.*;
import java.util.*;

/**
 *
 * @author NP
 */
public class Main {
    public static void main (String[] args)
    {
        //obj.input("item.xtxtx")
        //obj.input("discounts.xtxtx")
        //obj.input("bookings_errors.xtxtx")
    }
    
    //@Override for item.txt
    public void input(String filename)
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
            cols = new String[4];
            
            if(scanfile.hasNextLine()) scanfile.nextLine(); //header
            
            
            while(scanfile.hasNext())
            {
                line = scanfile.nextLine();
                cols = line.split(",");
                
                String code = cols[0].trim();
                String name = cols[1].trim();
                double unitprice = Double.parseDouble(cols[2].trim());
                
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
    
    
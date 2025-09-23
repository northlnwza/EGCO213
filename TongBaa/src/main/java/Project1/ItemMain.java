
package Project1;

import java.util.*;
import java.io.*;

public class ItemMain {

//    public static void main(String[] args) {
//        
//        List<Item> items = readItem("items.txt");
//        //readDiscount("discounts.txt");
//        
//    }
    
    static List<Item> readItem(String filename){
        
        List<Item> items = new ArrayList<>();
        String path = "src/main/Java/Project1/";
        File infile = new File(path + filename);
        
        Scanner scanfile;
        Scanner scannewfilename = new Scanner(System.in);
        boolean canfind = false;
        String[] cols;
        String line;
        
        
        
        while(!canfind)
        {
          
        try {
            String readfrom = path + filename;
            scanfile = new Scanner(infile);
            canfind = true;
            System.out.println("Read from " + readfrom);
            System.out.println();
            cols = new String[3];
            if(scanfile.hasNextLine()) scanfile.nextLine(); //header
            
            

            while(scanfile.hasNext()) {
                line = scanfile.nextLine();
                cols = line.split(","); 
                
                String code = cols[0].trim();
                String name = cols[1].trim();
                double price = Double.parseDouble( cols[2].trim() );
                Item item = new Item(code,name,price);
                items.add(item);
            }
        for (int i=0;i<3;i++){
            Item item = items.get(i);
            item.printRoom();
        } System.out.println();   
        for (int i=3;i<6;i++){
            Item item = items.get(i);
            item.printMeal();
        } System.out.println();
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
        return items;
    }
    /*
    static void readDiscount(String filename){

        //String path = "src/main/java/Project1/";
        String path = "src/Project1/";
        File file = new File(path+filename);
        System.out.println("Read from "+path+filename);
        try ( Scanner iscan = new Scanner(file) ) {
            iscan.nextLine();                                   //skip first line
            while(iscan.hasNext()) {
                String line = iscan.nextLine(); 
                String []cols = line.split(","); 
                String code = cols[0].trim();
                String name = cols[1].trim();
                 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
    

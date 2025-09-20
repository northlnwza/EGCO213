
package Project1;

import java.util.*;
import java.io.*;

public class ItemMain {

    public static void main(String[] args) {
        
        List<Item> items = readItem("items.txt");
        //readDiscount("discounts.txt");
        
    }
    
    static List<Item> readItem(String filename){

        //String path = "src/main/java/Project1/";
        String path = "src/Project1/";
        File file = new File(path+filename);
        System.out.println("Read from "+path+filename);
        List<Item> items = new ArrayList<>();
        try ( Scanner iscan = new Scanner(file) ) {
            iscan.nextLine();                                   //skip first line
            while(iscan.hasNext()) {
                String line = iscan.nextLine(); 
                String []cols = line.split(","); 
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
        catch (Exception e) {
            e.printStackTrace();
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

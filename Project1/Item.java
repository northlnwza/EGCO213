
package Project1;

interface Room{
    double totalRoom();
    void printRoom();
    double getRoom();
}
interface Meal{
    void printMeal();
    double getMeal();
}

public class Item implements Room, Meal{
    
    String code, name;
    double price;
    
    public Item(String c, String n, double p){
        code  = c;
        name  = n;
        price = p;
    }
    @Override   
    public double totalRoom(){
        price = price*(1.1)*(1.07);
        return price;
    }
    @Override
    public void printRoom(){
        System.out.printf("%S, %-22s rate (per day) = %,9.2f    ",code,name,price);
        totalRoom();
        System.out.printf("rate++ = %,9.2f\n",price);
    }
    @Override
    public double getRoom(){
        return price;
    }
    @Override
    public void printMeal(){
        System.out.printf("%S, %-15s rate (per person per day) = %6.2f\n",code,name,price);
    }
    @Override
    public double getMeal(){
        return price;
    }
}




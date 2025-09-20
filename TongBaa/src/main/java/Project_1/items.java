package Project1;

/**
 *
 * @author NP
 */

public abstract class Item {
    private String code;
    private String name;
    private double unitprice;
    
    public Item (String code, String name, double unitprice)
    {
        this.code = code;
        this.name = name;
        this.unitprice = unitprice;
       
    }
    
    abstract public void getItem();
    public String getcode() {return code;}
    public String getname() {return name;}
    public double getunitprice() {return unitprice;}
       
}

class Room extends Item {
    
    public Room (String code, String name, double unitprice){
        super(code, name, unitprice);
    }
    
    public double getCharge(double unitprice) {
        return ((1.1 *unitprice) * 1.07);
    }
    
    @Override
    public void getItem()
    {
        System.out.println(String.format("%s, %-22s rate (per day) = %-,9.2f     rate++ = %-,9.2f"
                , getcode(), getname(), getunitprice(), getCharge(getunitprice())));
    }
}

class Meal extends Item {
    public Meal(String code, String name, double unitprice)
    {
        super(code, name,unitprice);
    }
    @Override
    public void getItem()
    {
        System.out.println(String.format("%s, %-22s rate (per day) = %-,9.2f"
                , getcode(), getname(), getunitprice()));
    }
    
}

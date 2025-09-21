package Project1;

import static Project1.ItemMain.readItem;
import java.io.*;
import java.util.*;
/**
 *
 * @thanakrit 6713118 
 */


class CBooking
{
	private	String	B_id;
	private	String	C_id;
	private	double	totalprice;

	public String getbid()
	{
		return (this.B_id);
	}
	public String getcid()
	{
		return (this.C_id);
	}
	public double gettp()
	{
		return (this.totalprice);
	}
        public  double newtp(double tp)
        {
                this.totalprice = tp;
                return (this.totalprice);
        }
	public	CBooking(String b, String c, double p)
	{
		this.B_id = b;
		this.C_id = c;
		this.totalprice = p;
	}
}

class   Record implements Comparable<Record>
{
	private String  cid;
	private double  ta;
	private String  bookings;

	public	int compareTo(Record other)
	{
		if (this.ta < other.ta)
			return (1);
		else if (this.ta > other.ta)
			return (-1);
		return (0);
	}
	public	Record(String c, double t, String b)
	{
		this.cid=  c;
		this.ta=  t;
		this.bookings = b;
	}
	public String getcid()
	{
		return (this.cid);
	}
	public double getta()
	{
		return (this.ta);
	}
	public String getb()
	{
		return (this.bookings);
	}
}
class Customer
{
	public	ArrayList<CBooking> BL_file;
	public	ArrayList<String> cid;
	public	ArrayList<Record> rec;
        

	public	void summary()
	{
		int	i;
		
		this.getcus();
		Collections.sort(this.rec);
		i = 0;	
		System.out.printf("\n===== Customer Summary =====\n");
		while (i < this.rec.size())
		{
			System.out.printf("%-3s  >>  total amount =  %,12.2f    bookings = %s\n",this.rec.get(i).getcid(), this.rec.get(i).getta(), this.rec.get(i).getb());
			i++;
		}
	}
	public	void getcus()
	{
		int	i;
		int	j;
		int	count;
                ArrayList<CustomerTotal> sumcus;
		CBooking	obj2;

		this.readcid("bookings.txt");
		this.cid = new ArrayList<String>();
		this.rec = new ArrayList<Record>();
                sumcus = new ArrayList<CustomerTotal>();
                sumcus = gettotalamount();
		for (CBooking obj1 : this.BL_file)
		{
			if (!this.cid.contains(obj1.getcid()))
			{
				String	gcid;
				double	t_a;	
				StringBuilder	bk;

				gcid = "";
				t_a = 0;
        			this.cid.add(obj1.getcid());
				bk = new StringBuilder();
				bk.append("[");
                                int k;
                                k = 0;
                                while (k < sumcus.size())
                                {
                                    if (sumcus.get(k).getName().equals(obj1.getcid()))
                                    {
					gcid = obj1.getcid();	
					t_a = obj1.newtp(sumcus.get(k).getTotal());
                                    }
                                    k++;
                                }
				j = 0;
				count = 0;
				while (j < this.BL_file.size())
				{
					obj2 = this.BL_file.get(j);
					if ((obj2.getcid()).equals(obj1.getcid()))
						count++;	
					j++;	
				}
				i = 0;
				j = 0;
				while (i < this.BL_file.size())
				{
					obj2 = this.BL_file.get(i);
					if ((obj2.getcid()).equals(obj1.getcid()))
					{
						bk.append(obj2.getbid());
						if (j + 1 < count)
						{
							bk.append(" , ");
						}
						j++;
					}
					i++;
				}
				bk.append("]");
				this.rec.add(new Record(gcid, t_a, bk.toString()));
			}
		}
	}

	public void readcid(String filename)
	{
		Scanner	scan;
		Scanner keyboardScan;
		CBooking	b;
		String	path;
		String	cols[];
		String	line;
		boolean	opensuccess;
		int	i;

		opensuccess = false;
		while (!opensuccess)
		{
			try
			{
				path = "src/main/Java/Project1/";
				scan = new Scanner(new File(path + filename));
				opensuccess = true;
				i = 0;
				this.BL_file = new ArrayList<CBooking>();
				cols = new String[6];
				while (scan.hasNext())
				{
					line = scan.nextLine();		
					if (i != 0)
					{
						//play some try catch input here
						cols = line.split(",");
						b = new CBooking(cols[0].trim(), cols[1].trim(), 0);
						(this.BL_file).add(b);
					}
					i++;
				}
			}
			catch (FileNotFoundException e)
			{
				keyboardScan = new Scanner(System.in);
				System.out.print(e + " --> ");
                        	System.out.println("New file name = ");
                        	filename = keyboardScan.next();
			}
		}
	}
        public ArrayList<CustomerTotal> gettotalamount()
        {
                List<CustomerTotal> customerSum;
                
                Booking test = new Booking();
                test.bookingProcess();
                customerSum = test.getCustomerSummary();
                int i;
                int j;
                i = 0;
                ArrayList<CustomerTotal>   sumcus;
                ArrayList<String>   cid;
                sumcus = new ArrayList<CustomerTotal>();
                cid = new ArrayList<String>();
                double  sum;
                while (i < customerSum.size())
                {
                    j = 0;
                    sum = 0;
                    
                    while (j < customerSum.size())
                    {
                           if (customerSum.get(i).getName().equals(customerSum.get(j).getName()))
                           {
                              sum = sum + customerSum.get(j).getTotal();
                              
                           }
                       j++;
                    }
                    if(!cid.contains(customerSum.get(i).getName()))
                    {
                        sumcus.add(new CustomerTotal(customerSum.get(i).getName(), sum));
                        cid.add(customerSum.get(i).getName());
                    }
                    //System.out.printf("%s %s\n",customerSum.get(i).getName(), customerSum.get(i).getTotal());
                    i++;
                }
                //System.out.printf("-------\n");
                i = 0;
                while (i < sumcus.size())
                {
                   //System.out.printf("%s %.2f\n", sumcus.get(i).getName(), sumcus.get(i).getTotal());
                    i++;
                }
                return (sumcus);
        }
}

public class Main 
{
	public	static void main(String[] args)
	{
		Customer	C;
                
		C = new Customer();
		C.summary();
	}
    
}

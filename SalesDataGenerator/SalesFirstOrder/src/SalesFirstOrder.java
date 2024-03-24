import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class SalesFirstOrder {
	private static Random random = new Random();
	private static int seed = 0;
	
	private static int item_randomizer = 148;
	private static int fname_randomizer = 200;
	private static int lname_randomizer = 100;
	private static int mobile_randomizer = 99349978;
	private static int shipment_randomizer = 350000;
	private static int shipment_randomizer_offset = 340000;
	
	private static int branch_count = 25;
	private static int employee_count = 400;
	private static int customer_count = 1000;
	private static int dept_count = 5;
	private static int inventory_count = 10000;
	
	private static int min_salary = 10000;
	private static int max_salary = 40000;
	
	private static LocalDate startDate = LocalDate.of(2022, 1, 1); // Define start date
	private static LocalDate endDate = LocalDate.of(2024, 12, 31); // Define end date

	private static ArrayList<String> fname_list;
	private static ArrayList<String> lname_list;
	private static Map<String, Float> item_dict = new HashMap<>();
	private static ArrayList<String> item_list;
	
	private static String[] dvns= {"BLG", "CHN", "BOM", "HYD", "KOL", "DEL", "NAG"};
	private static String[] phone_prefix = {"98", "97", "88", "89", "70", "99", "89"};
	
	
	public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
	
	private static ArrayList<String> fileToList(String filename) throws IOException
	{
		ArrayList<String> list = new ArrayList<>();
		BufferedReader fr = new BufferedReader(new FileReader(filename));
		String line = fr.readLine(); 
		while (line != null)
		{ 
			list.add(line); 
			line = fr.readLine(); 
		}
		fr.close();
		return list;
	}

	
	private static ArrayList<String> fileToDict(String filename) throws IOException
	{
		ArrayList<String> list = new ArrayList<>();
		BufferedReader fr = new BufferedReader(new FileReader(filename));
		String line = fr.readLine(); 
		String[] key_value;
		while (line != null)
		{ 
			key_value = line.split(",");
			item_dict.put(key_value[0], Float.parseFloat(key_value[1]));
			list.add(key_value[0]);
			line = fr.readLine(); 
		}
		fr.close();
		return list;
	}
	
	
	private static void customerTable() throws IOException
	{
		//Customer Table	
		for(int i=0; i<=customer_count; i++) 
		{
			String customer_fname = fname_list.get(random.nextInt(fname_randomizer));
			String customer_lname = lname_list.get(random.nextInt(lname_randomizer));
			String customer_dvns = dvns[random.nextInt(7)];
			String customer_id = String.format("%06d", i);
			String customer_phone = phone_prefix[random.nextInt(7)] + String.format("%d",random.nextInt(mobile_randomizer));
			System.out.println(customer_id +", "+customer_fname +", "+ customer_lname + ", " + customer_dvns + ", "+ customer_phone);
		}
	}
	
	
	private static void employeeTable() throws IOException
	{			
		//Employee Table	
		for(int i=0; i<=employee_count; i++) 
		{
			String employee_fname = fname_list.get(random.nextInt(fname_randomizer));
			String employee_lname = lname_list.get(random.nextInt(lname_randomizer));
			String employee_id = "3453" + String.format("%02d",i);
			String branch_id = String.format("%d", random.nextInt(branch_count)+1);
			String dept_id = String.format("%d", random.nextInt(dept_count)+1);
			String month_salary = String.format("%d",random.nextInt(min_salary,max_salary));
			System.out.println(employee_id +", "+employee_fname +", "+ employee_lname + ", " + branch_id + ", " + dept_id+ ", " + month_salary);
		}
	}
	
	private static void inventoryTable() throws IOException
	{			
		//Inventory Table	
		for(int i=0; i<=inventory_count; i++) 
		{
			String product_id = String.format("%d", i+1);
			String branch_id = String.format("%d", random.nextInt(dept_count)+1);
			String shipment_id = String.format("%d",random.nextInt(shipment_randomizer_offset, shipment_randomizer));
			String product_name = item_list.get(random.nextInt(item_randomizer));
s			Float price = item_dict.get(product_name);
			String stock_left = String.format("%.0f", Math.abs(random.nextGaussian(2.0, 10.0)+2*10));
			LocalDate exp_date = generateRandomDate(startDate, endDate);
			LocalDate arrival_date = generateRandomDate(startDate, endDate);
			
			System.out.println(product_id +", "+branch_id +", "+ shipment_id + ", " + product_name + ", " + price+ ", " + stock_left +", " +exp_date +", " + arrival_date);
		}
	}
	
	public static void main(String args[])throws IOException
	{
		random.setSeed(seed);
		fname_list = fileToList("fname.txt");
		lname_list = fileToList("lname.txt");
		item_list = fileToDict("item.txt");
		//customerTable();
		//employeeTable();
		inventoryTable();
		}
}
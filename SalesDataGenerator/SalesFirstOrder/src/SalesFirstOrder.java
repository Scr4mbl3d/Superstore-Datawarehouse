import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

public class SalesFirstOrder {
	private final static String url = "jdbc:postgresql://localhost/Sales";
	private final static String user = "postgres";
	private final static String password = "1234";
	
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
	private static int dept_count = 4;
	private static int employee_sales_offset = 300;
	private static int inventory_count = 10000;
	
	private static int min_salary = 10000;
	private static int max_salary = 40000;
	
	private static LocalDate startDate = LocalDate.of(2024, 1, 1); // Define start date
	private static LocalDate endDate = LocalDate.of(2024, 12, 31); // Define end date
	static ZoneId defaultZoneId = ZoneId.systemDefault();
	
	private static ArrayList<String> fname_list;
	private static ArrayList<String> lname_list;
	private static ArrayList<String> branch_list;
	private static Map<String, Float> item_dict = new HashMap<>();
	private static ArrayList<String> item_list;
	private static ArrayList<String> department_list = new ArrayList<>();
	
	private static String[] dvns= {"BLG", "CHN", "BOM", "HYD", "KOL", "DEL"};
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
	
		
	private static void customerTable() throws IOException, SQLException
	{
		//Customer Table
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO customer VALUES (?, ?, ?, ?::dvns, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		for(int i=0; i<=customer_count; i++) 
		{
			st.setInt(1,i+1);
			st.setString(2,fname_list.get(random.nextInt(fname_randomizer)));
			st.setString(3,lname_list.get(random.nextInt(lname_randomizer)));
			st.setString(4 ,dvns[random.nextInt(6)]);
			st.setString(5,phone_prefix[random.nextInt(7)] + String.format("%d",random.nextInt(mobile_randomizer)));
			st.executeUpdate();		
		}
		con.close();
	}
	
	
	private static void employeeTable() throws IOException, SQLException
	{			
		//Employee Table
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		for (int i = 0; i < employee_count; i++)
		{
			st.setInt(1, 3453 + i);
			st.setString(2, fname_list.get(random.nextInt(fname_randomizer)));
			st.setString(3, lname_list.get(random.nextInt(lname_randomizer)));
			st.setString(4,String.format("%d", random.nextInt(branch_count)+1));
			st.setString(5,String.format("%d", random.nextInt(dept_count)+1));
			st.setInt(6, random.nextInt(min_salary,max_salary));
			st.executeUpdate();	
		}
		System.out.println("Inserted employee table records successfully");
		con.close();
	}
	
	private static void inventoryTable() throws IOException, SQLException
	{			
		//Inventory Table
		String product_name;
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO local_inventory VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		for(int i=0; i<=inventory_count; i++) 
		{
			st.setString(1, String.format("%d", i+1));
			st.setString(2, String.format("%d", random.nextInt(dept_count)+1));
			st.setString(4, String.format("%d",random.nextInt(shipment_randomizer_offset, shipment_randomizer)));
			product_name = item_list.get(random.nextInt(item_randomizer));
			st.setString(3, product_name);
			st.setFloat(6,item_dict.get(product_name));
			st.setDouble(7,Math.abs(random.nextGaussian(2.0, 10.0)+2*10));
			st.setDate(8, Date.valueOf( generateRandomDate(startDate, endDate)));
			st.setDate(5,Date.valueOf( generateRandomDate(startDate, endDate)));
			st.executeUpdate();
		}		
		System.out.println("Inserted local_inventory table records successfully");
		con.close();
	}
	private static void orderDetailsTable() throws IOException, SQLException
	{			
		//Order Details Table
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO order_details VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		for(int i=0; i<=inventory_count; i++) 
		{
			st.setInt(1, i+452234);
			st.setString(2, String.format("%d", random.nextInt(inventory_count)+1));
			st.setString(3, String.format("%d",random.nextInt(customer_count)+1));
			st.setInt(4, random.nextInt(3453 +employee_sales_offset, 3453 +employee_count));
			st.setInt(5, random.nextInt(10));
			st.setDouble(6, Math.abs(random.nextGaussian(2.0, 10.0)+2*10));
			st.setDate(7, Date.valueOf( generateRandomDate(startDate, endDate)));
			st.executeUpdate();
		}
		System.out.println("Inserted order_details table records successfully");
		con.close();
	}
	private static void branchTable() throws IOException, SQLException
	{
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO branch VALUES (?, ?, ?::dvns)";
		PreparedStatement st = con.prepareStatement(sql);
		String[] values = new String[3];
		for(int i = 0; i < 25;i++)
		{
			values = branch_list.get(i).split(","); 
			st.setString(1,values[0]);
			st.setString(2,values[1]);
			st.setString(3,values[2]);
			st.executeUpdate();	
			
		}
		System.out.println("Inserted branch table records successfully");
		con.close();	
	}
	
	private static void deptTable() throws IOException, SQLException
	{
		Connection con = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO department VALUES (?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		String[] values = new String[3];
		for(int i = 0; i < 5;i++)
		{
			values = department_list.get(i).split(","); 
			st.setString(1,values[0]);
			st.setString(2,values[1]);
			st.setInt(3,Integer.parseInt(values[2]));
			st.executeUpdate();	
			
		}
		System.out.println("Inserted department table records successfully");
		con.close();
	}
	
	public static void main(String args[])throws IOException, SQLException
	{

		random.setSeed(seed);
		fname_list = fileToList("fname.txt");
		lname_list = fileToList("lname.txt");
		item_list = fileToDict("item.txt");
		branch_list = fileToList("branch.txt");
		// Adding department names to the ArrayList
		department_list.add("1,Sales,3");
		department_list.add("2,Tech,5");
		department_list.add("3,Admin,1");
		department_list.add("4,Stock,4");
		department_list.add("5,Logistics,2");
		
		//customerTable();
		//branchTable();
		//deptTable();
		
		//employeeTable();
		//inventoryTable();
		
		orderDetailsTable();
		}
}
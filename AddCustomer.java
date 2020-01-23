package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class AddCustomer {

	Connection con;
	PreparedStatement pre;
	Statement stat;
	ResultSet res;
	String ch="y";
	Scanner sc=new Scanner(System.in);
	String option=null;
	int Customer_id;
	String Customer_fname,Customer_lname,Address,Email;
	public AddCustomer()

	{
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");
		     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","harsh","R@wat1997");
		   //  System.out.println("Mysql conneceted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void User() {
		AddCustomer c = new AddCustomer();
		System.out.println("If you want to perform the operations, Enter : y else : n");
		
		ch = sc.next();

		while(ch.equals("y"))
		{
			System.out.println("Select the operation you want to perform:(insert/delete/update/print/exit");
			option=c.sc.next();
			if(option.equals("insert"))
				c.insert();
			else if(option.equals("delete"))
				c.deleteDetails();
			else if(option.equals("update"))
				c.updateDetails();
			else if(option.equals("print"))
				c.printDetails();
			else if(option.equals("exit"))
				{System.out.println("Thank You");
				break;}
			System.out.println("Do you want to continue(y/n)");
			ch=c.sc.next();
		}
		System.out.println("Exit");

	}
	public void insert()

	{
		try
		{
			
			System.out.println("Enter Customer Id.");
			Customer_id=sc.nextInt();
			System.out.println("Enter Customer FName.");
			Customer_fname=sc.next();
			System.out.println("Enter Customer LName.");
			Customer_lname=sc.next();
			System.out.println("Enter Customer Address");
			Address=sc.next();
			System.out.println("Enter Customer Email");
			Email=sc.next();
			
			pre=con.prepareStatement("insert into Customer values(?,?,?,?,?)");
			pre.setInt(1, Customer_id);
			pre.setString(2,Customer_fname);
			pre.setString(3,Customer_lname);
			pre.setString(4,Address);
			pre.setString(5,Email);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Details Committed..");
			else
				System.out.println("Details are Not Committed..");
			pre.close();
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void printDetails()
	{
		try
		{
			stat=con.createStatement();
			res=stat.executeQuery("select * from Customer");
			while(res.next())
			{
				System.out.println("Customer_id : "+res.getInt("Customer_id"));
				System.out.println("Name : "+res.getString("Customer_fname"));
				System.out.println("");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateDetails()
	{
		
		try
		{
			System.out.println("Hey!!");
			System.out.println("Enter Customer Id.");
			int Customer_id=sc.nextInt();
			System.out.println("Enter Customer FName.");
			Customer_fname=sc.next();
			System.out.println("Enter Customer LName.");
			Customer_lname=sc.next();
			System.out.println("Enter Customer Address");
			Address=sc.next();
			System.out.println("Enter Customer Email");
			Email=sc.next();
			
			pre=con.prepareStatement("update Customer set Customer_fname=? & Customer_lname=? & Address=? & Email=? where Customer_id=?");
			pre.setInt(1,Customer_id);
			pre.setString(2, Customer_fname);
			pre.setString(3, Customer_lname);
			pre.setString(4, Address);
			pre.setString(5, Email);
			
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Name updated for Customerid 111");
			else
				System.out.println("Name is not updated..");
			pre.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void deleteDetails()
	{
		try
		{
			pre=con.prepareStatement("delete from Customer where Customer_id=?");
			pre.setInt(1,1);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Record Deleted...");
			else
				System.out.println("Record has not Deleted...");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		AddCustomer c=new AddCustomer();
		c.printDetails();
		System.out.println("=======================================================");
		c.User();	
	}
	}

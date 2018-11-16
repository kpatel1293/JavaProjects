/*******************************************************
 * Program Name: HKFloors
 * Student Name: Krishna Patel
 * Description of the Program: This program allows users to input and calculate the cost of the flooring of their choice.
 *******************************************************/

package com.krishna.projectcode;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class HKFloorsDB
{
	/******Private Instances******/
	private Connection connect;				//...connection
	private ArrayList<HKFloors> list; 	//...array list (contact class)
	
	/******Verify and Connect to Database******/
	public void HKFloorsDB()
	{
		//User Information
		//...url - edupe
		String url = "jdbc:mysql://devry.edupe.net:4300/CIS355A_3675";
		//...user name
		String usrnm = "3675";
		//...password
		String pwd = "DeVry_Student";
		
		//invoke array list
		list = new ArrayList<HKFloors>();
		
		//Test Connection - JBDC
		System.out.println("Test MySQL Connection - JBDC");
		try 
		{
		     Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException notFound) 
		{
		     System.out.println("Cannot locate the driver!");
		}
		System.out.println("Registered!");
		
		//Execute Connection to Database
		try 
		{
			//Connect using url, username, password
			connect = DriverManager.getConnection(url, usrnm, pwd);
			System.out.println("url: " + url + " username: " + usrnm + " password: " + pwd);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/******Add Order to Database******/
	protected void addOrder(HKFloors hkf)
	{
		try
		{
			//Insert Query
			String orderInsert = "INSERT INTO flooring (ID, CustomerName, CustomerAddress, FlooringType, FloorArea, FloorCost) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement insert = connect.prepareStatement(orderInsert);
			
			int id = 0;
			
			//Fill Row
			insert.setInt(1, id);
			insert.setString(2, hkf.getCustomerName());
			insert.setString(3, hkf.getCustomerAddress());
			insert.setString(4, hkf.getFlooringType());
			insert.setDouble(5, hkf.getArea());
			insert.setDouble(6, hkf.getCost());
			
			//execute query
			insert.executeUpdate();
			
			//row added message
			System.out.println("Student Added!");
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
	}
}

/*******************************************************
 * Program Name: HKFloors
 * Student Name: Krishna Patel
 * Description of the Program: This program allows users to input and calculate the cost of the flooring of their choice.
 *******************************************************/


package com.krishna.projectcode;

import java.sql.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class HKFloorsGUI extends JFrame
{
	/******Private Instances******/
	
	private HKFloors floor;
	private ArrayList<HKFloors> list;
	private HKFloorsDB floorDB;
	
	private Connection connect;
	private Statement stmt;
	private ResultSet rs;
	
	/*****GUI Components******/
	private JTabbedPane main;
	
	private JPanel newOrder, orderSummary, orderHistory;
	
	/******GUI : New Order******/
	private JLabel nameTxt, addressTxt, typeTxt, sizeTxt, lengthTxt, widthTxt;
	private JLabel areaTxt, costTxt;
	private JTextField nameStr, lengthStr, widthStr, addressStr;
	private JRadioButton woodBtn, carpetBtn;
	private ButtonGroup bg;
	private JButton submitBtn, resetBtn, calcAreaBtn, costBtn;
	
	/******GUI : Order Summary******/
	private JLabel nameOneTxt, addressOneTxt, typeOneTxt, areaOneTxt, costOneTxt;
	private JLabel nameTwoTxt, addressTwoTxt, typeTwoTxt, areaTwoTxt, costTwoTxt;
	private JButton doneBtn;
	
	/******GUI : Order History******/
	private JLabel title;
	private JTable table;
	private DefaultTableModel model;
	
	/******Public Instances******/
	public HKFloorsGUI()
	{
		super("HK Floors");
		
		floor = new HKFloors();
		floorDB = new HKFloorsDB();
		floorDB.HKFloorsDB();
		
		/******Declaration******/
		main = new JTabbedPane();
		
		/******************************/
		/********New Order Tab*********/
		/******************************/
		newOrder = new JPanel();
		
		/******Declaration******/
		nameTxt = new JLabel("Customer Name");
		addressTxt = new JLabel("Customer Adddress");
		typeTxt = new JLabel("Flooring Type: ");
		sizeTxt = new JLabel("Enter the size of the floor in feet (ft):");
		lengthTxt = new JLabel("Length");
		widthTxt = new JLabel("Width");
		
		areaTxt = new JLabel("");
		costTxt = new JLabel("");
		
		nameStr = new JTextField(20);
		nameStr.setText("Enter Name...");
		addressStr = new JTextField(20);
		addressStr.setText("Enter Address...");
		lengthStr = new JTextField(10);
		lengthStr.setText("Enter length...");
		widthStr = new JTextField(10);
		widthStr.setText("Enter width...");
		
		woodBtn = new JRadioButton("Wood Floor");
		carpetBtn = new JRadioButton("Carpet");
		
		submitBtn = new JButton("Display Order Summary");
		resetBtn = new JButton("Reset");
		calcAreaBtn = new JButton("Calculate Area");
		costBtn = new JButton("Calculate Cost");
		
		/******Radio Button******/
		bg = new ButtonGroup();
		bg.add(woodBtn);
		bg.add(carpetBtn);
		
		/******Action Listener : Display Order Summary******/
		submitBtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent ae)
			{
				displayOrder();
				main.setSelectedIndex(1);	

				
				System.out.println("Submit Button Clicked!");
			}
				});
		
		/******Action Listener : Reset******/
		resetBtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent ae)
			{
				reset();
				System.out.println("Reset Button Clicked!");
			}
				});
		
		/******Action Listener : Calculate Area******/
		calcAreaBtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent ae)
			{
				calcArea();
				System.out.println("Area Button Clicked!");
			}
				});
		
		/******Action Listener : Calculate Cost******/
		costBtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent ae)
			{
				cost();
				System.out.println("Cost Button Clicked!");
			}
				});
		
		/******Add To New Order Tab******/
		newOrder.setLayout(new GridLayout(0,2));

		newOrder.add(nameTxt);
		newOrder.add(nameStr);
		newOrder.add(addressTxt);
		newOrder.add(addressStr);

		newOrder.add(sizeTxt);
		newOrder.add(new JLabel(""));
		newOrder.add(lengthTxt);
		newOrder.add(lengthStr);
		newOrder.add(widthTxt);
		newOrder.add(widthStr);

		newOrder.add(typeTxt);
		newOrder.add(new JLabel(""));
		newOrder.add(woodBtn);
		newOrder.add(carpetBtn);
		
		newOrder.add(submitBtn);
		newOrder.add(resetBtn);
		newOrder.add(calcAreaBtn);
		newOrder.add(costBtn);
		
		newOrder.add(areaTxt);
		newOrder.add(costTxt);
		
		/**********************************/
		/********Order Summary Tab*********/
		/**********************************/
		orderSummary = new JPanel();
		
		/******Declaration******/
		nameOneTxt = new JLabel("Customer Name");
		addressOneTxt = new JLabel("Customer Address");
		typeOneTxt = new JLabel("Floor Selection");
		areaOneTxt = new JLabel("Area (sq ft)");
		costOneTxt = new JLabel("Cost ($)");
		
		nameTwoTxt = new JLabel("Customer Name");
		addressTwoTxt = new JLabel("Customer Address");
		typeTwoTxt = new JLabel("Wood Floor/Carpet"); 
		areaTwoTxt = new JLabel("00 sq ft");
		costTwoTxt = new JLabel("$ 00.00");

		doneBtn = new JButton("Done");
	
		/******Action Listener : Done******/
		doneBtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent ae)
			{
				orderSummaryFunction();
				orderHistoryTable();
				main.setSelectedIndex(2);
				System.out.println("Done Button Clicked!");
			}
				});
		
		/******Add To Order Summary Tab******/
		orderSummary.setLayout(new GridLayout(0,2));
		
		orderSummary.add(nameOneTxt);
		orderSummary.add(nameTwoTxt);
		orderSummary.add(addressOneTxt);
		orderSummary.add(addressTwoTxt);
		orderSummary.add(typeOneTxt);
		orderSummary.add(typeTwoTxt);
		orderSummary.add(areaOneTxt);
		orderSummary.add(areaTwoTxt);
		orderSummary.add(costOneTxt);
		orderSummary.add(costTwoTxt);
		orderSummary.add(doneBtn);
		
		/**********************************/
		/********Order History Tab*********/
		/**********************************/
		orderHistory = new JPanel();
		
		/******Declaration******/
		model = new DefaultTableModel();
		title = new JLabel("Order History");
		//Attach model to table
		table = new JTable();
		table.setModel(model);
		
		/******Add To Order Summary Tab******/
		orderHistory.setLayout(new GridLayout(2,0));
		orderHistory.add(title);
		orderHistory.add(new JScrollPane(table));
		
		/******Add To GUI******/
		main.add(newOrder, "New Order");
		main.add(orderSummary, "Order Summary");
		main.add(orderHistory, "Order History");
		
		add(main);
		
		/******GUI Layout******/
		setSize(600, 450);						//size
		setVisible(true); 						//visible
	}
	
	/******New Order : Display Order Summary Button******/
	public void displayOrder()
	{		
		//Get all user inputs
		String name = nameStr.getText();
		String address = addressStr.getText();
		double length = Double.parseDouble(lengthStr.getText());
		double width = Double.parseDouble(widthStr.getText());
		
		//Get flooring type selection/Set Text in Order Summary
		if(woodBtn.isSelected())
		{
			floor.setFlooringType("Wood Floor");
			typeTwoTxt.setText("Wood Floor");
		}
		else if(carpetBtn.isSelected())
		{
			floor.setFlooringType("Carpet");
			typeTwoTxt.setText("Carpet");
		}
		
		//Set text
		floor.setCustomerName(name);
		floor.setCustomerAddress(address);
		floor.setLength(length);
		floor.setWidth(width);
		String area = String.valueOf(floor.getArea());
		String cost = String.valueOf(floor.getCost());
		
		//Set Text in Order Summary when submit button clicked
		nameTwoTxt.setText(floor.getCustomerName());
		addressTwoTxt.setText(floor.getCustomerAddress());
		areaTwoTxt.setText(area + " sq ft");
		costTwoTxt.setText("$ " + cost);
		
		//Message
		System.out.println("Added to Order Summary!");
	}
	
	/******New Order : Calculate Area Button******/
	public void calcArea()
	{
		//get length and width
		double length = Double.parseDouble(lengthStr.getText());
		double width = Double.parseDouble(widthStr.getText());
		
		//set length and width to get area
		floor.setLength(length);
		floor.setWidth(width);
		String area = String.valueOf(floor.getArea());
		
		//output Area 
		areaTxt.setText("Area: " + area + " sq ft");
	}
	
	/******New Order : Calculate Cost Button******/
	public void cost()
	{
		//Get flooring type selection/Set Text in Order Summary
		if(woodBtn.isSelected())
		{
			floor.setFlooringType("Wood Floor");
		}
		else if(carpetBtn.isSelected())
		{
			floor.setFlooringType("Carpet");
		}
		
		//Calculate cost
		String cost = String.valueOf(floor.getCost());
		
		//output cost
		costTxt.setText("Cost: $" + cost);
	}
	
	/******New Order : Reset Button******/
	public void reset()
	{
		//Reset all inputs/selection
		nameStr.setText("Enter Name...");
		addressStr.setText("Enter Address...");
		lengthStr.setText("Enter length...");
		widthStr.setText("Enter width...");
		bg.clearSelection();
		areaTxt.setText("");
		costTxt.setText("");
	}
	
	/******Order Summary : Add to Database******/
	public void orderSummaryFunction()
	{
		//Get User Input
		String name = nameTwoTxt.getText();
		String addr	= addressTwoTxt.getText();
		String type = typeTwoTxt.getText();
		Double length = Double.parseDouble(lengthStr.getText());
		Double width = Double.parseDouble(widthStr.getText());
		
		//HKFloor Class
		HKFloors floor = new HKFloors(name, addr, type, length, width);
		
		//add to database
		floorDB.addOrder(floor);
		
		//Clear all Input
		clear();
		reset();
	}
	
	/******New Order : Reset Button******/
	public void clear()
	{
		nameTwoTxt.setText("Customer Name");
		addressTwoTxt.setText("Customer Address");
		typeTwoTxt.setText("Wood Floor/Carpet"); 
		areaTwoTxt.setText("00 sq ft");
		costTwoTxt.setText("$ 00.00");
	}
	
	/******Order History : Table******/
	public void orderHistoryTable()
	{
		//Database Info
		//...url - edupe
        String url = "jdbc:mysql://devry.edupe.net:4300/CIS355A_3675";
        //...user name
        String usrnm = "3675";
        //...password
        String pwd = "DeVry_Student";
        
        try
        {
        		//Connect to database
        		connect = DriverManager.getConnection(url, usrnm, pwd);
        		
        		//Query to display data
        		String query = "SELECT * FROM flooring";
        		stmt = connect.createStatement();
        		rs = stmt.executeQuery(query);
        		
        		//Metadata to get column names
        		ResultSetMetaData ms = rs.getMetaData();
        		
        		//Get Column Count
        		int count = ms.getColumnCount();
        		
        		//Vector object to add column name
        		Vector<String> columnName = new Vector<String>();
        		
        		//Loop to Get column name
        		for (int i = 1; i <= count; i++)
        		{
        			columnName.add(ms.getColumnName(i));
        		}
        		
        		//Add column name to model
        		model.setColumnIdentifiers(columnName);
        		
        		//Get Each Row
        		while(rs.next())
        		{
        			model.addRow(new Object[] { rs.getInt("ID"),
                            rs.getString("CustomerName"),
                            rs.getString("CustomerAddress"),
                            rs.getString("FlooringType"),
                            rs.getDouble("FloorArea"),
                            rs.getDouble("FloorCost")});
        		}
        }
        catch(SQLException sqle)
        {
        		sqle.printStackTrace();
        }
	}
	
	public static void main(String arg[])
	{
		HKFloorsGUI gui = new HKFloorsGUI();
	}
}

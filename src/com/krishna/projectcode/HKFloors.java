/*******************************************************
 * Program Name: HKFloors
 * Student Name: Krishna Patel
 * Description of the Program: This program allows users to input and calculate the cost of the flooring of their choice.
 *******************************************************/

package com.krishna.projectcode;

import java.text.DecimalFormat;

public class HKFloors 
{
	/******Private Instances******/
	private String customerName;
	private String customerAddress;
	private String flooringType;
	private double length;
	private double width;
	
	/******Public Instances******/
	
	/******Default Constructor******/
	public HKFloors()
	{
		customerName = "";
		customerAddress = "";
		flooringType = "";
		length = 0.0;
		width = 0.0;
	}
	
	/******Parameterized Constructor******/
	public HKFloors(String name, String address, String type, Double l, Double w)
	{
		customerName = name;
		customerAddress = address;
		flooringType = type;
		length = l;
		width = w;
	}
	
	/******Customer Name : setter******/
	public void setCustomerName(String name)
	{
		customerName = name;
	}
	
	/******Customer Name : getter******/
	public String getCustomerName()
	{
		return customerName;
	}
	
	/******Customer Address : setter******/
	public void setCustomerAddress(String address)
	{
		customerAddress = address;
	}
	
	/******Customer Address : getter******/
	public String getCustomerAddress()
	{
		return customerAddress;
	}
	
	/******Flooring Type : setter******/
	public void setFlooringType(String type)
	{
		flooringType = type;
		
	}
	
	/******Flooring Type : getter******/
	public String getFlooringType()
	{
		return flooringType;
	}
	
	/******Length : setter******/
	public void setLength(double l)
	{
		length = l;
	}
	
	/******Length : getter******/
	public double getLength()
	{
		return length;
	}
	
	/******Width : setter******/
	public void setWidth(double w)
	{
		width = w;
	}
	
	/******Width : getter******/
	public double getWidth()
	{
		//return width
		return width;
	}
	
	/******Area : getter******/
	public double getArea()
	{
		//Calculate area - length * width
		double area = length * width;
		
		return area;
	}
	
	/******Cost : getter******/
	public double getCost()
	{
		//initialize cost
		double cost = 0.00;
		
		//Set cost price according to type of flooring
		if (flooringType == "Wood Floor")
		{
			cost = 20.00;
		}
		else if (flooringType == "Carpet")
		{
			cost = 10.00;
		}
		
		//Calculate cost - area * cost = cost of flooring
		double calcCost = getArea() * cost;
		
		//return cost
		return calcCost;
	}
	
	/******To String******/
	public String toString()
	{
		return customerName;
	}
}
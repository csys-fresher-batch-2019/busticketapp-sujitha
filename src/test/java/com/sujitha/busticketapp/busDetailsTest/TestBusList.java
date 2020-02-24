package com.sujitha.busticketapp.busDetailsTest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.BusListDAOImpl;
import com.sujitha.busticketapp.dto.BusesDetails;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusList;

public class TestBusList {
	private static final Logger log=Logger.getInstance();
	

	public static void main(String[] args)throws DbException {
		
		
		BusListDAOImpl bli = new BusListDAOImpl();
		//Insert the details:
				//bli.busList(106,"AKV",10,"seater");
				
		//Update busName by using busNum
			//	bli.busNameUpdate("TVS",103);
	  
				
		//To Display busName by using busNum
				
				//String ss=bli.busName(102);
			//System.out.println(ss);		
			
    	//To  Display NoOfSeats by giving busNum
				    int seats = bli.noOfSeats(102);
				System.out.println(seats);		
				
		//Select the details by using List
				  // DisplayBusDeatils1();
		
		// To Delete busName*/
		         // bli.deleteBusName("TSS");
		//Select the details by using List
		   //DisplayBusDeatils();
		//bli.updateSeats(101);
	int a=bli.getAvailableSeats(101,LocalDate.now() );
	System.out.println(a);
		} 
	
	
	
	 public static void DisplayBusDeatils() throws DbException {
		 BusListDAOImpl bli = new BusListDAOImpl();
		 List<BusesDetails> list=new ArrayList<BusesDetails>();
			list=bli.allBusListDetails(1);
			for(BusesDetails busList:list)
			{
				System.out.println(busList.toString()
						);
			}
	 }
	 public static void DisplayBusDeatils1() throws DbException {
		 BusListDAOImpl bli = new BusListDAOImpl();
		 List<BusList> list=new ArrayList<BusList>();
			list=bli.allBusListDetailss();
			for(BusList busList:list)
			{
				System.out.println(busList);
			}
	 }
	 
	 
	 
	// TODO Auto-generated method stub
		
				// TODO Auto-generated method stub
				//BusList bl =new BusList();
			//	bl.busNum = 100;
				//bl.busName ="avk";
			//	bl.noOfSeats=10;
			////	bl.seatType="seater";
				
		       //BusListDAOImpl bli = new BusListDAOImpl();
			//bli.BusListDisplay(100,"tat",10,"sleaper");
			//bli.ListDisplay(bl);
			
			//Connection connection =DbConnection.getConnection() ; 
			//String sql="insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(50,'TAt',048,'sleeper')";
			//System.out.println(sql);
			//Statement stmt=connection.createStatement();
			//int row=stmt.executeUpdate(sql);
			//System.out.println(row);
			//String s="update buslist set bus_name ='SRM' where bus_num=101";
			//System.out.println(s);
			//Statempent stm=connection.createStatement();
			//int row1=stm.executeUpdate(s);
			//System.out.println(row1);
	 
	 
	 
	 
	}



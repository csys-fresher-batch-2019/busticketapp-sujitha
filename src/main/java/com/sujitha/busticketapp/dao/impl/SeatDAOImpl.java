package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class SeatDAOImpl {

	
	public int getBookedNumberOfSeats(int BusNum) throws Exception {
		Connection connection=DbConnection.getConnection();
		String s="select seat_no from booking where bus_num=?";
		System.out.println(s);
		 //Booking  b = new Booking();
		 PreparedStatement pst = connection.prepareStatement(s);
		   pst.setInt(1,BusNum);
		   ResultSet rs=pst.executeQuery();
		   int b = 0;
		   ArrayList<Integer> l=new ArrayList<Integer>();
		   int c=0;
		   while(rs.next())
			{
		
			   b = rs.getInt("seat_no");
			   c++;
			  l.add(b);
			  System.out.println(b);
			   
			}
		   System.out.println("Count : "+c);
		return c;
		
	}
	
	public int getTotalNumberofSeats(int BusNum) throws Exception {
		Connection connection=DbConnection.getConnection();
		String s="select no_of_seats from buslist where bus_num=?";
		System.out.println(s);
		PreparedStatement pst = connection.prepareStatement(s);
		   pst.setInt(1,BusNum);
		   ResultSet rs=pst.executeQuery();
		   int a= 0;
		   if(rs.next())
		   {
			   a=rs.getInt("no_of_seats");
		   }
		return a;
		
	}

	

public ArrayList<Integer> getUnFilledSeatNo(LocalDate bookedDate, int busNum) throws Exception {
	Connection connection=DbConnection.getConnection();
   String sql="select min_seat_no -1+level missing_number from (select min(1) min_seat_no,max(10) max_seat_no from booking)connect by  level <=max_seat_no-min_seat_no+1 minus select seat_no  as available_seats from booking where bus_num=? and booked_date=?";  
   		System.out.println(sql);
   		
		PreparedStatement pst = connection.prepareStatement(sql);
		   pst.setInt(1,busNum);
		   pst.setDate(2,Date.valueOf(bookedDate));
		   ResultSet rs=pst.executeQuery();
		   int a1=0;
		   ArrayList<Integer> unSeats=new ArrayList<Integer>();
		   while(rs.next()) {
			    unSeats.add(rs.getInt(1));
			   
			   
		   }
	return unSeats;
	}

          public  HashMap<String,String> getInsertUnFiledSeats(int busNum,int seatNo,LocalDate bookedDate) throws Exception {
        	 
        	  Connection connection=DbConnection.getConnection();
        	  SeatService s=new SeatService();
        	  int previousSeatNo=s.getUnFiledSeats(busNum, seatNo, bookedDate);

        	  String sql="select user_gender,gender_preferences from booking where booked_date=? and bus_num=? and seat_no=? ";
        	  
        	 PreparedStatement pst = connection.prepareStatement(sql);
   		   pst.setDate(1,Date.valueOf(bookedDate));
   		      pst.setInt(2,busNum);
   		   pst.setInt(3,previousSeatNo);
   		   ResultSet rs=pst.executeQuery();
   		   String s1=null;
   		   HashMap<String, String> hm=new HashMap<String, String>();
   		   while(rs.next())
   		   {
   			  hm.put(rs.getString("user_gender"),rs.getString("gender_preferences"));
   			  
   			  
   		   }
		return (hm) ;
   		  
          }  
          
          }





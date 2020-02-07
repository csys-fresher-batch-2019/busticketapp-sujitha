package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BookingDeatilsDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class BookingDetailDAOImpl implements BookingDeatilsDAO {
	private static final Logger log=Logger.getInstance(); 

	public int getLastSeatNo(LocalDate bookedDate, int BusNum) throws Exception {
		int seats = 0;
		String sql = " select max(seat_no) as seat_no from booking where booked_date=? and bus_num= ?";

		try(Connection connection = DbConnection.getConnection();PreparedStatement pst = connection.prepareStatement(sql);ResultSet rs = pst.executeQuery();)
		{
		   pst.setDate(1, Date.valueOf(bookedDate));
		  pst.setInt(2, BusNum);
		  if (rs.next()) {
			  seats = rs.getInt("seat_no");

		
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return seats;

	}

	public Booking searchBySeatNo(LocalDate bookedDate, int BusNum, int seatNo) throws Exception {
		
		String sql = " select seat_no, user_gender,gender_preferences from booking where booked_date=? and bus_num= ? and seat_no =?";
		 Booking b = null;
	    try (Connection connection = DbConnection.getConnection();PreparedStatement pst = connection.prepareStatement(sql);ResultSet rs = pst.executeQuery();)
	   
	    		
	    {
		
		pst.setDate(1, Date.valueOf(bookedDate));
		pst.setInt(2, BusNum);
		pst.setInt(3, seatNo);
		
		
		if (rs.next()) {
			b = new Booking();
			b.setSeatNo(rs.getInt("seat_no"));
			b.setUserGender(rs.getString("user_gender"));
			b.setGenderPreference(rs.getString("gender_preferences"));
		
			// b.genderPreference=rs.
		}
	}catch(Exception e)
		{
		e.printStackTrace();
	}
		return b;

	}

	public HashMap<Integer, String> getSeatNoAndUserGender(int busNum) throws Exception {
		String userGender1 = "F";
		String sql = "select seat_no,user_gender from booking where bus_num=busNum";
		HashMap<Integer, String> m = new HashMap<Integer, String>();
	try(Connection connection = DbConnection.getConnection();
			Statement stmt = connection.createStatement();ResultSet rs = stmt.executeQuery(sql);)
			{
		    while (rs.next()) {
			int seatNo = rs.getInt("seat_no");
			String gender = rs.getString("user_gender");
			m.put(seatNo, gender);
		}	
	}catch(Exception e)
	{
	e.printStackTrace();
}
		return m;

	}
	
	public void addUserBookingDetails(Booking booking) throws Exception {
		SeatDAOImpl sd = new SeatDAOImpl();
		SeatService ss = new SeatService();
		int nextSeatNo = ss.getNextSeatNo(booking.getBookedDate(), booking.getBusNum());
		booking.setSeatNo(nextSeatNo);
		int nextSeatNo1 = ss.getNextSeatNo(booking.getBookedDate(), booking.getBusNum(), booking.getUserGender(),
				booking.getGenderPreference());
		booking.setSeatNo(nextSeatNo1);

		
		//String s= getgenderPreferences(booking.getSeatNo(),booking.getUserGender());
		// log.getInput(s);
		String sql1 = "select no_of_seats from buslist where bus_num=?";
		String str = "insert into booking(user_id,travel_id,bus_num,user_gender,seat_no,booked_date,gender_preferences) values(?,?,?,?,?,?,?)";
		log.getInput(str);

		try(Connection connection = DbConnection.getConnection();
           PreparedStatement pst1 = connection.prepareStatement(sql1);ResultSet rs = pst1.executeQuery();PreparedStatement pst = connection.prepareStatement(str);)
		{
		pst1.setInt(1, booking.getBusNum());
		
		
		
		int totalSeats = 0;
		if (rs.next()) {
			totalSeats = rs.getInt("no_of_seats");
		}
		boolean isSeatsAvailable = totalSeats >= booking.getSeatNo();
		log.getInput("SeatsAvailable:" + isSeatsAvailable + ",totalSeats=" + totalSeats + ",booking Seat NO:" + booking.getSeatNo());
		if (isSeatsAvailable) {

			
			
			pst.setInt(1, booking.getUserId());
			pst.setInt(2, booking.getTravelId());

			pst.setInt(3, booking.getBusNum());
			pst.setString(4, booking.getUserGender());
			pst.setInt(5, booking.getSeatNo());
			pst.setDate(6, Date.valueOf(booking.getBookedDate()));
			pst.setString(7, booking.getGenderPreference());
            if(booking.getSeatNo()!=0)
            {
			int rows = pst.executeUpdate();
			log.getInput(rows);
            }
            else
            	log.getInput("seats are not available");
            
		}
	}catch(Exception e)
		{
		e.printStackTrace();
	}
		}

	
   public void addAvaialbleSeats(Booking booking,int seatNo) throws Exception {
	   String str = "insert into booking(user_id,travel_id,bus_num,user_gender,seat_no,booked_date,gender_preferences) values(?,?,?,?,?,?,?)";
		log.getInput(str);
		HashMap<String, String> hm=new HashMap<String, String>();
		SeatDAOImpl si=new SeatDAOImpl(); String gender=null;
		   String gender_preference=null;
 try (Connection connection = DbConnection.getConnection();
		 PreparedStatement pst = connection.prepareStatement(str);)
 {
	   hm=si.getInsertUnFiledSeats(booking.getBusNum(), seatNo, booking.getBookedDate());
	  
	   for(String s:hm.keySet())
	   {
		   gender=s;
	       gender_preference=hm.get(s);
	       
	   }
	   log.getInput(gender+"  "+gender_preference);
	  

		
		pst.setInt(1, booking.getUserId());
		pst.setInt(2, booking.getTravelId());

		pst.setInt(3, booking.getBusNum());
		pst.setString(4, booking.getUserGender());
		pst.setInt(5, seatNo);
		pst.setDate(6, Date.valueOf(booking.getBookedDate()));
		pst.setString(7, booking.getGenderPreference());
	   
	   if(booking.getUserGender().equals(gender) && booking.getGenderPreference().equals(gender_preference))
	   {
		   int rows = pst.executeUpdate();
		   log.getInput("seats are available");
	   }
	   else if(booking.getUserGender().equals(gender) && (!booking.getGenderPreference().equals(gender_preference))) 
	   {
		   int rows = pst.executeUpdate();
		   log.getInput("seats are available");
	   }
	   else if(!booking.getUserGender().equals(gender)  && (booking.getGenderPreference().equals("no") && gender_preference.equals("no")))
	   {
		   int rows = pst.executeUpdate();
		   log.getInput("seats are available");
	   }
	   
	   else
		   log.getInput("seats are not available");
	
	   
 }catch(Exception e)
	{
	e.printStackTrace();
}
   
   }
   
	public void bookUnfilledSeats(Booking booking) throws Exception {
		 SeatDAOImpl sdi = new SeatDAOImpl();
		  ArrayList<Integer> ai = new  ArrayList<Integer> ();
		 ai= sdi. getUnFilledSeatNo(booking.getBookedDate(), booking.getBusNum());
		 log.getInput("***Un filled seats***\n");
		 for(int seat:ai) {
			 
		 log.getInput(seat);
		}
		 Scanner sc= new Scanner(System.in);
		 log.getInput("Enter the seat no: ");
		 int s=sc.nextInt();
		 addAvaialbleSeats(booking, s);
		 
		 
		 
		 
		 
		 
		 
	
	}
}

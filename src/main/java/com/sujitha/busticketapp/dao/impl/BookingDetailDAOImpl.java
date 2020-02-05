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
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class BookingDetailDAOImpl implements BookingDeatilsDAO {

	public int getLastSeatNo(LocalDate bookedDate, int BusNum) throws Exception {
		int seats = 0;
		Connection connection = DbConnection.getConnection();

		String sql = " select max(seat_no) as seat_no from booking where booked_date=? and bus_num= ?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setDate(1, Date.valueOf(bookedDate));
		pst.setInt(2, BusNum);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			seats = rs.getInt("seat_no");

		}

		return seats;

	}

	public Booking searchBySeatNo(LocalDate bookedDate, int BusNum, int seatNo) throws Exception {
		Connection connection = DbConnection.getConnection();

		String sql = " select seat_no, user_gender,gender_preferences from booking where booked_date=? and bus_num= ? and seat_no =?";

		Booking b = null;
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setDate(1, Date.valueOf(bookedDate));
		pst.setInt(2, BusNum);
		pst.setInt(3, seatNo);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			b = new Booking();
			b.seatNo = rs.getInt("seat_no");
			b.userGender = rs.getString("user_gender");
			b.genderPreference = rs.getString("gender_preferences");
			// b.genderPreference=rs.
		}

		return b;

	}

	public HashMap<Integer, String> getSeatNoAndUserGender(int busNum) throws Exception {
		Connection connection = DbConnection.getConnection();
		String userGender1 = "F";
		String sql = "select seat_no,user_gender from booking where bus_num=busNum";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		HashMap<Integer, String> m = new HashMap<Integer, String>();
		while (rs.next()) {
			int seatNo = rs.getInt("seat_no");
			String gender = rs.getString("user_gender");
			m.put(seatNo, gender);
		}
		return m;

	}

	public void addUserBookingDetails(Booking booking) throws Exception {
		SeatDAOImpl sd = new SeatDAOImpl();
		SeatService ss = new SeatService();
		int nextSeatNo = ss.getNextSeatNo(booking.bookedDate, booking.busNum);
		booking.seatNo = nextSeatNo;
		int nextSeatNo1 = ss.getNextSeatNo(booking.bookedDate, booking.busNum, booking.userGender,
				booking.genderPreference);
		booking.seatNo = nextSeatNo1;

		
		// String s= getgenderPreferences(booking.seatNo,booking.userGender);
		// System.out.println(s);
		Connection connection = DbConnection.getConnection();

		String sql1 = "select no_of_seats from buslist where bus_num=?";
		PreparedStatement pst1 = connection.prepareStatement(sql1);
		pst1.setInt(1, booking.busNum);
		ResultSet rs = pst1.executeQuery();
		int totalSeats = 0;
		if (rs.next()) {
			totalSeats = rs.getInt("no_of_seats");
		}
		boolean isSeatsAvailable = totalSeats >= booking.seatNo;
		System.out.println("SeatsAvailable:" + isSeatsAvailable + ",totalSeats=" + totalSeats + ",booking Seat NO:" + booking.seatNo);
		if (isSeatsAvailable) {

			String str = "insert into booking(user_id,travel_id,bus_num,user_gender,seat_no,booked_date,gender_preferences) values(?,?,?,?,?,?,?)";
			System.out.println(str);

			PreparedStatement pst = connection.prepareStatement(str);
			pst.setInt(1, booking.userId);
			pst.setInt(2, booking.travelId);

			pst.setInt(3, booking.busNum);
			pst.setString(4, booking.userGender);
			pst.setInt(5, booking.seatNo);
			pst.setDate(6, Date.valueOf(booking.bookedDate));
			pst.setString(7, booking.genderPreference);
            if(booking.seatNo!=0)
            {
			int rows = pst.executeUpdate();
			System.out.println(rows);
            }
            else
            	System.out.println("seats are not available");
		}
	}

	
   public void addAvaialbleSeats(Booking booking,int seatNo) throws Exception {
	   Connection connection = DbConnection.getConnection();
	   SeatDAOImpl si=new SeatDAOImpl();
	   HashMap<String, String> hm=new HashMap<String, String>();
	   hm=si.getInsertUnFiledSeats(booking.busNum, seatNo, booking.bookedDate);
	   String gender=null;
	   String gender_preference=null;
	   for(String s:hm.keySet())
	   {
		   gender=s;
	       gender_preference=hm.get(s);
	       
	   }
	   System.out.println(gender+"  "+gender_preference);
	   String str = "insert into booking(user_id,travel_id,bus_num,user_gender,seat_no,booked_date,gender_preferences) values(?,?,?,?,?,?,?)";
		System.out.println(str);

		PreparedStatement pst = connection.prepareStatement(str);
		pst.setInt(1, booking.userId);
		pst.setInt(2, booking.travelId);

		pst.setInt(3, booking.busNum);
		pst.setString(4, booking.userGender);
		pst.setInt(5, seatNo);
		pst.setDate(6, Date.valueOf(booking.bookedDate));
		pst.setString(7, booking.genderPreference);
	   
	   if(booking.userGender.equals(gender) && booking.genderPreference.equals(gender_preference))
	   {
		   int rows = pst.executeUpdate();
		   System.out.println("seats are available");
	   }
	   else if(booking.userGender.equals(gender) && (!booking.genderPreference.equals(gender_preference))) 
	   {
		   int rows = pst.executeUpdate();
		   System.out.println("seats are available");
	   }
	   else if(!booking.userGender.equals(gender)  && (booking.genderPreference.equals("no") && gender_preference.equals("no")))
	   {
		   int rows = pst.executeUpdate();
		   System.out.println("seats are available");
	   }
	   
	   else
		   System.out.println("seats are not available");
	
	   

   
   }
   
	public void bookUnfilledSeats(Booking booking) throws Exception {
		 SeatDAOImpl sdi = new SeatDAOImpl();
		  ArrayList<Integer> ai = new  ArrayList<Integer> ();
		 ai= sdi. getUnFilledSeatNo(booking.bookedDate, booking.busNum);
		 System.out.println("***Un filled seats***\n");
		 for(int seat:ai) {
			 
		 System.out.println(seat);
		}
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Enter the seat no: ");
		 int s=sc.nextInt();
		 addAvaialbleSeats(booking, s);
		 
		 
		 
		 
		 
		 
		 
	
	}
}

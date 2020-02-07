package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.TicketBookingDAO;
import com.sujitha.busticketapp.dto.BusSeatsBooked;
import com.sujitha.busticketapp.model.TicketBooking;

public class TicketBookingDAOImpl implements TicketBookingDAO {

	public void addBookingDetails(TicketBooking tic) throws Exception
	{
		String sql="insert into ticket_booking(travel_id,no_of_seats_booked,user_id,fair,j_date,booked_date,payment,status) values(?,?,?,?,?,?,?,?)";
		
		try(Connection connection =DbConnection.getConnection() ;
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		pst.setInt(1,tic.travelId);
		pst.setInt(2,tic.noOfSeatsBooked);
		pst.setInt(3,tic.userId);
		pst.setInt(4,tic.fair);
		pst.setDate(5,Date.valueOf(tic.jDate));
		pst.setDate(6,Date.valueOf(tic.bookedDate));
		pst.setInt(7,tic.payment);
		pst.setString(8,tic.status);
		int row=pst.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getNoOfSeatsBooked(int travelId) throws Exception
	{
		String n =" select sum(no_of_seats_booked) no_of_seats_booked from ticket_booking where travel_id=? ";
		  System.out.println(n);
		  int s=0;
		try(Connection connection =DbConnection.getConnection() ;
	     PreparedStatement pst = connection.prepareStatement(n);
	     ResultSet rows=pst.executeQuery();)
		{
		pst.setInt(1,travelId);
		if(rows.next())
		{
			s= rows.getInt("no_of_seats_booked");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}

	public int totalPayment(String status) throws Exception {
		String sql ="select sum(payment) as payment from ticket_booking where status=?";
		 System.out.println(sql);
		 int f=0;
		try(Connection connection =DbConnection.getConnection() ;
	    PreparedStatement pst = connection.prepareStatement(sql);
	    ResultSet rows=pst.executeQuery();)
		{
		pst.setString(1,status);
		
		
		if(rows.next())
		{
			f= rows.getInt("payment");
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	return f;
	}

	public ArrayList<BusSeatsBooked> totalNoOfSeatsBooked(String status) throws Exception {
		String sql ="select booked_date,count(no_of_seats_booked) as total_seats from ticket_booking where status= ? group by booked_date";
		System.out.println(sql);
		ArrayList<BusSeatsBooked> list = new ArrayList<BusSeatsBooked>();
		try(Connection connection =DbConnection.getConnection() ;
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rows=pst.executeQuery();)
		{
		pst.setString(1,status);
		while(rows.next())
		{
	      int s= rows.getInt("total_seats");
	       Date si = rows.getDate("booked_date");
						LocalDate ld = si.toLocalDate();
			BusSeatsBooked bsd=new BusSeatsBooked();
			bsd.totalseats=s;
			bsd.bookedDate = ld;
			list.add(bsd);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	return list;
	
	}

	public int getSeatNo(int travelId, int userId) throws Exception {
		String sql="select  count(b.seat_no) as ticket_count from booking b  where travel_id=?  and user_id=?"; 
		System.out.println(sql);
		int f=0;
		try(Connection connection =DbConnection.getConnection() ;
		PreparedStatement pst = connection.prepareStatement(sql);
	    ResultSet rows=pst.executeQuery();)
		{
        pst.setInt(1,travelId);
	   pst.setInt(2,userId);
	
	if(rows.next())
	{
		f= rows.getInt("ticket_count");
		
	}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}

	

	
	

}

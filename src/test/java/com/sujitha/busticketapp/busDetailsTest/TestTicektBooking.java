package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.BusListDAOImpl;
import com.sujitha.busticketapp.dao.impl.TicketBookingDAOImpl;
import com.sujitha.busticketapp.dto.BusSeatsBooked;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.model.BusList;
import com.sujitha.busticketapp.model.TicketBooking;

public class TestTicektBooking {
	private static final Logger log=Logger.getInstance();

	public static void main(String[] args) throws Exception {
		TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
		
		//Display no of seats booked by using travelid: 
		      // int bookedseats=tbl.getNoOfSeatsBooked(10);
		     //  System.out.println(bookedseats);
		//Display total payment:
             //int payment=tbl.totalPayment("booked");
              //System.out.println(payment);
		
		
		// Insert all data:
		       //testInsert();
        //Display total seats by using date:      
              //totalSeats();
		Booking b=new Booking();
		       List<Booking> list=new ArrayList<Booking>();
		       list=tbl.bookedUserDetails(1);
		       for (Booking booking : list) {
		    	   System.out.println(booking.toString());
		    	   
			}
		      		       
		       
		       
    
		       
					
		
	
	
	}
	

	
	
	
	
	
	
	
	
	public static void testInsert() throws DbException {
	TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
	List<TicketBooking> List=new ArrayList<TicketBooking>();
	TicketBooking tb=new TicketBooking();
	
	tb.setTravelId(10);
	tb.setUserId(12);
	tb.setFair(800);
	tb.setjDate(LocalDate.parse("2020-02-02"));
	tb.setBookedDate(LocalDate.parse("2020-01-31"));
	
	int f=  tbl.getSeatNo(tb.getTravelId(), tb.getUserId());
	System.out.println(f);
	tb.setNoOfSeatsBooked(f);
	tb.setPayment(tb.getNoOfSeatsBooked()*tb.getFair());
	if(tb.getPayment()!=0)
	{
		tb.setStatus("booked");
	}
	else
		tb.setStatus("cancelled");
	List.add(tb);
	for(TicketBooking ticket:List)
	{
		tbl.addBookingDetails(ticket);
		System.out.println(ticket);
	}
	
	
	
	}
	public static void totalSeats() throws DbException {
		TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
        ArrayList<BusSeatsBooked> seatsdetails= tbl.totalNoOfSeatsBooked("booked");
		for(BusSeatsBooked seats: seatsdetails) {
			System.out.println(seats);
		}
		
	}
}


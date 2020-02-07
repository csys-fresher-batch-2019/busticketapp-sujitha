package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.dao.impl.TicketBookingDAOImpl;
import com.sujitha.busticketapp.dto.BusSeatsBooked;
import com.sujitha.busticketapp.model.TicketBooking;

public class TestTicektBooking {

	public static void main(String[] args) throws Exception {
		TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
		
		//Display no of seats booked by using travelid: 
		       //int bookedseats=tbl.getNoOfSeatsBooked(10);
		       //System.out.println(bookedseats);
		//Display total payment:
             //int payment=tbl.totalPayment("booked");
             // System.out.println(payment);
		
		
		// Insert all data:
		       //testInsert();
        //Display total seats by using date:      
              //totalSeats();
	}
	

	
	
	
	
	
	
	
	
	public static void testInsert() throws Exception {
	TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
	List<TicketBooking> List=new ArrayList<TicketBooking>();
	TicketBooking tb=new TicketBooking();
	
	tb.travelId=10;
	tb.userId=12;
	tb.fair=800;
	tb.jDate=LocalDate.parse("2020-02-02");
	tb.bookedDate=LocalDate.parse("2020-01-31");
	
	int f=  tbl.getSeatNo(tb.travelId, tb.userId);
	System.out.println(f);
	tb.noOfSeatsBooked=f;
	tb.payment=tb.noOfSeatsBooked*tb.fair;
	if(tb.payment!=0)
	{
		tb.status="booked";
	}
	else
		tb.status="cancelled";
	List.add(tb);
	for(TicketBooking ticket:List)
	{
		tbl.addBookingDetails(ticket);
		System.out.println(ticket);
	}
	
	
	
	}
	public static void totalSeats() throws Exception {
		TicketBookingDAOImpl tbl = new TicketBookingDAOImpl();
        ArrayList<BusSeatsBooked> seatsdetails= tbl.totalNoOfSeatsBooked("booked");
		for(BusSeatsBooked seats: seatsdetails) {
			System.out.println(seats);
		}
		
	}
}


package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.sujitha.busticketapp.dao.impl.BookingDetailDAOImpl;
import com.sujitha.busticketapp.dao.impl.SeatDAOImpl;

import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class TestBooking {

	public static void main(String[] args) throws Exception {
		
		//testTicketBooking();
		
		ArrayList<Booking> booking = new ArrayList<Booking>();
		Booking b = new Booking();
		b.userId = 13;
		b.travelId = 10;
		
		b.busNum = 101;
		b.userGender = "F";
		b.bookedDate = LocalDate.parse("2019-12-10");
		b.genderPreference = "yes";
		
		booking.add(b);
		
		BookingDetailDAOImpl bg = new BookingDetailDAOImpl();
		
		for (Booking bookings : booking) {
			System.out.println(bookings);
			//bg.addUserBookingDetails(bookings);
		}
		//bg.addAvaialbleSeats(b,8);
		bg.bookUnfilledSeats(b);
		
		
		
		
		
		
		
		SeatDAOImpl sd=new SeatDAOImpl ();

		SeatService sv= new SeatService();
	
		
		
		
		
		
		
		
		/*	/* int no=sv.getNextSeatNo(b.bookedDate, b.busNum, b.userGender,b.genderPreference);
		 System.out.println(no); 
		 booking.add(b1); 
		 Booking b3=bg.searchBySeatNo(b. bookedDate,b. busNum,b. seatNo); 
		 System.out.println(b3.seatNo);
		 System.out.println(b3.userGender);*/
		 
		// String s= bg. getgenderPreferences(b.seatNo,b.userGender);
		// System.out.println(s);
		
		/* HashMap<Integer, String> h=new HashMap<Integer, String>();
		 h=bg.getSeatNoAndUserGender(101); 
		 for(int k:h.keySet()) 
		 { 
			 String val=h.get(k);
		System.out.println(k+" : "+val); 
		}*/
		
		/*int booked=sd.getBookedNumberOfSeats(101);
		System.out.println(booked);
	   int t=sd.getTotalNumberofSeats(101);
	   System.out.println(t);*/
		/*ArrayList<Integer> l=new ArrayList<Integer>();
		l=sd.getUnFilledSeatNo(b.bookedDate, b.busNum);
		for(int s:l)
		{
			System.out.println(s);
		}*/
		//sd.getInsertUnFiledSeats(b.busNum, 2, b.bookedDate);
		
		
	
	}
}
	
	
	
		


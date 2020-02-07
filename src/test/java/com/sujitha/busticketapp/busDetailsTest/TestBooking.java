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
		b.setUserId(13);
		b.setTravelId(10);
		
		b.setBusNum(101);
		b.setUserGender("F");
		b.setBookedDate(LocalDate.parse("2019-12-10"));
		b.setGenderPreference("yes");
		
		booking.add(b);
		
		BookingDetailDAOImpl bg = new BookingDetailDAOImpl();
		
		for (Booking bookings : booking) {
			log.getInput(bookings);
			//bg.addUserBookingDetails(bookings);
		}
		//bg.addAvaialbleSeats(b,8);
		bg.bookUnfilledSeats(b);
		
		
		
		
		
		
		
		SeatDAOImpl sd=new SeatDAOImpl ();

		SeatService sv= new SeatService();
	
		
		
		
		
		
		
		
		/* int no=sv.getNextSeatNo(b.getBookedDate(), b.getBusNum(), b.getUserGender(),b.getGenderPreference());
		 log.getInput(no); 
		 booking.add(b); 
		 Booking b3=bg.searchBySeatNo(b.getBookedDate(),b.getBusNum(),b.getSeatNo()); 
		 log.getInput(b3.getSeatNo());
		 log.getInput(b3.getUserGender());*/
		 
		// String s= bg. getgenderPreferences(b.getSeatNo(),b.getUserGender());
		// log.getInput(s);
		
		 /*HashMap<Integer, String> h=new HashMap<Integer, String>();
		 h=bg.getSeatNoAndUserGender(101); 
		 for(int k:h.keySet()) 
		 { 
			 String val=h.get(k);
		log.getInput(k+" : "+val); 
		}*/
		
		/*int booked=sd.getBookedNumberOfSeats(101);
		log.getInput(booked);
	   int t=sd.getTotalNumberofSeats(101);
	   log.getInput(t);*/
		/*ArrayList<Integer> l=new ArrayList<Integer>();
		l=sd.getUnFilledSeatNo(b.getBookedDate(), b.getBusNum());
		for(int s:l)
		{
			log.getInput(s);
		}*/
		//sd.getInsertUnFiledSeats(b.getBusNum(), 2, b.getBookedDate());
		
		
	
	}
}
	
	
	
		


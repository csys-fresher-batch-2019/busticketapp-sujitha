package com.sujitha.busticketapp.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.BookingDetailDAOImpl;
import com.sujitha.busticketapp.dao.impl.SeatDAOImpl;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;

public class SeatService {
	private static final Logger log=Logger.getInstance();

	
	public static int getNextSeatNo(LocalDate bookedDate, int BusNum) throws DbException {

		BookingDetailDAOImpl obj = new BookingDetailDAOImpl();
		int lastSeatNo = obj.getLastSeatNo(bookedDate, BusNum);
		int nextSeatNo = lastSeatNo + 1;
		return nextSeatNo;

	}
	
	public int getNextSeatNo(LocalDate bookedDate, int BusNum, String userGender,String genderPreference) throws DbException {

		BookingDetailDAOImpl obj = new BookingDetailDAOImpl();
		 SeatDAOImpl sdi=new SeatDAOImpl();
		int lastSeatNo = obj.getLastSeatNo(bookedDate, BusNum);
		int seatsBooked = sdi. getBookedNumberOfSeats(BusNum);
		int totalSeats =sdi.getTotalNumberofSeats(BusNum);
		System.out.println("Last Seat No :" + lastSeatNo);
		int nextSeatNo = 0;
		if (lastSeatNo == 0) {
			nextSeatNo = 1;
		} 
		else if ( lastSeatNo == totalSeats ) {
			if ( seatsBooked == totalSeats) {	
				System.out.println("Seat not available");
			}
			else if ( seatsBooked < totalSeats) {
				ArrayList<Integer> seatNo = sdi.getUnFilledSeatNo(bookedDate, BusNum);
				System.out.println("This seat no is free: " + seatNo);
			}
		
		}
		else {
			Booking bookingObj = obj.searchBySeatNo(bookedDate, BusNum, lastSeatNo);
			System.out.println(bookingObj);
			
			if (bookingObj.getGenderPreference().equals("no") && genderPreference.equals("no") ) {
				nextSeatNo = lastSeatNo + 1;

			} else if (lastSeatNo % 2 == 0) {
				nextSeatNo = lastSeatNo + 1;
			} else if (bookingObj.getUserGender().equals(userGender)) {
				nextSeatNo = lastSeatNo + 1;
			} else {
				nextSeatNo = lastSeatNo + 2;
			}
		}
		return nextSeatNo;

	}


public int getUnFiledSeats(int busNum,int seatNo,LocalDate bookedDate) {
	 int previousSeatNo=0;
	  if(seatNo%2==0)
	  {
		  previousSeatNo=seatNo-1;
	  }
	  else
	  {
		  previousSeatNo=seatNo+1;
	  }
	  System.out.println(previousSeatNo);

	return previousSeatNo;
}





}





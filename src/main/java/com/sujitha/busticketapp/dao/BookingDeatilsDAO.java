package com.sujitha.busticketapp.dao;



import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.model.Booking;

public interface BookingDeatilsDAO {
 //insert values
	public void addUserBookingDetails(Booking booking) throws DbException;
	

	
}

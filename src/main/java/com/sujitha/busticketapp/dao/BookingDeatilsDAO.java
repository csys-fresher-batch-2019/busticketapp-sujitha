package com.sujitha.busticketapp.dao;

import java.time.LocalDate;
import java.util.HashMap;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.model.Booking;

public interface BookingDeatilsDAO {

	public int getLastSeatNo(LocalDate bookedDate, int BusNum) throws DbException;

	public Booking searchBySeatNo(LocalDate bookedDate, int BusNum, int seatNo) throws DbException;

	public HashMap<Integer, String> getSeatNoAndUserGender(int busNum) throws DbException;

	public int addUserBookingDetails(Booking booking) throws DbException;

	public int addAvaialbleSeats(Booking booking, int seatNo) throws DbException;

	public int bookUnfilledSeats(Booking booking) throws DbException;

	public void cancelTicket(String bookingId);
}

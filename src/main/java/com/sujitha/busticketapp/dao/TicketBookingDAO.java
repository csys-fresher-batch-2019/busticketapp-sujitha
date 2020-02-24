package com.sujitha.busticketapp.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dto.BusSeatsBooked;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.model.TicketBooking;

public interface TicketBookingDAO {
	public void addBookingDetails(TicketBooking tic) throws DbException;

	public int getNoOfSeatsBooked(int travelId) throws DbException;

	public int totalPayment(String status) throws DbException;

	public ArrayList<BusSeatsBooked> totalNoOfSeatsBooked(String status) throws DbException;

	public int getSeatNo(int travelId, int userId) throws DbException;

	public List<Booking> bookedUserDetails(int userId) throws Exception;
}

package com.sujitha.busticketapp.dao;

import java.time.LocalDate;
import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dto.BusesDetails;
import com.sujitha.busticketapp.model.BusList;

public interface BusListDAO {
	public void busList(int busNum, String busName, int noOfSeats, String seatType) throws DbException;

	public void busNameUpdate(String busName, int busNum) throws DbException;

	public String busName(int busNum) throws DbException;

	public int noOfSeats(int busNum) throws DbException;

	public List<BusList> allBusListDetailss() throws DbException;

	public List<BusesDetails> allBusListDetails(int routeNo) throws DbException;

	public void deleteBusName(String busName) throws DbException;

	public int getAvailableSeats(int busNum, LocalDate bookedDate) throws DbException;
}

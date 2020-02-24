package com.sujitha.busticketapp.dao;

import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.model.BusDetails;

public interface BusDetailsDAO<Details> {
	public void fairUpdate(int fair, int travelId) throws DbException;

	public int availableSeats(int travelId) throws DbException;

	public void addBusDetails(BusDetails bus) throws DbException;

	public int fairDetails(int travelId) throws DbException;

	public ArrayList<BusFare> getFairDetails(String busName) throws DbException;

	public int getAvailableSeats() throws DbException;

	public String getBusName(String toLocation) throws DbException;

	public List<BusDetails> busdetails(BusDetails bus) throws DbException;
}

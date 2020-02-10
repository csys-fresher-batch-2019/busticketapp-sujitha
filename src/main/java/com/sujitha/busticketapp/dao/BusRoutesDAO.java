package com.sujitha.busticketapp.dao;


import java.util.List;

import com.sujitha.busticketapp.dao.impl.BusRoutesDAOImpl;
import com.sujitha.busticketapp.model.BusRoutes;

public interface BusRoutesDAO{
    
	//select to_location from busroutes where route_no=2;
	public String toLocation(int routeNo) throws DbException;
	// insert into busroutes(route_no,from_location,to_location)values(4,'chennai','Thirchy');
	public void busRouteAdd(int routeNo,String fromLocation,String toLocation) throws DbException;
	// update busroutes set route_id=2 where to_location='Banglore';
	public void routeNoUpdate(int routeId,String toLocation) throws DbException;
	// select route_no from busroutes where from_location =? and to_location=?
	public int getRouteNo(String fromLocation,String toLocation) throws DbException;
	//select * from busroutes;
	public List<BusRoutes> displayBusroutes() throws DbException;
	//select from_location from busroutes;
	public List<BusRoutes> fromLocations() throws DbException;
	//select to_location from busroutes;
	public List<BusRoutes> toLocations() throws DbException;
	
}


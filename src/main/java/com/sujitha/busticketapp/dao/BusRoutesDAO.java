package com.sujitha.busticketapp.dao;

import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.BusRoutesDAOImpl;
import com.sujitha.busticketapp.model.BusRoutes;

public interface BusRoutesDAO {
	public String toLocation(int routeNo) throws DbException;

	public void busRouteAdd(int routeNo, String fromLocation, String toLocation) throws DbException;

	public void routeNoUpdate(int routeNo, String toLocation) throws DbException;

	public int getRouteNo(String fromLocation, String toLocation) throws DbException;

	public List<BusRoutes> displayBusroutes() throws DbException;

	public List<BusRoutes> fromLocations() throws DbException;

	public List<BusRoutes> toLocations() throws DbException;

}

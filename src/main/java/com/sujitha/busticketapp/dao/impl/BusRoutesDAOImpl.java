package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.BusRoutesDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusDetails;
import com.sujitha.busticketapp.model.BusRoutes;

public class BusRoutesDAOImpl implements BusRoutesDAO {
	private static final Logger log = Logger.getInstance();

	public String toLocation(int routeNo) throws DbException {
		String s = "select to_location from busroutes where route_no=?";
		System.out.println(s);
		String str = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(s);) {
			pst.setInt(1, routeNo);
			try (ResultSet rows = pst.executeQuery();) {
				while (rows.next()) {
					str = rows.getString("to_location");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return str;
	}

	public void busRouteAdd(int routeNo, String fromLocation, String toLocation) throws DbException {
		String st = "insert into busroutes(route_no,from_location,to_location)values(?,?,?)";
		System.out.println(st);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(st);) {
			pst.setInt(1, routeNo);
			pst.setString(2, fromLocation);
			pst.setString(3, toLocation);
			int row = pst.executeUpdate();
			System.out.println(row);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void routeNoUpdate(int routeNo, String toLocation) throws DbException {
		String n = "update busroutes set to_location=? where route_no=?";
		System.out.println(n);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(n);) {
			pst.setString(1, toLocation);
			pst.setInt(2, routeNo);
			int row = pst.executeUpdate();
			System.out.println(row);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public int getRouteNo(String fromLocation, String toLocation) throws DbException {
		String sql = "select route_no from busroutes where from_location =? and to_location=?";
		System.out.println(sql);
		int e1 = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, fromLocation);
			pst.setString(2, toLocation);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					e1 = rs.getInt("route_no");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return e1;
	}

	public List<BusRoutes> displayBusroutes() throws DbException {
		String sql = "select * from busroutes";
		System.out.println(sql);
		List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusRoutes br = new BusRoutes();
					int routeNo1 = rs.getInt("route_no");
					String fromLocation1 = rs.getString("from_location");
					String toLocation1 = rs.getString("to_location");
					br.setRouteNo(routeNo1);
					br.setFromLocation(fromLocation1);
					br.setToLocation(toLocation1);
					busroutes.add(br);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return busroutes;
	}

	public List<BusRoutes> fromLocations() throws DbException {
		String sql = "select distinct from_location from busroutes ";
		System.out.println(sql);
		List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusRoutes br = new BusRoutes();
					String fromLocation1 = rs.getString("from_location");
					br.setFromLocation(fromLocation1);
					busroutes.add(br);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return busroutes;
	}

	public List<BusRoutes> toLocations() throws DbException {
		String sql = "select distinct to_location from busroutes ";
		System.out.println(sql);
		List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusRoutes br = new BusRoutes();
					String toLocation1 = rs.getString("to_location");
					br.setToLocation(toLocation1);
					busroutes.add(br);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return busroutes;
	}

}

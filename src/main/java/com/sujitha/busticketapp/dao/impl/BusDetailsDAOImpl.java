package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.BusDetailsDAO;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusDetails;
import com.sujitha.busticketapp.model.BusList;

public class BusDetailsDAOImpl implements BusDetailsDAO {
	private static final Logger log = Logger.getInstance();

	public void fairUpdate(int fair, int travelId) throws DbException {
		String strr = "update busdetails set fair =?where travel_id=?";
		System.out.println(strr);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(strr);) {
			pst.setInt(1, fair);
			pst.setInt(2, travelId);
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public int availableSeats(int travelId) throws DbException {
		String sql = "select (bl.no_of_seats-bd.available_seats)availableSeats from  buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=?";
		System.out.println(sql);
		int a = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, travelId);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					a = rs.getInt("availableseats");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return a;
	}

	public void addBusDetails(BusDetails bus) throws DbException {
		String sql = "insert into busdetails(travel_id,route_no,bus_num,travel_date,start_time,end_time,fair,available_seats)values(?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, bus.getTravelId());
			pst.setInt(2, bus.getRouteNo());
			pst.setInt(3, bus.getBusNum());
			pst.setDate(4, Date.valueOf(bus.getTravelDate()));
			pst.setString(5, bus.getStartTime().toString());
			pst.setString(6, bus.getEndTime().toString());
			pst.setInt(7, bus.getFair());
			pst.setInt(8, bus.getAvailableSeats());
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public int fairDetails(int travelId) throws DbException {
		String sql = "select fair as f  from busdetails where travel_id=?";
		System.out.println(sql);
		int b = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, travelId);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					b = rs.getInt("f");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return b;
	}

	public ArrayList<BusFare> getFairDetails(String busName) throws DbException {
		ArrayList<BusFare> busfares = new ArrayList<BusFare>();
		String sql = "select buslist.bus_name, busdetails.fair from buslist inner join busdetails on buslist.bus_num = busdetails.bus_num";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusFare bd = new BusFare();
					String name = rs.getString("bus_name");
					int fare = rs.getInt("fair");
					bd.setBusName(name);
					bd.setFare(fare);
					busfares.add(bd);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return busfares;
	}

	public int getAvailableSeats() throws DbException {
		String sql = "select tb.travel_id,bl.no_of_seats,bl.no_of_seats-sum(tb.no_of_seats_booked)  available_seats from buslist bl,ticket_booking tb,busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=tb.travel_id group by bl.no_of_seats,tb.travel_id,available_seats";
		System.out.println(sql);
		int d = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					int travelId = rs.getInt("travel_id");
					int noOfSeat = rs.getInt("no_of_seats");
					int availableSeat = rs.getInt("available_seats");
					System.out.println(travelId + "," + noOfSeat + "," + availableSeat);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return d;
	}

	public String getBusName(String toLocation) throws DbException {
		String sql = "select bus_name,no_of_seats from buslist where bus_num=(select bus_num from busdetails where route_no = (select route_no from busroutes where to_location= ? ))";
		System.out.println(sql);
		String e1 = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, toLocation);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					e1 = rs.getString("bus_name");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return e1;
	}

	public List<BusDetails> busdetails(BusDetails bus) throws DbException {
		ArrayList<BusDetails> busdetails = new ArrayList<BusDetails>();
		String sql = "select start_time,end_time,fair from busdetails where bus_num=?";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, bus.getBusNum());
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusDetails bl = new BusDetails();
					bl.setStartTime(rs.getString("start_time"));
					bl.setEndTime(rs.getString("end_time"));
					bl.setFair(rs.getInt("fair"));
					busdetails.add(bl);
				}
				connection.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return busdetails;
	}
}

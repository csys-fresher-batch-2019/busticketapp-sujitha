package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.BusListDAO;
import com.sujitha.busticketapp.dto.BusesDetails;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.model.BusList;

public class BusListDAOImpl implements BusListDAO {
	private static final Logger log = Logger.getInstance();

	public void busList(int busNum, String busName, int noOfSeats, String seatType) throws DbException {
		String sql = "insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(?,?,?,?)";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, busNum);
			pst.setString(2, busName);
			pst.setInt(3, noOfSeats);
			pst.setString(4, seatType);
			if (noOfSeats <= 10) {
				int row = pst.executeUpdate();
				System.out.println(row);
			}
			connection.close();
		} catch (SQLException e) {
			log.error(e);
		}
}

	public void busNameUpdate(String busName, int busNum) throws DbException {
		String s = "update buslist set bus_name =? where bus_num=?";
		System.out.println(s);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(s);) {
			pst.setString(1, busName);
			pst.setInt(2, busNum);
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public String busName(int busNum) throws DbException {
		String name = "select bus_name from buslist where bus_num=?";
		System.out.println(name);
		String s = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(name);) {
			pst.setInt(1, busNum);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					s = rs.getString("bus_name");
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return s;
	}

	public int noOfSeats(int busNum) throws DbException {
		String seats = "select no_of_seats from buslist where bus_num=?";
		System.out.println(seats);
		int s = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(seats);) {
			pst.setInt(1, busNum);
			try (ResultSet rows = pst.executeQuery();) {

				while (rows.next()) {
					s = rows.getInt("no_of_seats");
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return s;
	}

	public List<BusList> allBusListDetailss() throws DbException {
		List<BusList> list = new ArrayList<BusList>();
		String sql = "select*from buslist";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusList bl = new BusList();
					bl.setBusNum(rs.getInt("bus_num"));
					bl.setBusName(rs.getString("bus_name"));
					bl.setNoOfSeats(rs.getInt("no_of_seats"));
					bl.setSeatType(rs.getString("seat_type"));
					list.add(bl);
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return list;
	}

	public List<BusesDetails> allBusListDetails(int routeNo) throws DbException {
		List<BusesDetails> list1 = new ArrayList<BusesDetails>();
		String sql = "select b.bus_num,bl.bus_name,bl.no_of_seats,bl.seat_type,b.start_time,b.end_time,b.fair from buslist bl,busdetails b where bl.bus_num=b.bus_num and b.route_no=?";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, routeNo);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					BusesDetails b = new BusesDetails();
					b.setBusNum(rs.getInt("bus_num"));
					b.setBusName(rs.getString("bus_name"));
					b.setNoOfSeats(rs.getInt("no_of_seats"));
					b.setSeatType(rs.getString("seat_type"));
					b.setStartTime(rs.getString("start_time"));
					b.setEndTime(rs.getString("end_time"));
					b.setFair(rs.getInt("fair"));
					list1.add(b);
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return list1;
	}

	public void deleteBusName(String busName) throws DbException {

		String sql = "delete from buslist where bus_name=?";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			try {
				pst.setString(1, busName);
				int row = pst.executeUpdate(sql);
				System.out.println(row);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println("There are child records found");
			}
			connection.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public int getAvailableSeats(int busNum, LocalDate bookedDate) throws DbException {
		String s = "select (no_of_seats - ( select nvl(sum(seat_no),0) from booking bg where bg.bus_num = bl.bus_num and booked_date= ? )) as no_of_seats from buslist bl where bus_num =?";
		System.out.println(s);
		int noOfSeat = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(s);) {
			Date d = Date.valueOf(bookedDate);
			pst.setDate(1, d);
			pst.setInt(2, busNum);
			ResultSet rows = pst.executeQuery();
			if (rows.next()) {
				noOfSeat = rows.getInt("no_of_seats");
			}
			System.out.println(noOfSeat);
			connection.close();
		} catch (SQLException e) {
			log.error(e);
		}
		return noOfSeat;
	}
}

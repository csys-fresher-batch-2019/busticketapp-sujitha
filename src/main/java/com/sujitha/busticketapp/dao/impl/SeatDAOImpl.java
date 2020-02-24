package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class SeatDAOImpl {
	private static final Logger log = Logger.getInstance();

	public int getBookedNumberOfSeats(int BusNum) throws DbException {
		String s = "select seat_no from booking where bus_num=?";
		System.out.println(s);
		int b = 0;
		ArrayList<Integer> l = new ArrayList<Integer>();
		int c = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(s);) {
			pst.setInt(1, BusNum);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					b = rs.getInt("seat_no");
					c++;
					l.add(b);
					System.out.println(b);
				}
				System.out.println("Count : " + c);
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return c;
	}

	public int getTotalNumberofSeats(int BusNum) throws DbException {
		String s = "select no_of_seats from buslist where bus_num=?";
		System.out.println(s);
		int a = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(s);) {
			pst.setInt(1, BusNum);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					a = rs.getInt("no_of_seats");
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return a;

	}

	public ArrayList<Integer> getUnFilledSeatNo(LocalDate bookedDate, int busNum) throws DbException {
		String sql = "select min_seat_no -1+level missing_number from (select min(1) min_seat_no,max(10) max_seat_no from booking)connect by  level <=max_seat_no-min_seat_no+1 minus select seat_no  as available_seats from booking where bus_num=? and booked_date=?";
		System.out.println(sql);
		int a1 = 0;
		ArrayList<Integer> unSeats = new ArrayList<Integer>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, busNum);
			pst.setDate(2, Date.valueOf(bookedDate));
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					unSeats.add(rs.getInt(1));
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return unSeats;
	}

	public HashMap<String, String> getInsertUnFiledSeats(int busNum, int seatNo, LocalDate bookedDate)
			throws DbException {
		String sql = "select user_gender,gender_preferences from booking where booked_date=? and bus_num=? and seat_no=? ";
		SeatService s = new SeatService();
		String s1 = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			int previousSeatNo = s.getUnFiledSeats(busNum, seatNo, bookedDate);
			pst.setDate(1, Date.valueOf(bookedDate));
			pst.setInt(2, busNum);
			pst.setInt(3, previousSeatNo);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					hm.put(rs.getString("user_gender"), rs.getString("gender_preferences"));
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return (hm);

	}
}

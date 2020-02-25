package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.BookingDeatilsDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.service.SeatService;

public class BookingDetailDAOImpl implements BookingDeatilsDAO {
	private static final Logger log = Logger.getInstance();

	public int getLastSeatNo(LocalDate bookedDate, int BusNum) throws DbException {
		int seats = 0;
		String sql = " select max(seat_no) as seat_no from booking where booked_date=? and bus_num= ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setDate(1, Date.valueOf(bookedDate));
			pst.setInt(2, BusNum);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					seats = rs.getInt("seat_no");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return seats;
	}

	public Booking searchBySeatNo(LocalDate bookedDate, int BusNum, int seatNo) throws DbException {
		String sql = " select seat_no, user_gender,gender_preferences from booking where booked_date=? and bus_num= ? and seat_no =?";
		Booking b = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setDate(1, Date.valueOf(bookedDate));
			pst.setInt(2, BusNum);
			pst.setInt(3, seatNo);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					b = new Booking();
					b.setSeatNo(rs.getInt("seat_no"));
					b.setUserGender(rs.getString("user_gender"));
					b.setGenderPreference(rs.getString("gender_preferences"));
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return b;
	}

	public HashMap<Integer, String> getSeatNoAndUserGender(int busNum) throws DbException {
		String userGender1 = "F";
		String sql = "select seat_no,user_gender from booking where bus_num=busNum";
		HashMap<Integer, String> m = new HashMap<Integer, String>();
		try (Connection connection = DbConnection.getConnection(); Statement stmt = connection.createStatement();) {
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					int seatNo = rs.getInt("seat_no");
					String gender = rs.getString("user_gender");
					m.put(seatNo, gender);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return m;
	}

	public int addUserBookingDetails(Booking booking) throws DbException {
		SeatDAOImpl sd = new SeatDAOImpl();
		SeatService ss = new SeatService();
		int nextSeatNo = ss.getNextSeatNo(booking.getBookedDate(), booking.getBusNum());
		booking.setSeatNo(nextSeatNo);
		int nextSeatNo1 = ss.getNextSeatNo(booking.getBookedDate(), booking.getBusNum(), booking.getUserGender(),
				booking.getGenderPreference());
		booking.setSeatNo(nextSeatNo1);
		int rows = 0;
		// String s= getgenderPreferences(booking.getSeatNo(),booking.getUserGender());
		// System.out.println(s);
		String sql1 = "select no_of_seats from buslist where bus_num=?";
		String str = "insert into booking(booking_id,user_id,bus_num,user_gender,seat_no,booked_date,gender_preferences,amount) values(booked_id.nextval,?,?,?,?,?,?,?)";
		System.out.println(str);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst1 = connection.prepareStatement(sql1);
				PreparedStatement pst = connection.prepareStatement(str);) {
			pst1.setInt(1, booking.getBusNum());
			int totalSeats = 0;
			try (ResultSet rs = pst1.executeQuery();) {
				if (rs.next()) {
					totalSeats = rs.getInt("no_of_seats");
				}
				boolean isSeatsAvailable = totalSeats >= booking.getSeatNo();
				System.out.println("SeatsAvailable:" + isSeatsAvailable + ",totalSeats=" + totalSeats
						+ ",booking Seat NO:" + booking.getSeatNo());
				if (isSeatsAvailable) {
					pst.setInt(1, booking.getUserId());
					pst.setInt(2, booking.getBusNum());
					pst.setString(3, booking.getUserGender());
					pst.setInt(4, booking.getSeatNo());
					pst.setDate(5, Date.valueOf(booking.getBookedDate()));
					pst.setString(6, booking.getGenderPreference());
					pst.setInt(7, booking.getAmount());
					if (booking.getSeatNo() != 0) {
						rows = pst.executeUpdate();
						System.out.println(rows);
					} else
						System.out.println("seats are not available");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return (rows);
	}

	public int addAvaialbleSeats(Booking booking, int seatNo) throws DbException {
		String str = "insert into booking(booking_id,user_id,bus_num,user_gender,seat_no,booked_date,gender_preferences,amount) values(booked_id.nextval,?,?,?,?,?,?,?)";
		System.out.println(str);
		int flag = 0;
		HashMap<String, String> hm = new HashMap<String, String>();
		SeatDAOImpl si = new SeatDAOImpl();
		String gender = null;
		String gender_preference = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(str);) {
			hm = si.getInsertUnFiledSeats(booking.getBusNum(), seatNo, booking.getBookedDate());
			for (String s : hm.keySet()) {
				gender = s;
				gender_preference = hm.get(s);
			}
			System.out.println(gender + "  " + gender_preference);
			pst.setInt(1, booking.getUserId());
			pst.setInt(2, booking.getBusNum());
			pst.setString(3, booking.getUserGender());
			pst.setInt(4, seatNo);
			pst.setDate(5, Date.valueOf(booking.getBookedDate()));
			pst.setString(6, booking.getGenderPreference());
			pst.setInt(7, booking.getAmount());
			if (booking.getUserGender().equals(gender) && booking.getGenderPreference().equals(gender_preference)) {
				int rows = pst.executeUpdate();
				System.out.println("seats are available");
				if (rows == 1) {
					flag = 1;
				}
			} else if (booking.getUserGender().equals(gender)
					&& (!booking.getGenderPreference().equals(gender_preference))) {
				int rows = pst.executeUpdate();
				if (rows == 1) {
					flag = 1;
				}
				System.out.println("seats are available");
			} else if (!booking.getUserGender().equals(gender)
					&& (booking.getGenderPreference().equals("no") && gender_preference.equals("no"))) {
				int rows = pst.executeUpdate();
				if (rows == 1) {
					flag = 1;
				}
				System.out.println("seats are available");
			} else {
				System.out.println("seats are not available");
			}
			connection.close();

		} catch (Exception e) {
			log.error(e);
		}
		return (flag);
	}

	public int bookUnfilledSeats(Booking booking) throws DbException {
		SeatDAOImpl sdi = new SeatDAOImpl();
		int res = 0;
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ai = sdi.getUnFilledSeatNo(booking.getBookedDate(), booking.getBusNum());
		System.out.println("***Un filled seats***\n");
		for (int seat : ai) {
			res = addAvaialbleSeats(booking, seat);
			if (res == 1)
				break;
		}
		return (res);
	}

	@Override
	public void cancelTicket(String bookingId) {
		String sql = "update booking set status='CANCELLED' where booking_id = ?";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, bookingId);
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}
}

package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.TicketBookingDAO;
import com.sujitha.busticketapp.dto.BusSeatsBooked;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Booking;
import com.sujitha.busticketapp.model.TicketBooking;

public class TicketBookingDAOImpl implements TicketBookingDAO {
	private static final Logger log = Logger.getInstance();

	public void addBookingDetails(TicketBooking tic) throws DbException {
		String sql = "insert into ticket_booking(travel_id,no_of_seats_booked,user_id,fair,j_date,booked_date,payment,status) values(?,?,?,?,?,?,?,?)";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, tic.getTravelId());
			pst.setInt(2, tic.getNoOfSeatsBooked());
			pst.setInt(3, tic.getUserId());
			pst.setInt(4, tic.getFair());
			pst.setDate(5, Date.valueOf(tic.getjDate()));
			pst.setDate(6, Date.valueOf(tic.getBookedDate()));
			pst.setInt(7, tic.getPayment());
			pst.setString(8, tic.getStatus());
			int row = pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public int getNoOfSeatsBooked(int travelId) throws DbException {
		String n = " select sum(no_of_seats_booked) no_of_seats_booked from ticket_booking where travel_id=? ";
		System.out.println(n);
		int s = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(n);
				ResultSet rows = pst.executeQuery();) {
			pst.setInt(1, travelId);
			if (rows.next()) {
				s = rows.getInt("no_of_seats_booked");
			}
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
		return s;
	}

	public int totalPayment(String status) throws DbException {
		String sql = "select sum(payment) as payment from ticket_booking where status=?";
		System.out.println(sql);
		int f = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rows = pst.executeQuery();) {
			pst.setString(1, status);
			if (rows.next()) {
				f = rows.getInt("payment");
			}
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
		return f;
	}

	public ArrayList<BusSeatsBooked> totalNoOfSeatsBooked(String status) throws DbException {
		String sql = "select booked_date,count(no_of_seats_booked) as total_seats from ticket_booking where status= ? group by booked_date";
		System.out.println(sql);
		ArrayList<BusSeatsBooked> list = new ArrayList<BusSeatsBooked>();
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rows = pst.executeQuery();) {
			pst.setString(1, status);
			while (rows.next()) {
				int s = rows.getInt("total_seats");
				Date si = rows.getDate("booked_date");
				LocalDate ld = si.toLocalDate();
				BusSeatsBooked bsd = new BusSeatsBooked();
				bsd.setTotalseats(s);
				bsd.setBookedDate(ld);
				list.add(bsd);
			}
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	public int getSeatNo(int travelId, int userId) throws DbException {
		String sql = "select  count(b.seat_no) as ticket_count from booking b  where travel_id=?  and user_id=?";
		System.out.println(sql);
		int f = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rows = pst.executeQuery();) {
			pst.setInt(1, travelId);
			pst.setInt(2, userId);
			if (rows.next()) {
				f = rows.getInt("ticket_count");
			}
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
		return f;
	}

	public List<Booking> bookedUserDetails(int userId) throws Exception {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "select * from booking where user_id=? order by booked_date desc";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, userId);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					Booking bl = new Booking();
					bl.setBookingId(rs.getLong("booking_id"));
					bl.setBusNum(rs.getInt("bus_num"));
					bl.setUserId(rs.getInt("user_id"));
					bl.setUserGender(rs.getString("user_gender"));
					bl.setSeatNo(rs.getInt("seat_no"));
					Date id = rs.getDate("booked_date");
					if (id != null) {
						LocalDate ld = id.toLocalDate();
						bl.setBookedDate(ld);
					}
					bl.setGenderPreference(rs.getString("gender_preferences"));
					Date d = rs.getDate("created_date");
					LocalDate d1 = d.toLocalDate();
					bl.setCreatedDate(d1);
					bl.setStatus(rs.getString("status"));
					bl.setAmount(rs.getInt("amount"));
					list.add(bl);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}
}

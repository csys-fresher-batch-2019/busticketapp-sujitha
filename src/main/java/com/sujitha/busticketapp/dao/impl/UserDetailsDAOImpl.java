package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.UserDetailsDAO;
import com.sujitha.busticketapp.dto.UserGenderEnum;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.UserDetails;

public class UserDetailsDAOImpl implements UserDetailsDAO {
	private static final Logger log = Logger.getInstance();

	public void getUserDetails(String userName, long userPhnNum, String userGender, String password)
			throws DbException {
		String str = "insert into user_details(user_id ,user_name,user_phn_num,user_gender,password)values(user_id_seq.nextval,?,?,?,?)";
		System.out.println(str);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(str);) {
			pst.setString(1, userName);
			pst.setLong(2, userPhnNum);
			pst.setString(3, userGender);
			pst.setString(4, password);
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void updateUserPhnNum(int userId, long userPhnNum) throws DbException {
		try (Connection connection = DbConnection.getConnection();) {
			String sql = "update user_details set user_phn_num=? where user_id=?";
			System.out.println(sql);
			try (PreparedStatement pst = connection.prepareStatement(sql);) {
				pst.setLong(1, userPhnNum);
				pst.setInt(2, userId);
				int row = pst.executeUpdate(sql);
				System.out.println(row);
			}
			String ss = "select user_id,user_name,user_phn_num from user_details where user_id=?";
			System.out.println(ss);
			try (PreparedStatement pst1 = connection.prepareStatement(ss); ResultSet rs = pst1.executeQuery(ss);) {
				pst1.setLong(1, userId);
				while (rs.next()) {
					int id = rs.getInt("user_id");
					String username = rs.getString("user_phn_num");
					System.out.println(id + "-" + username);
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	public String getUserGender(int userId) throws DbException {
		String strn = "select user_gender from user_details where user_id=?";
		System.out.println(strn);
		String a = null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(strn);) {
			pst.setInt(1, userId);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					a = rs.getString("user_gender");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return a;
	}

	public int getGenderCount(String userGender) throws DbException {
		String sql = "select count (*)user_gender from user_details where user_gender=?";
		System.out.println(sql);
		int v = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, userGender);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					v = rs.getInt("user_gender");

				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return v;
	}

	/*
	 * public ArrayList<UserDetails> getUserDetails() throws DbException {
	 * ArrayList<UserDetails> list=new ArrayList<UserDetails>(); Connection
	 * connection =DbConnection.getConnection(); String
	 * sql="select * from user_details"; System.out.println(sql); Statement
	 * stmt=connection.createStatement(); ResultSet rs=stmt.executeQuery(sql);
	 * 
	 * while(rs.next()) { UserDetails uds=new UserDetails();
	 * uds.userId=rs.getInt("user_id"); uds.userName=rs.getString("user_name");
	 * uds.userPhnNum=rs.getLong("user_phn_num");
	 * uds.userGender=UserGenderEnum.valueOf(rs.getString("user_gender"));
	 * list.add(uds); } return list; }
	 */

	public void addUserDetails(UserDetails userdetails) throws DbException {
		String sql = "insert into user_details(user_id ,user_name,user_phn_num,user_gender)values(?,?,?,?)";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, userdetails.getUserId());
			pst.setString(2, userdetails.getUserName());
			pst.setLong(3, userdetails.getUserPhnNum());
			pst.setString(4, userdetails.getUserGender().toString());
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void updateUG(int userID, UserGenderEnum userGender) throws DbException {
		String sql = "UPDATE USER_DETAILS SET USER_GENDER=? WHERE USER_ID=?";
		System.out.println(sql);
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, userGender.toString());
			pst.setInt(2, userID);
			int rows = pst.executeUpdate();
			System.out.println(rows);
			connection.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public boolean login(long userPhnNum, String password) throws DbException {
		String sql = "select user_phn_num,password from user_details where user_phn_num=? and password = ?";
		System.out.println(sql);
		boolean msg = true;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setLong(1, userPhnNum);
			pst.setString(2, password);
			try (ResultSet row = pst.executeQuery()) {
				if (row.next()) {
					msg = true;
					log.getInput("Successfully Login");
				} else {
					msg = false;
					log.getInput("Login Details are invalid");
				}
				connection.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return msg;
	}

	public int getUserId(long userPhnNum, String password) throws DbException {
		String sql = "select user_id from user_details where user_phn_num=?and password=?";
		System.out.println(sql);
		int v = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setLong(1, userPhnNum);
			pst.setString(2, password);
			try (ResultSet row = pst.executeQuery()) {
				if (row.next()) {
					v = row.getInt("user_Id");
				}
				connection.close();
			}
		} catch (Exception e) {

			log.error(e);
		}
		return v;
	}
}

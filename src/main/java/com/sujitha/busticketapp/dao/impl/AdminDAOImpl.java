package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.AdminDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.Admin;

public class AdminDAOImpl implements AdminDAO {
	private static final Logger log = Logger.getInstance();

	public int getId(String adminMailId, String adminPassword) {
		String sql = "select admin_id from admin where admin_mail=?and admin_password=?";
		Admin a = new Admin();
		int v = 0;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setString(1, adminMailId);
			pst.setString(2, adminPassword);

			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					v = rs.getInt("admin_id");
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return v;

	}
}
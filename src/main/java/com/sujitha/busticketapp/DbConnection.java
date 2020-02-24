package com.sujitha.busticketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getConnection(){
		Connection connection=null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		 connection=DriverManager.getConnection(url,"system","oracle");
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Driver class not found");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		throw new RuntimeException("invalid DB credentials"+e.getMessage());
		
		}
		
		return connection;
	}

}

package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BusRoutesDAO;
import com.sujitha.busticketapp.model.BusRoutes;

public  class BusRoutesDAOImpl implements BusRoutesDAO {

	

	public String toLocation(int routeNo) throws Exception {
		Connection connection =DbConnection.getConnection() ;
		String s="select to_location from busroutes where route_no="+routeNo+"";
		System.out.println(s);
		Statement stmt=connection.createStatement();
		ResultSet rows=stmt.executeQuery(s);
		String str= null;
		while(rows.next())
		{
	     str= rows.getString("to_location");
		}
		return str;
	}

	public void busRouteAdd(int routeNo, String fromLocation, String toLocation) throws Exception {
		Connection connection =DbConnection.getConnection() ;
		  String st="insert into busroutes(route_no,from_location,to_location)values('"+routeNo+"','"+fromLocation+"','"+toLocation+"')";
		  System.out.println(st);
		Statement stm=connection.createStatement();
		int row=stm.executeUpdate(st);
		System.out.println(row);
	}

	public void routeNoUpdate(int routeNo, String toLocation) throws Exception {
		Connection connection =DbConnection.getConnection() ;
		String n = "update busroutes set to_location='"+toLocation+"' where route_no='"+routeNo+"'";
	     System.out.println(n);
	     Statement stm=connection.createStatement();
			int row=stm.executeUpdate(n);
	      
			System.out.println(row);
		
	}

	
}
	
		

	
		

	

	
	



package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BusRoutesDAO;
import com.sujitha.busticketapp.model.BusRoutes;

public  class BusRoutesDAOImpl implements BusRoutesDAO {

	

	public String toLocation(int routeNo) throws Exception {
		String s="select to_location from busroutes where route_no=?";
		System.out.println(s);
		String str= null;
		try(Connection connection =DbConnection.getConnection() ;
		
		PreparedStatement pst = connection.prepareStatement(s);
		ResultSet rows=pst.executeQuery();)
		{
		
		pst.setInt(1,routeNo);
		while(rows.next())
		{
	     str= rows.getString("to_location");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return str;
	}

	public void busRouteAdd(int routeNo, String fromLocation, String toLocation) throws Exception {
		String st="insert into busroutes(route_no,from_location,to_location)values(?,?,?)";
		  System.out.println(st);
		try(Connection connection =DbConnection.getConnection() ;
		  
		  PreparedStatement pst = connection.prepareStatement(st);)
		{
		  pst.setInt(1,routeNo);
		  pst.setString(2,fromLocation);
		  pst.setString(3,toLocation);
		  int row=pst.executeUpdate(st);
		System.out.println(row);
	}catch(Exception e)
		{
		e.printStackTrace();
	}
	}

	public void routeNoUpdate(int routeNo, String toLocation) throws Exception {
		String n = "update busroutes set to_location=? where route_no=?";
	     System.out.println(n);
		try(Connection connection =DbConnection.getConnection() ;
		
	     PreparedStatement pst = connection.prepareStatement(n);)
		{
	     pst.setString(1,toLocation);
		  pst.setInt(2,routeNo);
			int row=pst.executeUpdate(n);
	      
			System.out.println(row);
		
	}catch(Exception e)
		{
		e.printStackTrace();
	}
	}

	public int getRouteNo(String fromLocation, String toLocation) throws Exception {
		String sql="select route_no from busroutes where from_location =? and to_location=?";
		System.out.println(sql);
		int e1 = 0;
		try(Connection connection =DbConnection.getConnection() ;
		
		 PreparedStatement pst = connection.prepareStatement(sql);)
		{
			pst.setString(1,fromLocation);
			pst.setString(2,toLocation);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
			e1=rs.getInt("route_no");
			
		}
		}catch(Exception e)
		{
		e.printStackTrace();
	}
	 return e1;
		
	}

	public List<BusRoutes> displayBusroutes() throws Exception {
		String sql="select * from busroutes";
		 System.out.println(sql);
		 List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		 BusRoutes br=new BusRoutes();
		try(Connection connection =DbConnection.getConnection() ;
		
		 PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();)
		{
			
			while(rs.next()) {
				
				int routeNo1=rs.getInt("route_no");
				String fromLocation1=rs.getString("from_location");
				 String toLocation1=rs.getString("to_location");
				 br.routeNo=routeNo1;
				 br.fromLocation=fromLocation1;
				 br.toLocation=toLocation1;
				 busroutes.add(br);
			}
		}catch(Exception e)
		{
		e.printStackTrace();
	}
		return busroutes ;
	}

	public List<BusRoutes> fromLocations() throws Exception {
		String sql="select from_location from busroutes";
		 System.out.println(sql);
		 List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		try(Connection connection =DbConnection.getConnection() ;
		
		 PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();)
		{
			
			while(rs.next()) {
				BusRoutes br=new BusRoutes();
				String fromLocation1=rs.getString("from_location");
				 br.fromLocation=fromLocation1;
				 busroutes.add(br);
			}
		}	catch(Exception e)
		{
		e.printStackTrace();
	}	
		return busroutes ;
	}

	public List<BusRoutes> toLocations() throws Exception {
		String sql="select to_location from busroutes ";
		 System.out.println(sql);
		 List<BusRoutes> busroutes = new ArrayList<BusRoutes>();
		 BusRoutes br=new BusRoutes();
		try(Connection connection =DbConnection.getConnection() ;
		
		 PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();)
		{
			
			while(rs.next()) {
				
				
				 String toLocation1=rs.getString("to_location");
				
				 br.toLocation=toLocation1;
				 busroutes.add(br);
			}
		}	catch(Exception e)
		{
		e.printStackTrace();
	}		
		return busroutes ;
	}
	
	
}
	
		

	
		

	

	
	



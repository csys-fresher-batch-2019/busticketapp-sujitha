package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BusDetailsDAO;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.model.BusDetails;

public class BusDetailsDAOImpl implements BusDetailsDAO{

	

	public void fairUpdate(int fair, int travelId) throws Exception {
     String strr ="update busdetails set fair =?where travel_id=?"; 
		
		System.out.println(strr);
		try(Connection connection =DbConnection.getConnection() ;
		
		PreparedStatement pst = connection.prepareStatement(strr);)
		{
		pst.setInt(1,fair);
		pst.setInt(2,travelId);
		int rows=pst.executeUpdate();
		System.out.println(rows);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public int availableSeats(int travelId) throws Exception {
		String sql="select (bl.no_of_seats-bd.available_seats)availableSeats from  buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=?";
		System.out.println(sql);
		int a=0;
		try(Connection connection =DbConnection.getConnection() ;

		PreparedStatement pst = connection.prepareStatement(sql);ResultSet rs=pst.executeQuery();)
		{
		pst.setInt(1,travelId);
		
		
		while(rs.next())
		{
			a= rs.getInt("availableseats");
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return a;
	}

	public void addBusDetails(BusDetails bus) throws Exception {
		String sql="insert into busdetails(travel_id,route_no,bus_num,travel_date,start_time,end_time,fair,available_seats)values(?,?,?,?,?,?,?,?)"  ;       
		 System.out.println(sql);
		try(Connection connection =DbConnection.getConnection() ;
		
				 PreparedStatement pst = connection.prepareStatement(sql);)
		{
					pst.setInt(1,bus.travelId);
					pst.setInt(2,bus.routeNo);
					pst.setInt(3,bus.busNum);
					pst.setDate(4,Date.valueOf(bus.travelDate));
					pst.setString(5,bus.startTime.toString());
					pst.setString(6, bus.endTime.toString());
					pst.setInt(7,bus.fair);
					pst.setInt(8,bus.availableSeats);
					
					int rows=pst.executeUpdate();
					System.out.println(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int fairDetails(int travelId) throws Exception {
		String sql ="select fair as f  from busdetails where travel_id=?";
		System.out.println(sql);
		int b = 0;
	try(	Connection connection =DbConnection.getConnection() ;
		PreparedStatement pst = connection.prepareStatement(sql);
	    ResultSet rs=pst.executeQuery();)
	{
        
       if(rs.next())
    {
	 b=rs.getInt("f");
	
    }
	}	catch(Exception e)
	{
		e.printStackTrace();
	}
		return b;
	}

	public ArrayList<BusFare> getFairDetails(String busName) throws Exception {
		ArrayList<BusFare> busfares = new ArrayList<BusFare>();

		String sql="select buslist.bus_name, busdetails.fair from buslist inner join busdetails on buslist.bus_num = busdetails.bus_num";
		 System.out.println(sql);
		 BusFare bd = new BusFare();
		try(Connection connection =DbConnection.getConnection() ;
				PreparedStatement pst = connection.prepareStatement(sql);
		    ResultSet rs=pst.executeQuery();)
		{
				while(rs.next())
		{
		String name=rs.getString("bus_name");
		int fare=rs.getInt("fair");
		
		
		bd.busName = name;
		bd.fare = fare;
		
		busfares.add(bd);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return busfares;
	
	}

	public int getAvailableSeats() throws Exception {
		String sql="select tb.travel_id,bl.no_of_seats,bl.no_of_seats-sum(tb.no_of_seats_booked)  available_seats from buslist bl,ticket_booking tb,busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=tb.travel_id group by bl.no_of_seats,tb.travel_id,available_seats";
		System.out.println(sql);
		int d =0;
		try(Connection connection =DbConnection.getConnection() ;
				PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();)
		{
        
       while(rs.next())
    {
	int travelId=rs.getInt("travel_id");
	int noOfSeat=rs.getInt("no_of_seats");
	int availableSeat=rs.getInt("available_seats");
    System.out.println(travelId+","+noOfSeat+","+availableSeat);
    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
       return d;
	}

	public String getBusName(String toLocation) throws Exception {
		String sql="select bus_name,no_of_seats from buslist where bus_num=(select bus_num from busdetails where route_no = (select route_no from busroutes where to_location= ? ))";
		 System.out.println(sql);
		 String e1 = null;
		try(Connection connection =DbConnection.getConnection() ;
		
	 PreparedStatement pst = connection.prepareStatement(sql);ResultSet rs=pst.executeQuery();)
		{
		pst.setString(1,toLocation);
		
		
		if(rs.next())
		{
		e1=rs.getString("bus_name");
		
	}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
 return e1;
	}
}
		

	

	

	
	
	
	


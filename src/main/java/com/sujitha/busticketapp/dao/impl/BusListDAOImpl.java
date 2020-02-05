package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BusListDAO;
import com.sujitha.busticketapp.model.BusList;

public class BusListDAOImpl implements BusListDAO  {
	//public void BusListDisplay(int busNum,String busName,int noOfSeats,String seatType) {
	//	String str="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+ busNum +"','"+busName+"','"+noOfSeats+"','"+seatType+"')";
		//System.out.println(str);
		
	//}
	//public void ListDisplay(BusList buslist) {
		//String sql="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+buslist.busNum +"','"+buslist.busName+"','"+buslist.noOfSeats+"','"+buslist.seatType+"')";
		//System.out.println(sql);
	//}
	public void busList(int busNum, String busName, int noOfSeats, String seatType) throws Exception  {
		Connection connection =DbConnection.getConnection() ;
		
		String sql="insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values('"+busNum+"','"+busName+"','"+noOfSeats+"','"+seatType+"')";
		System.out.println(sql);
		Statement stmt=connection.createStatement();
		if(noOfSeats<=10)
		{
				int row=stmt.executeUpdate(sql);
				System.out.println(row);
		}
		
	}
	public void busNameUpdate(String busName, int busNum) throws Exception  {
		Connection connection =DbConnection.getConnection() ;
		String s="update buslist set bus_name =? where bus_num=?";
				System.out.println(s);
				PreparedStatement pst = connection.prepareStatement(s);
				pst.setString(1,busName);
				pst.setInt(2,busNum);
				int rows=pst.executeUpdate();
				System.out.println(rows);
}
	public String busName(int busNum) throws Exception {
		Connection connection = DbConnection.getConnection();
		String name="select bus_name from buslist where bus_num='"+busNum+"'";
		System.out.println(name);
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery(name);
		String s = null;
		while(rs.next())
		{
			 s= rs.getString("bus_name");
			
		}
	return s;
		
	}
	public int noOfSeats(int busNum) throws Exception {
		Connection connection=DbConnection.getConnection();
		String seats="select no_of_seats from buslist where bus_num="+busNum+"";
		System.out.println(seats);
		Statement st = connection.createStatement();
		ResultSet rows=st.executeQuery(seats);
		int s = 0;
		while(rows.next())
		{
			 s= rows.getInt("no_of_seats");
			
		}
		 return s;
	}
	public List<BusList> allBusListDetails() throws Exception {
		List<BusList> list=new ArrayList<BusList>();
		Connection connection=DbConnection.getConnection();
		String sql = "select*from buslist";
		System.out.println(sql);
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			BusList bl=new BusList();
			bl.busNum=rs.getInt("bus_num");
			bl.busName=rs.getString("bus_name");
			bl.noOfSeats=rs.getInt("no_of_seats");
			bl.seatType=rs.getString("seat_type");
		list.add(bl);		
		}
		return list;
	}
	public void deleteBusName(String busName) throws Exception {
		Connection connection=DbConnection.getConnection();
		String sql ="delete from buslist where bus_name='" +busName+"'";
		System.out.println(sql);
		Statement stmt=connection.createStatement();
		try{
			int row=stmt.executeUpdate(sql);
		
		System.out.println(row);
		}catch(Exception e) {
			System.out.println(e);
		}
		finally{
		System.out.println("There are child records found");
		}
		
		
	}
   
   
}

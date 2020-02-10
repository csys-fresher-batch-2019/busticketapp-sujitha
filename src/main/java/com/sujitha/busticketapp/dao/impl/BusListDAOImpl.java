package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.BusListDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusList;

public class BusListDAOImpl implements BusListDAO  {
	private static final Logger log=Logger.getInstance();
	//public void BusListDisplay(int busNum,String busName,int noOfSeats,String seatType) {
	//	String str="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+ busNum +"','"+busName+"','"+noOfSeats+"','"+seatType+"')";
		//System.out.println(str);
		
	//}
	//public void ListDisplay(BusList buslist) {
		//String sql="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+buslist.busNum +"','"+buslist.busName+"','"+buslist.noOfSeats+"','"+buslist.seatType+"')";
		//System.out.println(sql);
	//}
	public void busList(int busNum, String busName, int noOfSeats, String seatType) throws DbException  {

		String sql="insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(?,?,?,?)";
		System.out.println(sql);
		try(Connection connection =DbConnection.getConnection() ;
		
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		pst.setInt(1,busNum);
		pst.setString(2,busName);
		pst.setInt(3,noOfSeats);
		pst.setString(4,seatType);
		if(noOfSeats<=10)
		{
				int row=pst.executeUpdate();
				System.out.println(row);
		}
		}catch(SQLException e)
		{
			log.error(e);
		}
		
	}
	public void busNameUpdate(String busName, int busNum) throws DbException  {
		String s="update buslist set bus_name =? where bus_num=?";
		System.out.println(s);
		try(Connection connection =DbConnection.getConnection() ;
		
				PreparedStatement pst = connection.prepareStatement(s);)
		{
				pst.setString(1,busName);
				pst.setInt(2,busNum);
				int rows=pst.executeUpdate();
				System.out.println(rows);
		}catch(SQLException e)
		{
			log.error(e);
		}
}
	public String busName(int busNum) throws DbException {
		
		String name="select bus_name from buslist where bus_num=?";
		System.out.println(name);
		String s = null;
	try(Connection connection = DbConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(name);
		)
	{

		
		pst.setInt(1,busNum);
		
		try(ResultSet rs=pst.executeQuery();)
		{
		while(rs.next())
		{
			 s= rs.getString("bus_name");
			
		}
		}}catch(SQLException e)
	{
		log.error(e);
	}
	return s;
		
	}
	public int noOfSeats(int busNum) throws DbException {
		String seats="select no_of_seats from buslist where bus_num=?";
		System.out.println(seats);
		int s = 0;
		try(Connection connection=DbConnection.getConnection();	PreparedStatement pst = connection.prepareStatement(seats);)
		{
			pst.setInt(1,busNum);
		try(ResultSet rows=pst.executeQuery();)
				{
			
		while(rows.next())
		{
			 s= rows.getInt("no_of_seats");
			
		}
		//System.out.println(s);
		
		}
		}catch(SQLException e)
		{
			log.error(e);
		}
		 return s;
	}
	public List<BusList> allBusListDetails() throws DbException {
		List<BusList> list=new ArrayList<BusList>();
		String sql = "select*from buslist";
		System.out.println(sql);
		try(Connection connection=DbConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(sql);
		)
		{
			try(ResultSet rs=pst.executeQuery();){
		while(rs.next())
		{
			BusList bl=new BusList();
			bl.setBusNum(rs.getInt("bus_num"));
			bl.setBusName(rs.getString("bus_name"));
			bl.setNoOfSeats(rs.getInt("no_of_seats"));
			bl.setSeatType(rs.getString("seat_type"));
		list.add(bl);		
		}
			}}catch(SQLException e)
		{
			log.error(e);
		}
		return list;
	}
	public void deleteBusName(String busName) throws DbException {
		
		String sql ="delete from buslist where bus_name=?";
		System.out.println(sql);
		try(Connection connection=DbConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		try{
			pst.setString(1,busName);
			int row=pst.executeUpdate(sql);
		
		System.out.println(row);
		}catch(Exception e) {
			System.out.println(e);
		}
		finally{
		System.out.println("There are child records found");
		}
		
		}	catch(SQLException e)
		{
			log.error(e);
		}
	}
   
   
}

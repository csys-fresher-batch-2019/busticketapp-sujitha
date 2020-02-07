package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.BusListDAO;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusList;

public class BusListDAOImpl implements BusListDAO  {
	private static final Logger log=Logger.getInstance();
	//public void BusListDisplay(int busNum,String busName,int noOfSeats,String seatType) {
	//	String str="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+ busNum +"','"+busName+"','"+noOfSeats+"','"+seatType+"')";
		//log.getInput(str);
		
	//}
	//public void ListDisplay(BusList buslist) {
		//String sql="insert into buslist(busNum,busName,noOfSeats,seatType)values('"+buslist.busNum +"','"+buslist.busName+"','"+buslist.noOfSeats+"','"+buslist.seatType+"')";
		//log.getInput(sql);
	//}
	public void busList(int busNum, String busName, int noOfSeats, String seatType) throws Exception  {

		String sql="insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(?,?,?,?)";
		log.getInput(sql);
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
				log.getInput(row);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void busNameUpdate(String busName, int busNum) throws Exception  {
		String s="update buslist set bus_name =? where bus_num=?";
		log.getInput(s);
		try(Connection connection =DbConnection.getConnection() ;
		
				PreparedStatement pst = connection.prepareStatement(s);)
		{
				pst.setString(1,busName);
				pst.setInt(2,busNum);
				int rows=pst.executeUpdate();
				log.getInput(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
}
	public String busName(int busNum) throws Exception {
		
		String name="select bus_name from buslist where bus_num=?";
		log.getInput(name);
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
		}}catch(Exception e)
	{
		e.printStackTrace();
	}
	return s;
		
	}
	public int noOfSeats(int busNum) throws Exception {
		String seats="select no_of_seats from buslist where bus_num=?";
		log.getInput(seats);
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
		//log.getInput(s);
		
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 return s;
	}
	public List<BusList> allBusListDetails() throws Exception {
		List<BusList> list=new ArrayList<BusList>();
		String sql = "select*from buslist";
		log.getInput(sql);
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
			}}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public void deleteBusName(String busName) throws Exception {
		
		String sql ="delete from buslist where bus_name=?";
		log.getInput(sql);
		try(Connection connection=DbConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		try{
			pst.setString(1,busName);
			int row=pst.executeUpdate(sql);
		
		log.getInput(row);
		}catch(Exception e) {
			log.getInput(e);
		}
		finally{
		log.getInput("There are child records found");
		}
		
		}	catch(Exception e)
		{
			e.printStackTrace();
		}
	}
   
   
}

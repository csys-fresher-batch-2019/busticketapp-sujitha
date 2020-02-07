package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.UserDetailsDAO;
import com.sujitha.busticketapp.dto.UserGenderEnum;
import com.sujitha.busticketapp.model.UserDetails;

public class UserDetailsDAOImpl implements UserDetailsDAO {

	public void getUserDetails(String userName, long userPhnNum, String userGender,String password) throws Exception {
		String str="insert into user_details(user_id ,user_name,user_phn_num,user_gender,password)values(user_id_seq.nextval,?,?,?,?)";
		System.out.println(str);
		try(Connection connection =DbConnection.getConnection() ;
	    PreparedStatement pst = connection.prepareStatement(str);)
		{
		pst.setString(1,userName);
		pst.setLong(2,userPhnNum);
		pst.setString(3,userGender);
		pst.setString(4,password);
		int rows=pst.executeUpdate();
		System.out.println(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateUserPhnNum(int userId,long userPhnNum) throws Exception {
		try(Connection connection =DbConnection.getConnection() ;)
		{
		String sql="update user_details set user_phn_num=? where user_id=?";
		System.out.println(sql);
		try
		(PreparedStatement pst = connection.prepareStatement(sql);)
		{
		 pst.setLong(1,userPhnNum);
		 pst.setInt(2,userId);
		int row=pst.executeUpdate(sql);
		
		System.out.println(row);
		}
		String ss= "select user_id,user_name,user_phn_num from user_details where user_id=?";
		System.out.println(ss);
		try
		(PreparedStatement pst1 = connection.prepareStatement(ss);)
		{
		ResultSet rs=pst1.executeQuery(ss);
		while(rs.next())
		{
			int id = rs.getInt("user_id");
		String username=rs.getString("user_phn_num");
		System.out.println(id + "-" + username);
		}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getUserGender(int userId) throws Exception {
		
		String strn="select user_gender from user_details where user_id=?";
		System.out.println(strn);
		String a= null;
		try(Connection connection =DbConnection.getConnection() ;
		
				PreparedStatement pst = connection.prepareStatement(strn);
		ResultSet rs=pst.executeQuery();)
		{
			pst.setInt(1,userId);
		if(rs.next())
		{
			a= rs.getString("user_gender");
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return a;
	}

	  public int getGenderCount(String  userGender) throws Exception {
		  String sql ="select count (*)user_gender from user_details where user_gender=?";
			System.out.println(sql);
			int v=0;
		try(Connection connection =DbConnection.getConnection(); 
		
				PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery(sql);)
		{
			pst.setString(1,userGender);
		if(rs.next())
		{
			 v= rs.getInt("user_gender");
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	  }

	/*public ArrayList<UserDetails> getUserDetails() throws Exception {
		ArrayList<UserDetails> list=new ArrayList<UserDetails>();
		Connection connection =DbConnection.getConnection();
		String sql="select * from user_details";
		System.out.println(sql);
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next()) {
			UserDetails uds=new UserDetails();
			uds.userId=rs.getInt("user_id");
			uds.userName=rs.getString("user_name");
			uds.userPhnNum=rs.getLong("user_phn_num");
			uds.userGender=UserGenderEnum.valueOf(rs.getString("user_gender"));
			list.add(uds);
		}
		return list;
	}*/

	public void addUserDetails(UserDetails userdetails) throws Exception {
		String sql="insert into user_details(user_id ,user_name,user_phn_num,user_gender)values(?,?,?,?)";
		System.out.println(sql);
		try(Connection connection =DbConnection.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		pst.setInt(1,userdetails.userId);
		pst.setString(2,userdetails.userName);
		pst.setLong(3,userdetails.userPhnNum);
		pst.setString(4,userdetails.userGender.toString());
		int rows=pst.executeUpdate();
		System.out.println(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void updateUG(int userID,UserGenderEnum userGender) throws Exception {
		String sql="UPDATE USER_DETAILS SET USER_GENDER=? WHERE USER_ID=?";
		System.out.println(sql);
		try(Connection connection =DbConnection.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		
		pst.setString(1,userGender.toString());
		pst.setInt(2,userID);
		
		int rows=pst.executeUpdate();
		System.out.println(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void login(long userPhnNum, String password) throws Exception {
		String sql = "select user_phn_num,password from user_details where user_phn_num=? and password = ?";
		System.out.println(sql);
        try(Connection connection=DbConnection.getConnection();
        		PreparedStatement pst = connection.prepareStatement(sql);
        		ResultSet row =pst. executeQuery(sql);)
       {
		
		if (row.next())
		{
		 
			System.out.println("Successfully Login");
		}
		else {
			System.out.println("Login Details are invalid");
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
		
	}
	
	


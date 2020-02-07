package com.sujitha.busticketapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbConnection;
import com.sujitha.busticketapp.dao.UserDetailsDAO;
import com.sujitha.busticketapp.dto.UserGenderEnum;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.UserDetails;

public class UserDetailsDAOImpl implements UserDetailsDAO {
	private static final Logger log=Logger.getInstance();

	public void getUserDetails(String userName, long userPhnNum, String userGender,String password) throws Exception {
		String str="insert into user_details(user_id ,user_name,user_phn_num,user_gender,password)values(user_id_seq.nextval,?,?,?,?)";
		log.getInput(str);
		try(Connection connection =DbConnection.getConnection() ;
	    PreparedStatement pst = connection.prepareStatement(str);)
		{
		pst.setString(1,userName);
		pst.setLong(2,userPhnNum);
		pst.setString(3,userGender);
		pst.setString(4,password);
		int rows=pst.executeUpdate();
		log.getInput(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateUserPhnNum(int userId,long userPhnNum) throws Exception {
		try(Connection connection =DbConnection.getConnection() ;)
		{
		String sql="update user_details set user_phn_num=? where user_id=?";
		log.getInput(sql);
		try
		(PreparedStatement pst = connection.prepareStatement(sql);)
		{
		 pst.setLong(1,userPhnNum);
		 pst.setInt(2,userId);
		int row=pst.executeUpdate(sql);
		
		log.getInput(row);
		}
		String ss= "select user_id,user_name,user_phn_num from user_details where user_id=?";
		log.getInput(ss);
		try
		(PreparedStatement pst1 = connection.prepareStatement(ss);ResultSet rs=pst1.executeQuery(ss);)
		{
		
		 pst1.setLong(1,userId);
		while(rs.next())
		{
			int id = rs.getInt("user_id");
		String username=rs.getString("user_phn_num");
		log.getInput(id + "-" + username);
		}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getUserGender(int userId) throws Exception {
		
		String strn="select user_gender from user_details where user_id=?";
		log.getInput(strn);
		String a= null;
		try(Connection connection =DbConnection.getConnection() ;
		
				PreparedStatement pst = connection.prepareStatement(strn);
		)
		{
			pst.setInt(1,userId);
			
			try(ResultSet rs=pst.executeQuery();)
			{
		if(rs.next())
		{
			a= rs.getString("user_gender");
			
		}
			}}catch(Exception e)
		{
			e.printStackTrace();
		}
		return a;
	}

	  public int getGenderCount(String  userGender) throws Exception {
		  String sql ="select count (*)user_gender from user_details where user_gender=?";
			log.getInput(sql);
			int v=0;
		try(Connection connection =DbConnection.getConnection(); 
		
				PreparedStatement pst = connection.prepareStatement(sql);
		)
		{
			pst.setString(1,userGender);
			try(ResultSet rs=pst.executeQuery();)
			{
		if(rs.next())
		{
			 v= rs.getInt("user_gender");
			
		}
			}}catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	  }

	/*public ArrayList<UserDetails> getUserDetails() throws Exception {
		ArrayList<UserDetails> list=new ArrayList<UserDetails>();
		Connection connection =DbConnection.getConnection();
		String sql="select * from user_details";
		log.getInput(sql);
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
		log.getInput(sql);
		try(Connection connection =DbConnection.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		pst.setInt(1,userdetails.getUserId());
		pst.setString(2,userdetails.getUserName());
		pst.setLong(3,userdetails.getUserPhnNum());
		pst.setString(4,userdetails.getUserGender().toString());
		int rows=pst.executeUpdate();
		log.getInput(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void updateUG(int userID,UserGenderEnum userGender) throws Exception {
		String sql="UPDATE USER_DETAILS SET USER_GENDER=? WHERE USER_ID=?";
		log.getInput(sql);
		try(Connection connection =DbConnection.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(sql);)
		{
		
		pst.setString(1,userGender.toString());
		pst.setInt(2,userID);
		
		int rows=pst.executeUpdate();
		log.getInput(rows);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void login(long userPhnNum, String password) throws Exception {
		String sql = "select user_phn_num,password from user_details where user_phn_num=? and password = ?";
		log.getInput(sql);
        try(Connection connection=DbConnection.getConnection();
        		PreparedStatement pst = connection.prepareStatement(sql);
        		)
       {
		try(ResultSet row =pst. executeQuery();)
		{
		if (row.next())
		{
		 
			log.getInput("Successfully Login");
		}
		else {
			log.getInput("Login Details are invalid");
		}
		
		
		}}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
		
	}
	
	


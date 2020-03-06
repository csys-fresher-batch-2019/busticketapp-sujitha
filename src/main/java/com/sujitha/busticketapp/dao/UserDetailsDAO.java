package com.sujitha.busticketapp.dao;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dto.UserGenderEnum;
import com.sujitha.busticketapp.model.UserDetails;

public interface UserDetailsDAO {
	public void getUserDetails(String userName, long userPhnNum, String userGender, String password) throws DbException;

	public void updateUserPhnNum(int userId, long userPhnNum) throws DbException;

	public String getUserGender(int userId) throws DbException;

	public int getGenderCount(String userGender) throws DbException;

	public void addUserDetails(UserDetails userdetails) throws DbException;

	public void updateUG(int userID, UserGenderEnum userGender) throws DbException;

	public boolean login(long userPhnNum, String password) throws DbException;

	public int getUserId(long userPhnNum, String password) throws DbException;

}

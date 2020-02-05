package com.sujitha.busticketapp.model;

import com.sujitha.busticketapp.dto.UserGenderEnum;

public class UserDetails {
	public int userId;
	public String  userName;
    public long userPhnNum;
	public String userGender;
	public String password;
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", userPhnNum=" + userPhnNum
				+ ", userGender=" + userGender + ", password=" + password + "]";
	}
	
	
	
	}



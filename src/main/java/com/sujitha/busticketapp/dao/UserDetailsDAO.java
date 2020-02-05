package com.sujitha.busticketapp.dao;

import java.util.ArrayList;

import com.sujitha.busticketapp.dto.UserGenderEnum;
import com.sujitha.busticketapp.model.UserDetails;

public interface UserDetailsDAO {
	//insert into user_details(user_id ,user_name,user_phn_num,user_gender)values(14,pavithra,1234567891,F)
 public void getUserDetails(String userName,long userPhnNum,String userGender,String password) throws Exception;

  // update user_details set user_name=kavitha where user_id=12;
   public void updateUserPhnNum(int userId,long userPhnNum) throws Exception;
   
   //select user_gender from user_detail where user_id=13;
   public String getUserGender(int userId) throws Exception;
    //select count (*)user_gender from user_details where user_gender='F'
   public int getGenderCount(String userGender) throws Exception;
   // select * from user_details
  // public ArrayList<UserDetails> getUserDetails() throws Exception;
   // insert into user_details(user_id ,user_name,user_phn_num,user_gender)values(?,?,?,?)
   public void addUserDetails(UserDetails userdetails) throws Exception;
   //UPDATE USER_DETAILS SET USER_GENDER='M' WHERE USER_ID=13;
   public void updateUG(int userID,UserGenderEnum userGender) throws Exception;
   
}

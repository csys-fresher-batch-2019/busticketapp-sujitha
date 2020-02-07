package com.sujitha.busticketapp.model;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
	 private int userId;
	 private int travelId;
	 
	 private int busNum ;
	 private	String userGender ;
	 private int seatNo ;
	 public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public int getBusNum() {
		return busNum;
	}
	public void setBusNum(int busNum) {
		this.busNum = busNum;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public LocalDate getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getGenderPreference() {
		return genderPreference;
	}
	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}
	private LocalDate bookedDate ;
	 private  String genderPreference;
@Override
public String toString() {
	return "Booking [userId=" + userId + ", travelId=" + travelId + ", busNum=" + busNum + ", userGender=" + userGender
			+ ", seatNo=" + seatNo + ", bookedDate=" + bookedDate + ", genderPreference=" + genderPreference + "]";
}

}
  



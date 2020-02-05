package com.sujitha.busticketapp.model;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
	 public int userId;
	 public int travelId;
	 
	public int busNum ;
public	String userGender ;
	public int seatNo ;
 public LocalDate bookedDate ;
  public  String genderPreference;
@Override
public String toString() {
	return "Booking [userId=" + userId + ", travelId=" + travelId + ", busNum=" + busNum + ", userGender=" + userGender
			+ ", seatNo=" + seatNo + ", bookedDate=" + bookedDate + ", genderPreference=" + genderPreference + "]";
}

}
  



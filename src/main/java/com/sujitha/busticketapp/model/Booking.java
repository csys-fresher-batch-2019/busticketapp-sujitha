package com.sujitha.busticketapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Booking {
	 
	
	 private int userId;
	 private int busNum ;
	 private	String userGender ;
	
	private int seatNo ;
	 private LocalDate bookedDate;
	 private String genderPreference;
	 private int Amount;
	 private long bookingId;
	 private String status;
	 private LocalDate createdDate;
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "Booking [userId=" + userId + ", busNum=" + busNum + ", userGender=" + userGender + ", seatNo=" + seatNo
				+ ", bookedDate=" + bookedDate + ", genderPreference=" + genderPreference + ", Amount=" + Amount
				+ ", bookingId=" + bookingId + ", status=" + status + ", createdDate=" + createdDate + "]";
	}
	
		
	
		
		
	
}	





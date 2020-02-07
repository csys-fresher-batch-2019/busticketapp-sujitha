package com.sujitha.busticketapp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class BusDetails {
	private  int travelId ;
	private  int routeNo;
	private  int busNum;
	private  LocalDate travelDate;
	private  LocalTime startTime;
	private  LocalTime endTime;
	private  int fair ;
	private  int availableSeats;
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public int getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(int routeNo) {
		this.routeNo = routeNo;
	}
	public int getBusNum() {
		return busNum;
	}
	public void setBusNum(int busNum) {
		this.busNum = busNum;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public int getFair() {
		return fair;
	}
	public void setFair(int fair) {
		this.fair = fair;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}

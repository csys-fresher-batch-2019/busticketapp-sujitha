package com.sujitha.busticketapp.dto;

import java.time.LocalTime;

public class BusesDetails {
	private int busNum;
	private String busName; 
	private int noOfSeats;
	private String seatType;
	private  int travelId ;
	private  int routeNo;
	private  String startTime;
	private  String endTime;
	private  int fair ;
	public int getBusNum() {
		return busNum;
	}
	public void setBusNum(int busNum) {
		this.busNum = busNum;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getFair() {
		return fair;
	}
	public void setFair(int fair) {
		this.fair = fair;
	}
	@Override
	public String toString() {
		return "BusesDetails [busNum=" + busNum + ", busName=" + busName + ", noOfSeats=" + noOfSeats + ", seatType="
				+ seatType + ", travelId=" + travelId + ", routeNo=" + routeNo + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", fair=" + fair + "]";
	}
}
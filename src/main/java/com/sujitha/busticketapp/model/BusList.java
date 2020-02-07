package com.sujitha.busticketapp.model;

public class BusList {
	
		private int busNum;
		private String busName; 
		private int noOfSeats;
		private String seatType;
		@Override
		public String toString() {
			return "BusList [busNum=" + busNum + ", busName=" + busName + ", noOfSeats=" + noOfSeats + ", seatType="
					+ seatType + "]";
		}
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
	}



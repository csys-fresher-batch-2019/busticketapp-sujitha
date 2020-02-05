package com.sujitha.busticketapp.model;

public class BusList {
	
		public int busNum;
		public String busName; 
		public int noOfSeats;
		public String seatType;
		@Override
		public String toString() {
			return "BusList [busNum=" + busNum + ", busName=" + busName + ", noOfSeats=" + noOfSeats + ", seatType="
					+ seatType + "]";
		}
	}



package com.sujitha.busticketapp.model;

import java.sql.Date;
import java.time.LocalDate;



public class TicketBooking {
	public int travelId;
	public int  noOfSeatsBooked;
	public int userId;
	public int fair;
	public LocalDate jDate;
	 public LocalDate bookedDate;
	 public int payment;
	 public String status;
	@Override
	public String toString() {
		return "TicketBooking [travelId=" + travelId + ", noOfSeatsBooked=" + noOfSeatsBooked + ", userId=" + userId
				+ ", fair=" + fair + ", jDate=" + jDate + ", bookedDate=" + bookedDate + ", payment=" + payment
				+ ", status=" + status + "]";
	}

}

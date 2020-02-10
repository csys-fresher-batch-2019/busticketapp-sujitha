package com.sujitha.busticketapp.dao;

import java.sql.Date;
import java.util.ArrayList;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dto.BusSeatsBooked;


public interface TicketBookingDAO  {
	 
   // select no_of_seats_booked from ticket_booking where travel_id=20;
     public int getNoOfSeatsBooked (int travelId) throws DbException;
   //select sum(payment) from ticket_booking where status='booked';
      public int totalPayment(String status) throws DbException;
  //select booked_date,count(no_of_seats_booked)as total_seats from ticket_booking where  status='booked' group by no_of_seats_booked,booked_date; 
      public ArrayList<BusSeatsBooked> totalNoOfSeatsBooked(String status) throws DbException;
     
   // select  count(b.seat_no) as ticket_count from booking b  where travel_id=10 and bus_num=101 and user_id=11;

      public int getSeatNo(int travelId,int userId) throws DbException;
      




}

package com.sujitha.busticketapp.dao;

import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.model.BusDetails;

public interface BusDetailsDAO<Details> {

	
	// update busdetails set fair =1500 where travel_id=30; 
	  public void fairUpdate(int fair,int travel_id) throws Exception;
	// select (bl.no_of_seats-available_seats)availableSeats from  buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=tb.travel_id and tb.travel_id='"+travelId+"'group by bl.no_of_seats,tb.travel_id)"
      public int  availableSeats(int travelId) throws Exception;
    
    //insert into busdetails(travel_id,route_no,bus_num,travel_date,start_time,end_time,fair,available_seats)values(40,4,300,to_date('19-01-2020','DD-MM-YYYY'),('9:10:00,pm'),('5:10:00,am'),760,60)
    void addBusDetails(BusDetails busdetail) throws Exception;
    
     // select fair from busdetails where travel_id=10;
    public int fairDetails(int travelId) throws Exception;
     
    
   // select buslist.bus_name, busdetails.fair from buslist inner join busdetails on buslist.bus_num = busdetails.bus_num;
     public ArrayList<BusFare> getFairDetails(String busName) throws Exception;
 
     // select   tb.travel_id,bl.no_of_seats,bl.no_of_seats-sum(tb.no_of_seats_booked)  available_seats from
     //buslist bl,
     //ticket_booking tb,
     //busdetails bd where bl.bus_num=bd.bus_num and bd.travel_id=tb.travel_id 
     //group by bl.no_of_seats,tb.travel_id
       public int getAvailableSeats() throws Exception;
       
       //select bus_name from buslist where bus_num=(select bus_num from busdetails where route_no =
      // (select route_no from busroutes where to_location='Kovilpatti'));
        public String getBusName (String toLocation) throws Exception;


}   

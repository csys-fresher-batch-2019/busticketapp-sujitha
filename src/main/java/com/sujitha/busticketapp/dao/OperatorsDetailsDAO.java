package com.sujitha.busticketapp.dao;

import java.util.List;

import com.sujitha.busticketapp.dto.Buses;
import com.sujitha.busticketapp.model.OperatorsDetails;

public interface OperatorsDetailsDAO {
	//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bl.bus_num in(select bus_num from buslist where op_name='TAT Travels');
   public List<Buses> getDetails(String opName) throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bl.bus_num in(select bus_num from buslist where bus_name like '%A%');
public List<Buses> displayBuses(String busName) throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bl.bus_num in(select bus_num from buslist where bus_name='TAT');
public List<Buses> displayBusDetails(String busName)throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bl.bus_num in(select bus_num from buslist where bus_model='non-ac');
 public List<Buses> dispalyBus(String busModel)throws Exception;
 //select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bl.bus_num in(select bus_num from buslist where bus_model='ac' );
 public List<Buses> displayBusModel(String busModel)throws Exception;
 //select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.ratings in(select min(ratings) from busdetails);
public List<Buses> displayBusesRating()throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.ratings in(select max(ratings) from busdetails);
public List<Buses> displayBusRating()throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.fair in(select min(fair) from busdetails);
public List<Buses> displayBusFair()throws Exception;
//select bl.bus_name,bl.no_of_seats, bl.seat_type,bl.bus_model,bd.start_time,bd.end_time,bd.fair,bd.ratings,bd.available_seats from buslist bl,busdetails bd where bl.bus_num=bd.bus_num and bd.fair in(select max(fair) from busdetails);
public List<Buses>displayBusesFair()throws Exception;
 public int getId(String operatorEmailId,String operatorPassword);
 public void setDetails(OperatorsDetails operator);
 public List<OperatorsDetails> displayOperators() throws Exception;
 public int getBusNum(int routeNo) throws Exception;
}

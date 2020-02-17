package com.sujitha.busticketapp.dao;

import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.model.BusList;

public interface BusListDAO {
	//insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(101,'TAT',40,'seater');
   // void BusListDisplay(int busNum,String busName,int noOfSeats,String seatType);

//insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(101,'TAT',40,'seater');
 //public void ListDisplay(BusList buslist);
  
 //insert into buslist(bus_num,bus_name,no_of_seats,seat_type)values(50,'TAt',048,'sleeper');
   public void busList(int busNum,String busName,int noOfSeats,String seatType) throws DbException;
   
  // "update buslist set bus_name ='SRM' where bus_num=101";
   public void busNameUpdate(String busName,int busNum) throws DbException;
  
   //select bus_name from buslist where bus_num=102;
   public String busName(int busNum) throws DbException;
  // select no_of_seats from buslist where bus_num=102;
   public int noOfSeats(int busNum) throws DbException;
   // select*from buslist;
    public List<BusList> allBusListDetailss() throws DbException;
    //delete from buslist where busname= ?
    public void deleteBusName(String busName ) throws DbException;
    
}


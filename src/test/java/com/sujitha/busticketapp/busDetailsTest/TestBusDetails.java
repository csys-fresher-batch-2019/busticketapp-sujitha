package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.sujitha.busticketapp.dao.impl.BusDetailsDAOImpl;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.logger.Logger;
import com.sujitha.busticketapp.model.BusDetails;

public class TestBusDetails {
	private static final Logger log=Logger.getInstance();
	public static void main(String[] args) throws Exception {
		
		
      
		BusDetailsDAOImpl bdl = new BusDetailsDAOImpl();
		
		 // update bus fair using travelid:
             //bdl.fairUpdate(750, 30);
		
         // Add Busdetails:
		        // testInsert();
		 
	     // To find available of seats usuing travel id:
		      // int available= bdl.availableSeats(10);
            //   System.out.println(available);
      
         //Display fair details:
		       //int fair= bdl.fairDetails(10);
		       // System.out.println(fair);
    
         //Dispaly fair using busname:	
		       //displayFair();
	
        // Display AvailableSeats:        
		   // bdl.getAvailableSeats();
		 
		// Display busdetails using to location:    
		     //  String busname=bdl.getBusName("Kovilpatti");
		   // System.out.println(busname);
		      
		     // testInsert();
	   
	}
      
	
	
	public static void testInsert() throws Exception {
    	ArrayList<BusDetails> busdetail= new ArrayList<BusDetails>();
 	      BusDetails	bd = new BusDetails();
 	     BusDetailsDAOImpl bdl = new BusDetailsDAOImpl();
 	         bd.setTravelId(40);
 	         bd.setRouteNo(4);
 	         bd.setBusNum(104);
             bd.setTravelDate(LocalDate.parse("2019-01-01"));
             bd.setStartTime(LocalTime.parse("16:00"));
             bd.setEndTime(LocalTime.parse("19:00"));
             bd.setFair(900);
             bd.setAvailableSeats(20);
 	  busdetail.add(bd);
	        for(BusDetails busdetl:busdetail) {
        bdl.addBusDetails(busdetl);
       }
	
      }
	private static void displayFair() throws Exception {
		BusDetailsDAOImpl bdl = new BusDetailsDAOImpl();
		ArrayList<BusFare> fairdetails= bdl.getFairDetails("TAT");
	 for (BusFare busFare : fairdetails) {
		System.out.println(busFare);
	}
		
	}
	}



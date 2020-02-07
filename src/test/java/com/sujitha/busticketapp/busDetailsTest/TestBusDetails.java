package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.sujitha.busticketapp.dao.impl.BusDetailsDAOImpl;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.model.BusDetails;

public class TestBusDetails {

	public static void main(String[] args) throws Exception {
		
      
		BusDetailsDAOImpl bdl = new BusDetailsDAOImpl();
		
		 // update bus fair using travelid:
             //bdl.fairUpdate(1000, 30);
		
         // Add Busdetails:
		         // testInsert();
		 
	     // To find available of seats usuing travel id:
		       //int available= bdl.availableSeats(10);
               //System.out.println(available);
      
         //Display fair details:
		      //  int fair= bdl.fairDetails(10);
		       // System.out.println(fair);
    
         //Dispaly fair using busname:	
		       //displayFair();
	
        // Display AvailableSeats:        
		   // bdl.getAvailableSeats();
		 
		// Display busdetails using to location:    
		       String busname=bdl.getBusName("Kovilpatti");
		    System.out.println(busname);
		      
		     // testInsert();
	   
	}
      
	
	
	public static void testInsert() throws Exception {
    	ArrayList<BusDetails> busdetail= new ArrayList<BusDetails>();
 	      BusDetails	bd = new BusDetails();
 	     BusDetailsDAOImpl bdl = new BusDetailsDAOImpl();
 	         bd.travelId=40;
 	         bd.routeNo=4;
 	         bd.busNum=300;
             bd.travelDate= LocalDate.parse("2019-01-01");
             bd.startTime=LocalTime.parse("16:00");
             bd.endTime=LocalTime.parse("19:00");
             bd.fair=900;
             bd.availableSeats=20;
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



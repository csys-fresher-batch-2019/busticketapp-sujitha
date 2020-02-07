package com.sujitha.busticketapp.busDetailsTest;

import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.dao.impl.BusDetailsDAOImpl;
import com.sujitha.busticketapp.dao.impl.BusRoutesDAOImpl;
import com.sujitha.busticketapp.dto.BusFare;
import com.sujitha.busticketapp.model.BusRoutes;

public class TestBusRoutes {

	public static void main(String[] args) throws Exception {
		BusRoutesDAOImpl br= new BusRoutesDAOImpl();
		// Insert the bus routes:
		      //br.busRouteAdd(4,"Trichy","Madurai");
		// Update the details:
		     // br.routeNoUpdate(4,"Bangloreee");
   // int a=  br.getRouteNo("chennai", "Kovilpatti");
   // System.out.println(a);
       
	  
	  /* List<BusRoutes> routes=br.displayBusroutes();
	   for(BusRoutes route:routes) {
		   System.out.println(route);
	   }  */ 
		   List<BusRoutes> bus=br.fromLocations();
		   for(BusRoutes r:bus) {
			   System.out.println(r.fromLocation);
		   }	  
			 List<BusRoutes> rout=br.toLocations();
			   for(BusRoutes ro:rout) {
				   System.out.println(ro.toLocation);
			   }	   
			    
	  
	}

}

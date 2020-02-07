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
		     // br.busRouteAdd(5,"Thirunelveli","Madurai");
		// Update the details:
		   //   br.routeNoUpdate(4,"Virudhunagar");
   //int a=  br.getRouteNo("chennai", "Kovilpatti");
  //  log.getInput(a);
       
	  
	 /* List<BusRoutes> routes=br.displayBusroutes();
	   for(BusRoutes route:routes) {
		   log.getInput(route);
	   }  */ 
		   List<BusRoutes> bus=br.fromLocations();
		   for(BusRoutes r:bus) {
			   log.getInput(r.getFromLocation());
		   }	  
			 List<BusRoutes> rout=br.toLocations();
			   for(BusRoutes ro:rout) {
				   log.getInput(ro.getToLocation());
			   }	   
			    
	  
	}

}

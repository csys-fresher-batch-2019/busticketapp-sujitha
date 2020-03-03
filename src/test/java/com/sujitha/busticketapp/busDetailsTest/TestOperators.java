package com.sujitha.busticketapp.busDetailsTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.BusDetailsDAOImpl;
import com.sujitha.busticketapp.dao.impl.BusListDAOImpl;
import com.sujitha.busticketapp.dao.impl.OperatorsDetailsDAOImpl;
import com.sujitha.busticketapp.dto.Buses;
import com.sujitha.busticketapp.model.BusDetails;
import com.sujitha.busticketapp.model.BusList;
import com.sujitha.busticketapp.model.OperatorsDetails;

public class TestOperators {

	public static void main(String[] args) throws Exception {
		TestOperators t= new TestOperators();
		 OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
             //t.DisplayBuses();
            //t.DisplayBusName();
		//t.DisplayBusname1();
		 System.out.println("cgguihijpouuig");
		t.DisplayBussname1();
		/*t.DisplayBussnamee();
		t.DisplayBusrating();
		t.DisplayBusratings();
	  t.DisplayBusFair();
	  t.DisplayBusesFair();
	 
	  // int s=ts.getId("tat@gmail.com","tat1234");
	   //System.out.println(s);*/
	  // t.testInsert();
	  // t.DisplayOperator();
		 /*List<Buses> list=new ArrayList<Buses>();
			list=ts.getDetails("TAT Travels");
			for(Buses buses:list)
			{
				System.out.println(buses);
			}*/
		//int a= ts.getBusNum(3);
		//System.out.println(a);
	}

	public static void DisplayBuses() throws Exception {
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
		 List<Buses> list=new ArrayList<Buses>();
			list=ts.getDetails("TAT Travels");
			for(Buses buses:list)
			{
				System.out.println(buses);
			}
	 }
 public void DisplayBusName() throws Exception {
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBuses("A");
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 }
 public void DisplayBusname1()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBusDetails("TAT");
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBussname1()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.dispalyBus("ac");
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBussnamee()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.dispalyBus("non-ac");
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBusrating()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBusesRating();
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBusratings()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBusRating();
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBusFair()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBusFair();
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public void DisplayBusesFair()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<Buses> list=new ArrayList<Buses>();
	 list=ts.displayBusesFair();
	 for(Buses buses:list)
		{
			System.out.println(buses);
		}
 
	}
 public static void testInsert() throws DbException {
 	ArrayList<OperatorsDetails> operator= new ArrayList<OperatorsDetails>();
 	OperatorsDetails	bd = new OperatorsDetails();
	OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	        bd.setOperatorName("SVS Travels");
	        bd.setOperatorEmailId("svs@gmail.com");
	        bd.setOperatorPhoneNumber("9876908765");
	        bd.setOperatorPassword("svs12345");
 	operator.add(bd);
	        for(OperatorsDetails op:operator) {
     ts.setDetails(op);
    }
 
}
 public void DisplayOperator()throws Exception{
		OperatorsDetailsDAOImpl ts= new OperatorsDetailsDAOImpl();
	 List<OperatorsDetails> list=new ArrayList<OperatorsDetails>();
	 list=ts.displayOperators();
	 for(OperatorsDetails op:list)
		{
			System.out.println(op.getOperatorName());
		}
 
	}

}
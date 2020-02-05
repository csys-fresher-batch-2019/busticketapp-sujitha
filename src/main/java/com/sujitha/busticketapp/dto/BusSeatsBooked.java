package com.sujitha.busticketapp.dto;

import java.time.LocalDate;

public class BusSeatsBooked {
  public int totalseats;
   public LocalDate bookedDate;
@Override
public String toString() {
	return "BusSeatsBooked [totalseats=" + totalseats + ", bookedDate=" + bookedDate + "]";
}
}

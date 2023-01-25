package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;

public class Airline extends AbstractAirline<Flight> {
  private final String name;

  private ArrayList<Flight> flightArrayList = new ArrayList<>();


  public Airline(String name)
  {
    this.name = name;
  }

  @Override
  public String getName()
  {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight)
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    flightArrayList.add(flight);
  }


  @Override
  public Collection<Flight> getFlights()
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return flightArrayList;
  }
}

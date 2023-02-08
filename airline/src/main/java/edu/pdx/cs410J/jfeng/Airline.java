package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Airline extends AbstractAirline<Flight> {
  private final String name;

  private ArrayList<Flight> flightArrayList;


  public Airline(String name)
  {
    this.name = name;
    flightArrayList = new ArrayList<>();
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


  public void sort()
  {
    //flightArrayList.sort(Comparator.comparing(Flight::getSource));
    Collections.sort(flightArrayList, new FlightComparator());
  }

  public class FlightComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
      int result = o1.getSource().compareTo(o2.getSource());
      if (result == 0) {
        result = o1.getDepartureString().compareTo(o2.getDepartureString());
      }
      return result;
    }
  }
}

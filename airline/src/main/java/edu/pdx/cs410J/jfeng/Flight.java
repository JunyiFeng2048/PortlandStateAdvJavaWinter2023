package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {

  public String flightName;
  public int flightNumber;
  public String src;
  public String departTime;
  public String destination;
  public String arriveTime;

  public Flight() {}

  public Flight(String flightName, int flightNumber, String src, String departTime, String destination, String arriveTime)
  {
    this.flightName = flightName;
    this.flightNumber = flightNumber;
    this.src = src;
    this.departTime = departTime;
    this.destination = destination;
    this.arriveTime = arriveTime;
  }

  public String getFlightName()
  {
    return flightName;
  }

  @Override
  public int getNumber()
  {
    return flightNumber;
  }

  @Override
  public String getSource()
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return src;
  }

  @Override
  public String getDepartureString()
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return departTime;
  }

  @Override
  public String getDestination()
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return destination;
  }

  @Override
  public String getArrivalString()
  {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return arriveTime;
  }


}

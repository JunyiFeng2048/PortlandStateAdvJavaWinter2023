package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight extends AbstractFlight {

  private String flightName;
  private int flightNumber;
  private String src;
  private String departTime;
  private String destination;
  private String arriveTime;

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
    return src;
  }

  @Override
  public String getDepartureString()
  {
    return departTime;
  }

  @Override
  public String getDestination()
  {
    return destination;
  }

  @Override
  public String getArrivalString()
  {
    return arriveTime;
  }

  public void setFlightName(String flightName)
  {
    this.flightName = flightName;
  }

  public String setFlightNumber(int flightNumber)
  {
    if(checkValidFlightNumber(flightNumber))
      this.flightNumber = flightNumber;
    else
      return "Invalid Flight Number";
    return "Valid Flight Number";
  }

  public String setSource(String src)
  {
    if(checkValidSrcAndDestCode(src))
      this.src = src;
    else
      return "Invalid Src or Dest Code";
    return "Valid Src or Dest Code";
  }

  public String setDepartTime(String departTime)
  {
    if(checkValidDateAndTime(departTime))
      this.departTime = departTime;
    else
      return "Invalid Date and Time";
    return "Valid Date and Time";
  }

  public String setDestination(String destination)
  {
    if(checkValidSrcAndDestCode(destination))
      this.destination = destination;
    else
      return "Invalid Src or Dest Code";
    return "Valid Src or Dest Code";
  }

  public String setArriveTime(String arriveTime)
  {
    if(checkValidDateAndTime(arriveTime))
      this.arriveTime = arriveTime;
    else
      return "Invalid Date and Time";
    return "Valid Date and Time";
  }

  public boolean checkValidFlightNumber(int flightNumber)
  {
    if(flightNumber < 0 || flightNumber > 999)
      return false;
    return true;
  }

  public boolean checkValidDateAndTime(String dateAndTime)
  {
    try {
      Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateAndTime);
    } catch (ParseException e)
    {
      return false;
    }
    return true;
  }

  public boolean checkValidSrcAndDestCode(String str)
  {
    if(str.length() != 3)
      return false;
    for (int i = 0; i < 3; i++) {
      if ((Character.isLetter(str.charAt(i)) == false))
        return false;
    }
    return true;
  }
}

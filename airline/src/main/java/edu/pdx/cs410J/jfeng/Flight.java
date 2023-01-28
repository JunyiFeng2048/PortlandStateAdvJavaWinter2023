package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight extends AbstractFlight {

  private int flightNumber;
  private String src;
  private String departDate;
  private String departTime;
  private String destination;
  private String arriveDate;
  private String arriveTime;

  public Flight() {}

  public Flight(int flightNumber, String src, String departDate, String departTime, String destination, String arriveDate, String arriveTime) {
    this.flightNumber = flightNumber;
    this.src = src;
    this.departDate = departDate;
    this.departTime = departTime;
    this.destination = destination;
    this.arriveDate = arriveDate;
    this.arriveTime = arriveTime;
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
    String departureString = departDate + " " + departTime;
    return departureString;
  }

  @Override
  public String getDestination()
  {
    return destination;
  }

  @Override
  public String getArrivalString()
  {
    String arriveString = arriveDate + " " + arriveTime;
    return arriveString;
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

  public String setDepartDate(String departDate)
  {
    if(checkValidDate(departDate))
      this.departDate = departDate;
    else
      return "Invalid Date";
    return "Valid Date";
  }

  public String setDepartTime(String departTime)
  {
    if(checkValidTime(departTime))
      this.departTime = departTime;
    else
      return "Invalid Time";
    return "Valid Time";
  }

  public String setDestination(String destination)
  {
    if(checkValidSrcAndDestCode(destination))
      this.destination = destination;
    else
      return "Invalid Src or Dest Code";
    return "Valid Src or Dest Code";
  }

  public String setArriveDate(String arriveDate)
  {
    if(checkValidDate(arriveDate))
      this.arriveDate = arriveDate;
    else
      return "Invalid Date";
    return "Valid Date";
  }

  public String setArriveTime(String arriveTime)
  {
    if(checkValidTime(arriveTime))
      this.arriveTime = arriveTime;
    else
      return "Invalid Time";
    return "Valid Time";
  }


  public boolean checkValidFlightNumber(int flightNumber)
  {
    if(flightNumber < 0 || flightNumber > 999)
      return false;
    return true;
  }

  public boolean checkValidDate(String date)
  {
    try {
      new SimpleDateFormat("MM/dd/yyyy").parse(date);
    } catch (ParseException e)
    {
      return false;
    }
    return true;
  }

  public boolean checkValidTime(String time)
  {
    try {
      new SimpleDateFormat("HH:mm").parse(time);
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

package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Flight extends AbstractFlight implements Comparable<Flight> {

  private int flightNumber;
  private String src;
  private String departDate;
  private String departTime;
  private String departPeriod;

  private String destination;
  private String arriveDate;
  private String arriveTime;
  private String arrivePeriod;
  private String duration;

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

  public String getDepartDate() {
    return departDate;
  }

  public String getDepartTime() {
    return departTime;
  }

  public String getArriveDate() {
    return arriveDate;
  }

  public String getArriveTime() {
    return arriveTime;
  }

  @Override
  public Date getDeparture()  ////
  {
    String dateAndTime = departDate + " " + departTime + " " + departPeriod;
    //System.out.println(dateAndTime);
    Date departure = new Date(dateAndTime);
    return departure;
  }

  @Override
  public String getDepartureString()
  {
    if(departPeriod == null)
    {
      String departureString = departDate + " " + departTime;
      return departureString;
    }
    Date departure = getDeparture();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    return simpleDateFormat.format(departure);
  }

  @Override
  public String getDestination()
  {
    return destination;
  }

  @Override
  public Date getArrival()  ////
  {
    String dateAndTime = arriveDate + " " + arriveTime + " " + arrivePeriod;
    try
    {
      Date arrive = new Date(dateAndTime);
      return arrive;
    }catch (IllegalArgumentException e)
    {
      System.err.println("Invalid Date or Time");
      System.exit(0);
    }

    return null;
  }

  @Override
  public String getArrivalString()
  {
    if(arrivePeriod == null)
    {
      String arriveString = arriveDate + " " + arriveTime;
      return arriveString;
    }
    Date arrive = getArrival();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    return simpleDateFormat.format(arrive);
  }
  public String getDepartPeriod() {
    return departPeriod;
  }

  public String getArrivePeriod() {
    return arrivePeriod;
  }

  public String getDuration()
  {
    return duration;
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

  public void setDepartPeriod(String departPeriod) {
    this.departPeriod = departPeriod;
  }

  public void setArrivePeriod(String arrivePeriod) {
    this.arrivePeriod = arrivePeriod;
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

  public void setDuration(String duration)
  {
    this.duration = duration;
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

  public String prettyPrintReturn()
  {
    return "Flight " + getNumber() +
            " departs " + getSource() +
            " at " + getDepartureString() +
            " and arrives " + getDestination() +
            " at " + getArrivalString();
  }

  @Override
  public int compareTo(Flight o) {
    int result = src.compareTo(o.src);
    if (result == 0) {
      result = getDepartureString().compareTo(o.getDepartureString());
    }
    return result;
  }

}

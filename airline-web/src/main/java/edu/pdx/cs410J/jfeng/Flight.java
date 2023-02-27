package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {
  private int flightNumber;
  private String sourceCode;
  private String departDate;
  private String departTime;
  private String departPeriod ;
  private String destinationCode;
  private String arrivalDate;
  private String arrivalTime;
  private String arrivalPeriod;
  private String duration;
  private String flightDetailString;

  public Flight() {}

  public Flight(int flightNumber, String sourceCode, String departDate, String departTime, String destinationCode, String arrivalDate, String arrivalTime)
  {
    this.flightNumber = flightNumber;
    this.sourceCode = sourceCode;
    this.departDate = departDate;
    this.departTime = departTime;
    this.destinationCode = destinationCode;
    this.arrivalDate = arrivalDate;
    this.arrivalTime = arrivalTime;
  }

  @Override
  public int getNumber() {
    return 0;
  }

  @Override
  public String getSource() {
    return null;
  }

  @Override
  public String getDepartureString() {
    return null;
  }

  @Override
  public String getDestination() {
    return null;
  }

  @Override
  public String getArrivalString() {
    return null;
  }


////////////////////////////

  public int getFlightNumber() {
    return flightNumber;
  }

  public String getSourceCode() {
    return sourceCode;
  }

  public String getDepartDate() {
    return departDate;
  }

  public String getDepartTime() {
    return departTime;
  }

  public String getDepartPeriod() {
    return departPeriod;
  }

  public String getDestinationCode() {
    return destinationCode;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public String getArrivalPeriod() {
    return arrivalPeriod;
  }

  public String getDuration() {
    return duration;
  }

  public void setFlightNumber(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  public void setSourceCode(String sourceCode) {
    this.sourceCode = sourceCode;
  }

  public void setDepartDate(String departDate) {
    this.departDate = departDate;
  }

  public void setDepartTime(String departTime) {
    this.departTime = departTime;
  }

  public void setDepartPeriod(String departPeriod) {
    this.departPeriod = departPeriod;
  }

  public void setDestinationCode(String destinationCode) {
    this.destinationCode = destinationCode;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setArrivalPeriod(String arrivalPeriod) {
    this.arrivalPeriod = arrivalPeriod;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

}
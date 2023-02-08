package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A skeletal implementation of the <code>TextParser</code> class for Project 2.
 */
public class TextParser implements AirlineParser<Airline>
{
  private String filePath;
  private Airline airline;

  public TextParser(String filePath, Airline airline)
  {
    this.filePath = filePath;
    this.airline = airline;

  }

  public boolean checkValidFlightNumberInFile(String flightNumber)
  {
    try
    {
      int fn = Integer.parseInt(flightNumber);
      if(fn < 0 || fn > 9999)
      {
        System.err.println("Invalid Flight Number in this file. Fix it first");
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      System.err.println("Invalid Flight Number in this file. Fix it first");
      return false;
    }
    return true;
  }

  public boolean checkValidDateAndTimeInFile(String dateAndTime)
  {
    try {
      new SimpleDateFormat("MM/dd/yyyy HH:mm aa").parse(dateAndTime);
    } catch (ParseException e)
    {
      System.err.println("Invalid Date and Time in this file. Fix it first");
      return false;
    }
    return true;
  }


  public boolean checkValidSrcAndDestCodeInFile(String str)
  {
    if(str.length() != 3)
      return false;
    for (int i = 0; i < 3; i++) {
      if ((Character.isLetter(str.charAt(i)) == false))
      {
        System.err.println("Invalid Src code or Dest code in this file. Fix it first");
        return false;
      }
    }
    return true;
  }

  public Flight fileDataIsValid(String[] fileDataArray)
  {

    Flight flight = new Flight();
    String temp = fileDataArray[3] + " " + fileDataArray[5];
    String delimiter = " ";
    String[] dateAndTime;
    dateAndTime = temp.split(delimiter);

    if(checkValidFlightNumberInFile(fileDataArray[1])
            && checkValidSrcAndDestCodeInFile(fileDataArray[2])
            && checkValidDateAndTimeInFile(fileDataArray[3])
            && checkValidSrcAndDestCodeInFile(fileDataArray[4])
            && checkValidDateAndTimeInFile(fileDataArray[5]))
    {
      flight.setFlightNumber(Integer.parseInt(fileDataArray[1]));
      flight.setSource(fileDataArray[2]);
      flight.setDepartDate(dateAndTime[0]);
      flight.setDepartTime(dateAndTime[1]);
      flight.setDepartPeriod(dateAndTime[2]);
      flight.setDestination(fileDataArray[4]);
      flight.setArriveDate(dateAndTime[3]);
      flight.setArriveTime(dateAndTime[4]);
      flight.setArrivePeriod(dateAndTime[5]);
      return flight;
    }
    return null;
  }

  @Override
  public Airline parse() throws ParserException
  {
    InputStream inputStream;
    try {
      inputStream = new FileInputStream(filePath);
    } catch (FileNotFoundException e) {
      return airline; //if the file does not exist
    }
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    try (
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    ) {
      String[] fileDataArray = new String[6];
      Flight flight;
      while(bufferedReader.ready())
      {
        for (int i = 0; i < 6; i++)
        {
          fileDataArray[i] = bufferedReader.readLine();
          if(i == 0 && !fileDataArray[i].equals(airline.getName()))
          {
            String wrongAirline = String.format("This is not the file for Airline " + airline.getName());
            System.err.println(wrongAirline);
            return null;
          }
        }
        flight = fileDataIsValid(fileDataArray); ///////////////////
        if(flight == null)
        {
          return null;
        }
        else
        {
          airline.addFlight(flight);
        }
      }
    } catch (IOException e) {
      //throw new ParserException("While parsing airline text", e);
      System.err.println("Error parsing airline text");
      return null;
    }
    return airline;
  }
}

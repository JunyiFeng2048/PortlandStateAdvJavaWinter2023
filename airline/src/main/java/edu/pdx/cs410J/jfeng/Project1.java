package edu.pdx.cs410J.jfeng;

import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidDateAndTime(String dateAndTime)
  {
    try {
      Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateAndTime);
    } catch (ParseException e)
    {
      System.err.println("Invalid Date and Time");
      return false;
    }
    return true;
  }

  static boolean isValidFlightNumber(String flightNumber)
  {
    try
    {
      int fn = Integer.parseInt(flightNumber);
    }
    catch (NumberFormatException e)
    {
      System.err.println("Invalid FlightNumber");
      return false;
    }
    return true;
  }

  public static void main(String[] args) throws IOException
  {

    if (args.length == 0)
    {
      System.err.println("Missing command line arguments");
      return;
    }
    else if (args.length > 7)
    {
      System.err.println("Too many command line arguments");
      return;
    }

    String firstArg = args[0];
    if (args.length != 7 && !firstArg.equals("-README") )
    {
      System.err.println("Not enough arguments");
      return;
    }
    else if(firstArg.equals("-README"))
    {
      try (
              InputStream readme = Project1.class.getResourceAsStream("README.txt")
      )
      {
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String README = reader.readLine();
        System.out.println(README);
      }
    }
    else if (firstArg.equals("-print"))
    {
      if(isValidFlightNumber(args[2]) && isValidDateAndTime(args[4]) && isValidDateAndTime(args[6]))
      {
        Flight flight = new Flight(args[1], Integer.parseInt(args[2]), args[3], args[4], args[5], args[6]);
        System.out.println(flight.toString());
      }

    }
  }

}
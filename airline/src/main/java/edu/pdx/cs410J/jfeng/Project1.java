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
  //@VisibleForTesting
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
      if(fn < 0)
      {
        System.err.println("Invalid Flight Number");
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      System.err.println("Invalid Flight Number");
      return false;
    }
    return true;
  }

  static boolean isValidSrcAndDestCode(String str)
  {
    if(str.length() != 3)
    {
      System.err.println("Invalid Src or Dest Code");
      return false;
    }
    for (int i = 0; i < 3; i++) {
      if ((Character.isLetter(str.charAt(i)) == false)) {
        System.err.println("Invalid Src or Dest Code");
        return false;
      }
    }
    return true;
  }

  static void getREADME()
  {
    try (
            InputStream readme = Project1.class.getResourceAsStream("README.txt")
    )
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String s;
      while ((s=reader.readLine())!=null)
        System.out.println(s);

    } catch (IOException e) {
      System.err.printf("README.txt does not exist");;
    }
  }

  public static void main(String[] args) throws IOException
  {
    if (args.length == 0)
    {
      System.err.println("Missing command line arguments");
      System.out.println("\tusage: java -jar target/airline-2023.0.0.jar [options] <args>\n" +
              "\t\targs are (in this order):\n" +
              "\t\t\tairline The name of the airline\n" +
              "\t\t\tflightNumber The flight number\n" +
              "\t\t\tsrc Three-letter code of departure airport\n" +
              "\t\t\tdepart Departure date and time (24-hour time)\n" +
              "\t\t\tdest Three-letter code of arrival airport\n" +
              "\t\t\tarrive Arrival date and time (24-hour time)\n" +
              "\t\toptions are (options may appear in any order):\n" +
              "\t\t\t-print Prints a description of the new flight\n" +
              "\t\t\t-README Prints a README for this project and exits\n" +
              "\t*Date and time should be in the format: mm/dd/yyyy hh:mm");
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
      getREADME();
    }
    else if (firstArg.equals("-print"))
    {
      if(isValidFlightNumber(args[2]) && isValidDateAndTime(args[4]) && isValidDateAndTime(args[6])
      && isValidSrcAndDestCode(args[3]) && isValidSrcAndDestCode(args[5]))
      {
        //Flight flight = new Flight(args[1], Integer.parseInt(args[2]), args[3], args[4], args[5], args[6]);
        Flight flight = new Flight();
        flight.setFlightName(args[1]);
        flight.setFlightNumber(Integer.parseInt(args[2]));
        flight.setSource(args[3]);
        flight.setDepartTime(args[4]);;
        flight.setDestination(args[5]);
        flight.setArriveTime(args[6]);
        System.out.println(flight.toString());
      }
    }
  }

}
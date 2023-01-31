package edu.pdx.cs410J.jfeng;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project1
{
  //@VisibleForTesting
  static boolean isValidDateAndTime(String date, String time)
  {
    String dateAndTime = date + " " + time;
    try {
      new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateAndTime);
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
      if(fn < 0 || fn > 999)
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
      if ((Character.isLetter(str.charAt(i)) == false))
      {
        System.err.println("Invalid Src or Dest Code");
        return false;
      }
    }
    return true;
  }

  static boolean isValidFileName(String fileName)
  {
    String fileType = ".txt";

    if(fileType.equals(fileName.substring(fileName.length() - 4)) && !fileName.contains("/"))
    {
      return true;
    }
    System.err.println("Invalid file name, must just the file name and end with '.txt'");
    return false;

  }

  static boolean getREADME()
  {
    try (
            InputStream readme = Project1.class.getResourceAsStream("README.txt")
    )
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String s;
      while ((s=reader.readLine()) != null)
        System.out.println(s);

    } catch (IOException e) {
      System.err.printf("README.txt does not exist");;
      return false;
    }
    return true;
  }

  static void usageMessage()
  {
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
  }

  static boolean validation(String[] argArray)
  {
    if (isValidFlightNumber(argArray[0]) && isValidDateAndTime(argArray[2],argArray[3])
            && isValidDateAndTime(argArray[5],argArray[6]) && isValidSrcAndDestCode(argArray[1])
            && isValidSrcAndDestCode(argArray[4]))
    {
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws IOException, ParserException
  {
    if (args.length == 0)
    {
      System.err.println("Missing command line arguments");
      usageMessage();
      return;
    }

    String firstArg = args[0];
    int argsLength = args.length;
    Flight flight = new Flight();
    if(firstArg.equals("-README"))
    {
      getREADME();
    }
    else if(argsLength == 8 && !firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 1, 8);
      if(validation(argArray))
      {
        flight.setFlightNumber(Integer.parseInt(args[1]));
        flight.setSource(args[2]);
        flight.setDepartDate(args[3]);
        flight.setDepartTime(args[4]);
        flight.setDestination(args[5]);
        flight.setArriveDate(args[6]);
        flight.setArriveTime(args[7]);
        Airline airline = new Airline(args[0]);
        airline.addFlight(flight);
        System.out.println("Successfully added a flight to " + args[0]);
      }
    }
    else if(argsLength == 9 && firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 2, 9);

      if(validation(argArray))
      {
        flight.setFlightNumber(Integer.parseInt(args[2]));
        flight.setSource(args[3]);
        flight.setDepartDate(args[4]);
        flight.setDepartTime(args[5]);;
        flight.setDestination(args[6]);
        flight.setArriveDate(args[7]);
        flight.setArriveTime(args[8]);
        Airline airline = new Airline(args[1]);
        airline.addFlight(flight);
        System.out.println(flight.toString());
      }
    }
    else if(argsLength == 10 && firstArg.equals("-textFile"))
    {
      String argArray[] = Arrays.copyOfRange(args, 3, 10);
      String fileName = args[1];
      if(isValidFileName(fileName))
      {
        if(validation(argArray))
        {
          String directory = "./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/%s";
          String filePath = String.format(directory,fileName);

          Airline airline = new Airline(args[2]);

          TextParser textParser = new TextParser(filePath,airline);
          airline = textParser.parse();
          if(airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          flight.setFlightNumber(Integer.parseInt(args[3]));
          flight.setSource(args[4]);
          flight.setDepartDate(args[5]);
          flight.setDepartTime(args[6]);;
          flight.setDestination(args[7]);
          flight.setArriveDate(args[8]);
          flight.setArriveTime(args[9]);
          airline.addFlight(flight);

          TextDumper textDumper = new TextDumper(filePath);
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + fileName);

        }
      }
    }
    else if(firstArg.equals("lol"))
    {
      Airline airline = new Airline("lol");
      String filePath = "./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/emptyTestParser.txt";
      TextParser textParser = new TextParser(filePath,airline);
      airline = textParser.parse();
      ArrayList<Flight> flightArrayList = (ArrayList<Flight>) airline.getFlights();
      for (int i = 0; i < flightArrayList.size(); i++)
      {
        System.out.println(airline.getName());
        System.out.println(flightArrayList.get(i).getNumber());
        System.out.println(flightArrayList.get(i).getSource());
        System.out.println(flightArrayList.get(i).getDepartureString());
        System.out.println(flightArrayList.get(i).getDestination());
        System.out.println(flightArrayList.get(i).getArrivalString());
      }
    }
    else
    {
      System.err.println("Invalid or missing command line argument. See '-README'");
    }

  }

}
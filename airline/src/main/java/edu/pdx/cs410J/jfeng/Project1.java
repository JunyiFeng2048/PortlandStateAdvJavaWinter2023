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
  @VisibleForTesting
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
            "\t\t\tairline \tThe name of the airline\n" +
            "\t\t\tflightNumber \tThe flight number\n" +
            "\t\t\tsrc \tThree-letter code of departure airport\n" +
            "\t\t\tdepart \tDeparture date and time (24-hour time)\n" +
            "\t\t\tdest \tThree-letter code of arrival airport\n" +
            "\t\t\tarrive \tArrival date and time (24-hour time)\n" +
            "\t\toptions are (options may appear in any order):\n" +
            "\t\t\t-textFile file \tWhere to read/write the airline info\n" +
            "\t\t\t-print \tPrints a description of the new flight\n" +
            "\t\t\t-README \tPrints a README for this project and exits\n" +
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
    String directory = "./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/%s";
    String firstArg = args[0];
    int argsLength = args.length;
    if(firstArg.equals("-README"))
    {
      getREADME();
    }
    else if(argsLength == 8 && !firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 1, 8);
      if(validation(argArray))
      {
        Flight flight = new Flight(Integer.parseInt(args[1]),args[2],
                args[3],args[4],args[5],args[6],args[7]);
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
        Flight flight = new Flight(Integer.parseInt(args[2]),
                args[3],args[4],args[5],args[6],args[7],args[8]);
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
          String filePath = String.format(directory,fileName);

          Airline airline = new Airline(args[2]);

          TextParser textParser = new TextParser(filePath,airline);
          airline = textParser.parse();
          if(airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[3]),args[4],
                  args[5],args[6],args[7],args[8],args[9]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + fileName);
        }
      }
    }
    else if(argsLength == 11 && (firstArg.equals("-textFile") && args[2].equals("-print")))
    {
      String argArray[] = Arrays.copyOfRange(args, 4, 11);
      String fileName = args[1];
      if(isValidFileName(fileName))
      {
        if(validation(argArray))
        {
          String filePath = String.format(directory,fileName);

          Airline airline = new Airline(args[3]);

          TextParser textParser = new TextParser(filePath,airline);
          airline = textParser.parse();
          if(airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[4]),args[5],
                  args[6],args[7],args[8],args[9],args[10]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + fileName);
          System.out.println(flight.toString());
        }
      }
    }
    else if(argsLength == 11 && (firstArg.equals("-print") && args[1].equals("-textFile")))
    {
      String argArray[] = Arrays.copyOfRange(args, 4, 11);
      String fileName = args[2];
      if(isValidFileName(fileName))
      {
        if(validation(argArray))
        {
          String filePath = String.format(directory,fileName);

          Airline airline = new Airline(args[3]);

          TextParser textParser = new TextParser(filePath,airline);
          airline = textParser.parse();
          if(airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[4]),args[5],
                  args[6],args[7],args[8],args[9],args[10]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + fileName);
          System.out.println(flight.toString());

        }
      }
    }

    else
    {
      System.err.println("Invalid or missing command line argument. See '-README'");
    }

  }

}
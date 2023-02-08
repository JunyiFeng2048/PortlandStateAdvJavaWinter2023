package edu.pdx.cs410J.jfeng;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * The main class for the CS410J airline Project
 */
public class Project3
{
  @VisibleForTesting
  static boolean isValidDateAndTime(String dateAndTime)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    boolean valid;
    try {
      sdf.parse(dateAndTime);
      sdf.setLenient(false);
      valid = true;
    } catch (ParseException e) {
      System.err.println("Invalid Date or Time");
      valid = false;
    }
    return valid;
  }

  static boolean isValidFlightNumber(String flightNumber)
  {
    try
    {
      int fn = Integer.parseInt(flightNumber);
      if(fn < 0 || fn > 9999)
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

    AirportNames airportNames = null;
    Map<String,String > namesMap = airportNames.getNamesMap();
    if(namesMap.containsKey(str) || namesMap.containsKey(str.toUpperCase()))
    {
      return true;
    }
    System.err.println("Invalid Src or Dest Code");
    return false;
  }

  static boolean isValidFileNameAndPath(String filePath)
  {
    String fileType = ".txt";
    if(filePath.length() < 4 || !fileType.equals(filePath.substring(filePath.length() - 4)))
    {
      System.err.println("Invalid file name, must end with '.txt'");
      return false;
    }
    String[] directory = filePath.split("/");
    String dir = "";
    for(int i=0; i<directory.length-1; i++)
    {
      dir =  dir + directory[i] + "/";
    }
    Path path = Paths.get(dir);
    if(!Files.isDirectory(path))
    {
      System.out.println(path);
      System.err.println("Not a directory");
      return false;
    }
    return true;
  }

  static boolean isValidDuration(String departure, String arrival)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    try{
      Date date1 = sdf.parse(departure);
      Date date2 = sdf.parse(arrival);
      long time_difference = date2.getTime() - date1.getTime();
      if(time_difference < 0)
      {
        System.err.println("Invalid Date or Time");
        return false;
      }
    }
    catch (ParseException e) {
      System.err.println("Invalid Date or Time");
      return false;
    }
    return true;
  }

  static String calDuration(String departure, String arrival)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    try {
      Date date1 = sdf.parse(departure);
      Date date2 = sdf.parse(arrival);
      long time_difference = date2.getTime() - date1.getTime();
      long second = (time_difference / 1000) % 60;
      long minute = (time_difference / (1000 * 60)) % 60;
      long hour = (time_difference / (1000 * 60 * 60)) % 24;
      String time = String.format("%02d:%02d:%02d", hour, minute, second);
      return time;
    }
    catch (ParseException e) {
      System.err.println("Invalid Date or Time");
      return null;
    }
  }

  static boolean getREADME()
  {
    try (
            InputStream readme = Project3.class.getResourceAsStream("README.txt")
    )
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String s;
      while ((s=reader.readLine()) != null)
        System.out.println(s);

    } catch (IOException e) {
      System.err.printf("README.txt does not exist");
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
            "\t\t\t-textFile file.txt(./folder/file.txt or an absolute path) \tWhere to read/write the airline info\n" +
            "\t\t\t-print \tPrints a description of the new flight\n" +
            "\t\t\t-README \tPrints a README for this project and exits\n" +
            "\t*Date and time should be in the format: mm/dd/yyyy hh:mm");
  }

  static boolean validation(String[] argArray)
  {
    String departure = argArray[2] + " " + argArray[3] + " " + argArray[4];
    String arrival = argArray[6] + " " + argArray[7] + " " + argArray[8];
    if (isValidFlightNumber(argArray[0]) && isValidDateAndTime(departure)
            && isValidDateAndTime(arrival) && isValidSrcAndDestCode(argArray[1])
            && isValidSrcAndDestCode(argArray[5]) && isValidDuration(departure,arrival))
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
    if (firstArg.equals("-README"))
    {
      getREADME();
    }
    else if (argsLength == 10 && !firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 1, 10);
      if (validation(argArray)) {
        Flight flight = new Flight(Integer.parseInt(args[1]), args[2],
                args[3], args[4], args[6], args[7], args[8]);
        flight.setDepartPeriod(args[5]);
        flight.setArrivePeriod(args[9]);
        Airline airline = new Airline(args[0]);
        airline.addFlight(flight);
        System.out.println("Successfully added a flight to " + args[0]);
        System.out.println(args[0] + " " + flight.prettyPrintReturn());

      }
    }
    else if (argsLength == 11 && firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 2, 11);
      if (validation(argArray))
      {
        Flight flight = new Flight(Integer.parseInt(args[2]),
                args[3], args[4], args[5], args[7], args[8], args[9]);
        flight.setDepartPeriod(args[6]);
        flight.setArrivePeriod(args[10]);
        Airline airline = new Airline(args[1]);
        airline.addFlight(flight);
        System.out.println(args[1] + " " + flight.prettyPrintReturn());
      }
    }
    else if (argsLength == 12 && firstArg.equals("-textFile"))
    {
      String argArray[] = Arrays.copyOfRange(args, 3, 12);
      String filePath = args[1];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[2]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[3]), args[4],
                  args[5], args[6], args[8], args[9], args[10]);
          flight.setDepartPeriod(args[7]);
          flight.setArrivePeriod(args[11]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          airline.sort();/////
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
        }
      }
    }
    else if (argsLength == 13 && (firstArg.equals("-textFile") && args[2].equals("-print"))) {
      String argArray[] = Arrays.copyOfRange(args, 4, 13);
      String filePath = args[1];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[3]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[4]), args[5],
                  args[6], args[7], args[9], args[10], args[11]);
          flight.setDepartPeriod(args[8]);
          flight.setArrivePeriod(args[12]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          airline.sort();/////
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
          System.out.println(flight.toString());
        }
      }
    }
    else if (argsLength == 13 && (firstArg.equals("-print") && args[1].equals("-textFile")))
    {
      String argArray[] = Arrays.copyOfRange(args, 4, 13);
      String filePath = args[2];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[3]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[4]), args[5],
                  args[6], args[7], args[9], args[10], args[11]);
          flight.setDepartPeriod(args[8]);
          flight.setArrivePeriod(args[12]);
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          airline.sort();/////
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
          System.out.println(flight.toString());
        }
      }
    }
    else if (argsLength == 11 && firstArg.equals("-pretty"))
    {
      String argArray[] = Arrays.copyOfRange(args, 2, 11);
      if (validation(argArray))
      {
        Airline airline = new Airline(args[1]);
        Flight flight = new Flight(Integer.parseInt(args[2]), args[3],
                args[4], args[5], args[7], args[8], args[9]);
        flight.setDepartPeriod(args[6].toLowerCase());
        flight.setArrivePeriod(args[10].toLowerCase());
        flight.setDuration(calDuration(flight.getDepartureString(), flight.getArrivalString()));
        airline.addFlight(flight);
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        prettyPrinter.print(airline);
      }
    }
    else if (argsLength == 12 && firstArg.equals("-pretty")) ////////
    {
      String argArray[] = Arrays.copyOfRange(args, 3, 12);
      String filePath = args[1];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[2]);
          PrettyPrinter prettyPrinter = new PrettyPrinter(filePath);
          Flight flight = new Flight(Integer.parseInt(args[3]), args[4],
                  args[5], args[6], args[8], args[9], args[10]);
          flight.setDepartPeriod(args[7].toLowerCase());
          flight.setArrivePeriod(args[11].toLowerCase());
          flight.setDuration(calDuration(flight.getDepartureString(), flight.getArrivalString()));
          airline.addFlight(flight);
          prettyPrinter.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
        }
      }
    }
    else if (argsLength == 13 && ((firstArg.equals("-print") && args[1].equals("-pretty"))))
    {
      String argArray[] = Arrays.copyOfRange(args, 4, 13);
      String filePath = args[2];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[3]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          PrettyPrinter prettyPrinter = new PrettyPrinter(filePath);
          Flight flight = new Flight(Integer.parseInt(args[4]), args[5],
                  args[6], args[7], args[9], args[10], args[11]);
          flight.setDepartPeriod(args[8].toLowerCase());
          flight.setArrivePeriod(args[12].toLowerCase());
          flight.setDuration(calDuration(flight.getDepartureString(), flight.getArrivalString()));
          airline.addFlight(flight);
          prettyPrinter.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
        }
      }
    }
    else if (argsLength == 13 && (firstArg.equals("-pretty") && args[1].equals("-textFile")))
    {
      String argArray[] = Arrays.copyOfRange(args, 4, 13);
      String filePath = args[2];
      if (isValidFileNameAndPath(filePath))
      {
        if (validation(argArray))
        {
          Airline airline = new Airline(args[3]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[4]), args[5],
                  args[6], args[7], args[9], args[10], args[11]);
          flight.setDepartPeriod(args[8]);
          flight.setArrivePeriod(args[12]);
          flight.setDuration(calDuration(flight.getDepartureString(), flight.getArrivalString()));
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          airline.sort();/////
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
          //System.out.println(flight.toString());
          PrettyPrinter prettyPrinter = new PrettyPrinter(filePath);
          prettyPrinter.print(airline);

        }
      }
    }
    else
    {
      System.err.println("Invalid or missing command line argument. See '-README'");
    }
  }
}
package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project4
{
  static String calDuration(String departure, String arrival)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    try {
      Date date1 = sdf.parse(departure);
      Date date2 = sdf.parse(arrival);
      long time_difference = date2.getTime() - date1.getTime();
      long minute = (time_difference / (1000 * 60)) % 60;
      long hour = (time_difference / (1000 * 60 * 60)) % 24;
      long day = (time_difference / (1000*60*60*24)) % 365;
      long year = (time_difference / (1000l*60*60*24*365));
      String time = String.format("%02dy %02dd %02dh %02dm ", year, day, hour, minute);
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
            InputStream readme = Project4.class.getResourceAsStream("README.txt")
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


  public static void main(String[] args) throws IOException, ParserException, ParserConfigurationException, SAXException
  {
    if (args.length == 0)
    {
      System.err.println("Missing command line arguments");
      usageMessage();
      return;
    }
    String firstArg = args[0];
    ProjectValidation pv = new ProjectValidation();
    int argsLength = args.length;
    if (firstArg.equals("-README"))
    {
      getREADME();
    }
    else if (argsLength == 10 && !firstArg.equals("-print"))
    {
      String argArray[] = Arrays.copyOfRange(args, 1, 10);
      if (pv.validation(argArray)) {
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
      if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
      if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
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
          PrettyPrinter prettyPrinter = new PrettyPrinter(filePath);
          prettyPrinter.print(airline);
        }
      }
    }
    else if (argsLength == 14 && ((firstArg.equals("-pretty") && args[1].equals("-print") && args[2].equals("textFile"))
            || ((firstArg.equals("-print") && args[1].equals("-pretty") && args[2].equals("textFile")))))
    {
      String argArray[] = Arrays.copyOfRange(args, 5, 14);
      String filePath = args[3];
      if (pv.isValidTextFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
        {
          Airline airline = new Airline(args[4]);
          TextParser textParser = new TextParser(filePath, airline);
          airline = textParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline text");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[5]), args[6],
                  args[7], args[8], args[10], args[11], args[12]);
          flight.setDepartPeriod(args[9]);
          flight.setArrivePeriod(args[13]);
          flight.setDuration(calDuration(flight.getDepartureString(), flight.getArrivalString()));
          airline.addFlight(flight);
          TextDumper textDumper = new TextDumper(filePath);
          airline.sort();/////
          textDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
          PrettyPrinter prettyPrinter = new PrettyPrinter(filePath);
          prettyPrinter.print(airline);
        }
      }
    }
    else if (argsLength == 12 && firstArg.equals("-xmlFile"))
    {
      String argArray[] = Arrays.copyOfRange(args, 3, 12);
      String filePath = args[1];
      if (pv.isValidXmlFileNameAndPath(filePath))
      {
        if (pv.validation(argArray))
        {
          Airline airline = new Airline(args[2]);
          XmlParser xmlParser = new XmlParser(filePath, airline);
          airline = xmlParser.parse();
          if (airline == null)
          {
            System.err.println("Error parsing airline xml");
            return;
          }
          Flight flight = new Flight(Integer.parseInt(args[3]), args[4],
                  args[5], args[6], args[8], args[9], args[10]);
          flight.setDepartPeriod(args[7]);
          flight.setArrivePeriod(args[11]);
          airline.addFlight(flight);
          XmlDumper xmlDumper = new XmlDumper(filePath);
          airline.sort();/////
          xmlDumper.dump(airline);
          System.out.println("Successfully added a flight to " + filePath);
        }
      }
    }
    else
    {
      System.err.println("Invalid or missing command line argument. See '-README'");
    }
  }
}
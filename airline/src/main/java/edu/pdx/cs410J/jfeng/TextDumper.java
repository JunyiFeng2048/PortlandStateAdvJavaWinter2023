package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;


/**
 * A skeletal implementation of the <code>TextDumper</code> class for Project 2.
 */
public class TextDumper implements AirlineDumper<Airline>
{
  private String filePath;

  public TextDumper(String filePath)
  {
    this.filePath = filePath;
  }

  private void writeFile(Airline airline) throws FileNotFoundException
  {
    PrintWriter printWriter = new PrintWriter(new FileOutputStream((filePath)));
    ArrayList<Flight> flightArrayList = (ArrayList<Flight>) airline.getFlights();

    for (int i = 0; i < flightArrayList.size(); i++)
    {
      printWriter.println(airline.getName());
      printWriter.println(flightArrayList.get(i).getNumber());
      printWriter.println(flightArrayList.get(i).getSource());
      printWriter.println(flightArrayList.get(i).getDepartureString());
      printWriter.println(flightArrayList.get(i).getDestination());
      printWriter.println(flightArrayList.get(i).getArrivalString());
      printWriter.flush();
    }
    printWriter.close();
    System.out.println("Successfully added a flight to " + filePath);
  }


  @Override
  public void dump(Airline airline) throws IOException
  {
    File file = new File(filePath);
    if(file.exists())
    {
      InputStream inputStream = new FileInputStream(filePath);
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

      try (
              BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
      )
      {
        String fileAirline = bufferedReader.readLine();
        if(!fileAirline.equals(airline.getName()))
        {
          String wrongAirline = String.format("This is not the file for Airline " + airline.getName());
          System.out.println(wrongAirline);
        }
        else
        {
          writeFile(airline);
        }
      }
      catch (NullPointerException e)
      {
        writeFile(airline);
      }
    }
    else
    {
      writeFile(airline);
    }

  }

  public void prettyDump(Airline airline) throws IOException
  {
    PrintWriter printWriter = new PrintWriter(new FileOutputStream((filePath),true));
    ArrayList<Flight> flightArrayList = (ArrayList<Flight>) airline.getFlights();
    airline.sort();
    for (int i = 0; i < flightArrayList.size(); i++)
    {
      System.out.println("Airline: " + airline.getName() +
              ", Flight: " + flightArrayList.get(i).getNumber() +
              ", Source: " + flightArrayList.get(i).getSource() +
              ", Departure Time: " + flightArrayList.get(i).getDepartureString() +
              ", Destination: " + flightArrayList.get(i).getDestination() +
              ", Arrival Time: " + flightArrayList.get(i).getArrivalString() +
              ", Duration: " + flightArrayList.get(i).getDuration());
      printWriter.println("Airline: " + airline.getName() +
              ", Flight: " + flightArrayList.get(i).getNumber() +
              ", Source: " + flightArrayList.get(i).getSource() +
              ", Departure Time: " + flightArrayList.get(i).getDepartureString() +
              ", Destination: " + flightArrayList.get(i).getDestination() +
              ", Arrival Time: " + flightArrayList.get(i).getArrivalString() +
              ", Duration: " + flightArrayList.get(i).getDuration());
      printWriter.flush();
    }
    printWriter.close();
    System.out.println("Successfully added a flight to " + filePath);
  }

  public void prettyPrint(Airline airline)
  {
    ArrayList<Flight> flightArrayList = (ArrayList<Flight>) airline.getFlights();
    airline.sort();
    for (int i = 0; i < flightArrayList.size(); i++)
    {
      System.out.println("Airline: " + airline.getName() +
              ", Flight: " + flightArrayList.get(i).getNumber() +
              ", Source: " + flightArrayList.get(i).getSource() +
              ", Departure Time: " + flightArrayList.get(i).getDepartureString() +
              ", Destination: " + flightArrayList.get(i).getDestination() +
              ", Arrival Time: " + flightArrayList.get(i).getArrivalString() +
              ", Duration: " + flightArrayList.get(i).getDuration());
    }
  }
}

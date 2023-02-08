package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;

public class PrettyPrinter implements AirlineDumper<Airline> {


    private String filePath;

    public PrettyPrinter(String filePath)
    {
        this.filePath = filePath;
    }


    @Override
    public void dump(Airline airline) throws IOException
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

    }

}

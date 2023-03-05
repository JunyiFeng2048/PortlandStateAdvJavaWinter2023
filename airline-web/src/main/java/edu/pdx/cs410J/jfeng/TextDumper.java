package edu.pdx.cs410J.jfeng;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public void dump(Airline airline, String src, String dest) {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ){
      String airlineName = airline.getName();
      for (Flight flight : airline.getFlights())
      {
        if(src == null && dest == null)
        {
          pw.println(airlineName + ": " + flight.getFlightNumber() + " " +
                  flight.getSourceCode() + " " + flight.getDepartDate() + " " +
                  flight.getDepartTime() + " " + flight.getDepartPeriod() + " " +
                  flight.getDestinationCode() + " " + flight.getArrivalDate() + " " +
                  flight.getArrivalTime() + " " + flight.getArrivalPeriod());
        }
        else if (src != null && dest != null && src.equals(flight.getSourceCode()) && dest.equals(flight.getDestinationCode()))
        {
          pw.println(airlineName + ": " + flight.getFlightNumber() + " " +
                  flight.getSourceCode() + " " + flight.getDepartDate() + " " +
                  flight.getDepartTime() + " " + flight.getDepartPeriod() + " " +
                  flight.getDestinationCode() + " " + flight.getArrivalDate() + " " +
                  flight.getArrivalTime() + " " + flight.getArrivalPeriod());
        }
      }
      pw.flush();
    }
  }
}

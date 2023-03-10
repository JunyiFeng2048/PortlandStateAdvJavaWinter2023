package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperParserTest {

/*
  @Test
  void airlineWithOneFlightCanBeDumpedAndParsed() throws ParserException {
    String airlineName = "Airline";
    int flightNumber = 122;
    String sourceCode = "PDX";
    String departDate = "11/11/2011";
    String departTime = "11:11";
    String departPeriod = "am";
    String destinationCode = "ABE";
    String arrivalDate = "11/11/2111";
    String arrivalTime = "11:11";
    String arrivalPeriod = "pm";
    Airline airline = new Airline(airlineName);
    Flight flight = new Flight(flightNumber,sourceCode,departDate,departTime,destinationCode,arrivalDate,arrivalTime);
    flight.setArrivalPeriod(arrivalPeriod);
    flight.setDepartPeriod(departPeriod);
    airline.addFlight(flight);

    Airline read = dumpAndParse(airline);
    assertThat(read.getName(), equalTo(airlineName));
    assertThat(read.getFlights().iterator().next().getFlightNumber(), equalTo(flightNumber));
  }

  private Airline dumpAndParse(Airline airline) throws ParserException {
    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    String src = null;
    String dest = null;

    dumper.dump(airline, src, dest);

    String text = sw.toString();

    TextParser parser = new TextParser(new StringReader(text));
    return parser.parse();
  }


    @Test
    void testDumperWithNoSrcAndDest
    {
        PrintWriter pw = new PrintWriter()
        String airlineName = "Airline";
        int flightNumber = 122;
        String sourceCode = "PDX";
        String departDate = "11/11/2011";
        String departTime = "11:11";
        String departPeriod = "am";
        String destinationCode = "ABE";
        String arrivalDate = "11/11/2111";
        String arrivalTime = "11:11";
        String arrivalPeriod = "pm";
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight(flightNumber,sourceCode,departDate,departTime,destinationCode,arrivalDate,arrivalTime);
        flight.setArrivalPeriod(arrivalPeriod);
        flight.setDepartPeriod(departPeriod);
        airline.addFlight(flight);
        TextDumper textDumper = new TextDumper(writer);

    }
    */
    @Test
    void textdumperWithSrcAndDest()
    {
        StringWriter sw = new StringWriter();
        TextDumper dumper = new TextDumper(sw);
        String airlineName = "Airline";
        int flightNumber = 122;
        String sourceCode = "PDX";
        String departDate = "11/11/2011";
        String departTime = "11:11";
        String departPeriod = "am";
        String destinationCode = "ABE";
        String arrivalDate = "11/11/2111";
        String arrivalTime = "11:11";
        String arrivalPeriod = "pm";
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight(flightNumber,sourceCode,departDate,departTime,destinationCode,arrivalDate,arrivalTime);
        flight.setArrivalPeriod(arrivalPeriod);
        flight.setDepartPeriod(departPeriod);
        airline.addFlight(flight);

        dumper.dump(airline,sourceCode,destinationCode);
    }

    @Test
    void textdumperWithoutSrcAndDest()
    {
        StringWriter sw = new StringWriter();
        TextDumper dumper = new TextDumper(sw);
        String airlineName = "Airline";
        int flightNumber = 122;
        String sourceCode = null;
        String departDate = "11/11/2011";
        String departTime = "11:11";
        String departPeriod = "am";
        String destinationCode = null;
        String arrivalDate = "11/11/2111";
        String arrivalTime = "11:11";
        String arrivalPeriod = "pm";
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight(flightNumber,sourceCode,departDate,departTime,destinationCode,arrivalDate,arrivalTime);
        flight.setArrivalPeriod(arrivalPeriod);
        flight.setDepartPeriod(departPeriod);
        airline.addFlight(flight);

        dumper.dump(airline,sourceCode,destinationCode);
    }

    @Test
    void testDumpNotFound(){
        StringWriter sw = new StringWriter();
        TextDumper dumper = new TextDumper(sw);
        dumper.dumpNotFound();
    }


}
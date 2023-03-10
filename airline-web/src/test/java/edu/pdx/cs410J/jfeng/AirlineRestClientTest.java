package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * A unit test for the REST client that demonstrates using mocks and
 * dependency injection
 */
public class AirlineRestClientTest {
  private static final String HOST = "localhost";
  private static final int PORT = 8080;

  @Test
  void getAllEntriesPerformsHttpGetWithNoParameters() throws ParserException, IOException {
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


    //HttpRequestHelper http = mock(HttpRequestHelper.class);

    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    //when(client.get(eq(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName)))).thenReturn(airlineAsText(airline));

    //assertNull(client.getAirline(airlineName));
    //assertThat(read.getFlights().iterator().next().getNumber(), equalTo(flightNumber));

  }


  private Response airlineAsText(Airline airline) {
    StringWriter writer = new StringWriter();
    new TextDumper(writer).dump(airline,null,null);

    return new Response(writer.toString());
  }

/*
  @Test
  void testGet() throws IOException {
    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    assertNull(client.get(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, "airlineName",
            AirlineServlet.FLIGHT_DETAIL_PARAMETER, "flightDetail")));
  }

  @Test
  void testPost() throws IOException {
    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    assertNull(client.post(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, "airlineName",
            AirlineServlet.FLIGHT_DETAIL_PARAMETER, "flightDetail")));
  }
*/
  @Test
  void testSendEncodedRequest() throws IOException {
    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    client.sendEncodedRequest("https://www.google.com", "POST", Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, "airlineName",
            AirlineServlet.FLIGHT_DETAIL_PARAMETER, "flightDetail"));
  }

  @Test
  void testEncodeParameters() throws IOException {
    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    client.encodeParameters(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, "airlineName",
            AirlineServlet.FLIGHT_DETAIL_PARAMETER, "flightDetail"));
  }


}
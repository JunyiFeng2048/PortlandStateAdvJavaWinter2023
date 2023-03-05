package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * A unit test for the REST client that demonstrates using mocks and
 * dependency injection
 */
public class AirlineRestClientTest {
  private static final String HOST = "localhost";
  private static final int PORT = 8080;
  /*
  @Test
  void getAllDictionaryEntriesPerformsHttpGetWithNoParameters() throws ParserException, IOException {
    String airlineName = "Airline";
    int flightNumber = 122;
    Airline airline = new Airline(airlineName);
    airline.addFlight(new Flight(flightNumber));


    //HttpRequestHelper http = mock(HttpRequestHelper.class);

    AirlineRestClient client = new AirlineRestClient(HOST, PORT);
    //when(client.get(eq(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName)))).thenReturn(airlineAsText(airline));

    Airline read = client.getAirline(airlineName);
    assertThat(read.getName(), equalTo(airlineName));
    assertThat(read.getFlights().iterator().next().getNumber(), equalTo(flightNumber));

  }

   */
/*
  private Response airlineAsText(Airline airline) {
    StringWriter writer = new StringWriter();
    new TextDumper(writer).dump(airline);

    return new Response(writer.toString());
  }

 */
}
package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class AirlineServletTest {

  @Test
  void gettingFlightsForNonExistentAirlineReturns404() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINE_NAME_PARAMETER)).thenReturn("Airline");

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
  }


  @Test
  void addFlightInNewAirline() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    String airlineName = "Airline";
    int flightNumber = 123;
    String flightNumberAsString = String.valueOf(flightNumber);
    String sourceCode = "PDX";
    String departDate = "11/11/2011";
    String departTime = "11:11";
    String departPeriod = "am";
    String destinationCode = "ABE";
    String arrivalDate = "11/11/2111";
    String arrivalTime = "11:11";
    String arrivalPeriod = "pm";
    String flightDetail = "";
    flightDetail = flightNumberAsString + " " + sourceCode + " " +
            departDate + " " + departTime + " " + departPeriod + " " + destinationCode + " " +
            arrivalDate + " " + arrivalTime + " " + arrivalPeriod;

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINE_NAME_PARAMETER)).thenReturn(airlineName);
    when(request.getParameter(AirlineServlet.FLIGHT_DETAIL_PARAMETER)).thenReturn(flightDetail);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    String xml = stringWriter.toString();
    assertThat(xml, containsString(airlineName));
    assertThat(xml, containsString(flightNumberAsString));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    Airline airline = servlet.getAirline(airlineName);
    assertThat(airline.getName(), equalTo(airlineName));

    Flight flight = airline.getFlights().iterator().next();
    assertThat(flight.getFlightNumber(), equalTo(flightNumber));
  }

  @Test
  void testMissingRequiredParameter() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    String airlineName = null;
    int flightNumber = 123;
    String flightNumberAsString = String.valueOf(flightNumber);

    String flightDetail = null;

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINE_NAME_PARAMETER)).thenReturn(airlineName);
    when(request.getParameter(AirlineServlet.FLIGHT_DETAIL_PARAMETER)).thenReturn(flightDetail);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    //String xml = stringWriter.toString();


  }


}
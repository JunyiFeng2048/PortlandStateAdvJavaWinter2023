package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Flight} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class FlightTest {

  @Test
  void NullAirlineNameThrowsNullPointerException()
  {
    Flight flight = new Flight(null, 666, "ABC", "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.getFlightName(), is(nullValue()));
  }

  @Test
  void NullSrcThrowsNullPointerException()
  {
    Flight flight = new Flight("CS410J Air Express", 666, null, "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.getSource(), is(nullValue()));
  }

  @Test
  void NullDepartTimeThrowsNullPointerException()
  {
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", null ,"EGF", "03/2/2023 1:03");
    assertThat(flight.getDepartureString(), is(nullValue()));
  }

  @Test
  void NullDestinationThrowsNullPointerException()
  {
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,null, "03/2/2023 1:03");
    assertThat(flight.getDestination(), is(nullValue()));
  }

  @Test
  void NullArriveTimeThrowsNullPointerException()
  {
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,"EGF", null);
    assertThat(flight.getArrivalString(), is(nullValue()));
  }

  @Test
  void flightNamedGoodIsNamedGood() {
    String flightName = "Good";
    Flight flight = new Flight(flightName, 666, "ABC", "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.getFlightName(), equalTo(flightName));
  }

  @Test
  void flightNumber666Is666() {
    int flightNumber = 666;
    Flight flight = new Flight("CS410J Air Express", flightNumber, "ABC", "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.getNumber(), equalTo(flightNumber));
  }

  @Test
  void invalidFlightNumberShouldNotPass1() {
    int flightNumber = -666;
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.setFlightNumber(flightNumber),  containsString("Invalid Flight Number"));
  }
    @Test
    void invalidFlightNumberShouldNotPass2() {
        int flightNumber = 9999;
        Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
        assertThat(flight.setFlightNumber(flightNumber),  containsString("Invalid Flight Number"));
    }
  @Test
  void invalidSrcAndDestCodeShouldNotPass() {
    String src = "Not src";
    Flight flight = new Flight("CS410J Air Express", 666, src, "3/15/2023 10:39" ,"EGF", "03/2/2023 1:03");
    assertThat(flight.setSource(src),  containsString("Invalid Src or Dest Code"));
  }

  @Test
  void invalidSrcAndDestCodeShouldNotPass2() {
    String dest = "Not dest";
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,dest, "03/2/2023 1:03");
    assertThat(flight.setDestination(dest),  containsString("Invalid Src or Dest Code"));
  }
  @Test
  void invalidDateAndTimeShouldNotPass1() {
    String departTime = "Not Depart Time";
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", departTime ,"EGF", "03/2/2023 1:03");
    assertThat(flight.setDepartTime(departTime),  containsString("Invalid Date and Time"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass2() {
    String departTime = "3/15/2023";
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", departTime ,"EGF", "03/2/2023 1:03");
    assertThat(flight.setArriveTime(departTime),  containsString("Invalid Date and Time"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass3() {
    String arriveTime = "Not Depart Time";
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,"EGF", arriveTime);
    assertThat(flight.setArriveTime(arriveTime),  containsString("Invalid Date and Time"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass4() {
    String arriveTime = "3/15/2023";
    Flight flight = new Flight("CS410J Air Express", 666, "ABC", "3/15/2023 10:39" ,"EGF", arriveTime);
    assertThat(flight.setDepartTime(arriveTime),  containsString("Invalid Date and Time"));
  }

}

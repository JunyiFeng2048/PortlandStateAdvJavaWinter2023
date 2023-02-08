package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Flight} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class FlightTest {

  /**
   * Tests that assign null param to a flight
   */

  /*
  @Test
  void NullSrcThrowsNullPointerException()
  {
    Flight flight = new Flight(666, null, "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.getSource(), is(nullValue()));
  }

  @Test
  void NullDepartDateThrowsNullPointerException()
  {
    Flight flight = new Flight(666, "ABC", null, "10:39", "EGF", "03/2/2023", "1:03");
    assertThat(flight.getDepartureString(), is(nullValue()));
  }

  @Test
  void NullDepartTimeThrowsNullPointerException()
  {
    Flight flight = new Flight(666, "ABC", "3/15/2023 ", null, "EGF", "03/2/2023", "1:03");
    assertThat(flight.getDestination(), is(nullValue()));
  }

  @Test
  void NullDestinationThrowsNullPointerException()
  {
    Flight flight = new Flight(666, "ABC", "3/15/2023 ", "10:39", null, "03/2/2023", "1:03");
    assertThat(flight.getDestination(), is(nullValue()));
  }

  @Test
  void NullArriveDateThrowsNullPointerException()
  {
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,"EGF", null, "1:03");
    assertThat(flight.getArrivalString(), is(nullValue()));
  }

  @Test
  void NullArriveTimeThrowsNullPointerException()
  {
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,"EGF", "03/2/2023", null);
    assertThat(flight.getArrivalString(), is(nullValue()));
  }

  */
  /**
   * Tests that assign a flight number to a flight
   */
  @Test
  void flightNumber666Is666() {
    int flightNumber = 666;
    Flight flight = new Flight(flightNumber, "ABC", "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.getNumber(), equalTo(flightNumber));
  }

  @Test
  void testCorrectSrc() {
    String src = "ABC";
    Flight flight = new Flight(666, src, "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.getSource(), equalTo(src));
  }



  /**
   * Tests that invalid flight number issues an error
   */
  @Test
  void invalidFlightNumberShouldNotPass1() {
    int flightNumber = -666;
    Flight flight = new Flight(flightNumber, "ABC", "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.setFlightNumber(flightNumber),  containsString("Invalid Flight Number"));
  }
    @Test
    void invalidFlightNumberShouldNotPass2() {
        int flightNumber = 9999;
        Flight flight = new Flight(flightNumber, "ABC", "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
        assertThat(flight.setFlightNumber(flightNumber),  containsString("Invalid Flight Number"));
    }

  /**
   * Tests that invalid src dest issues an error
   */

  @Test
  void validSrcAndDestCodeShouldPass1() {
    String src = "ABC";
    Flight flight = new Flight(666, src, "3/15/2023", "10:39" ,"DEF", "03/2/2023", "1:03");
    assertThat(flight.setSource(src),  containsString("Valid Src or Dest Code"));
  }
  @Test
  void validSrcAndDestCodeShouldPass2() {
    String dest = "ABC";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,dest, "03/2/2023", "1:03");
    assertThat(flight.setDestination(dest),  containsString("Valid Src or Dest Code"));
  }

  @Test
  void invalidSrcAndDestCodeShouldNotPass1() {
    String src = "Not src";
    Flight flight = new Flight(666, src, "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.setSource(src),  containsString("Invalid Src or Dest Code"));
  }
  @Test
  void invalidSrcAndDestCodeShouldNotPass2() {
    String dest = "Not dest";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,dest, "03/2/2023", "1:03");
    assertThat(flight.setDestination(dest),  containsString("Invalid Src or Dest Code"));
  }
  @Test
  void invalidSrcAndDestCodeShouldNotPass3() {
    String src = "A1A";
    Flight flight = new Flight(666, src, "3/15/2023", "10:39", "DEF", "03/2/2023", "1:03");
    assertThat(flight.setSource(src),  containsString("Invalid Src or Dest Code"));
  }

  @Test
  void invalidSrcAndDestCodeShouldNotPass4() {
    String dest = "A1A";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,dest, "03/2/2023", "1:03");
    assertThat(flight.setDestination(dest),  containsString("Invalid Src or Dest Code"));
  }

  /**
   * Tests that invalid date and time issues an error
   */
  @Test
  void invalidDateAndTimeShouldNotPass1() {
    String departDate = "Not Depart Date";
    Flight flight = new Flight(666, "ABC", departDate, "1:03", "EGF", "03/2/2023", "1:03");
    assertThat(flight.setDepartDate(departDate),  containsString("Invalid Date"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass2() {
    String departTime = "3/15/2023";
    Flight flight = new Flight(666, "ABC", "3/15/2023", departTime ,"EGF", "03/2/2023", "1:03");
    assertThat(flight.setArriveTime(departTime),  containsString("Invalid Time"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass3() {
    String arriveDate = "Not Arrive Date";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,"EGF", arriveDate, "11:03");
    assertThat(flight.setArriveDate(arriveDate),  containsString("Invalid Date"));
  }

  @Test
  void invalidDateAndTimeShouldNotPass4() {
    String arriveTime = "3/15/2023";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,"EGF", "3/15/2023", arriveTime);
    assertThat(flight.setDepartTime(arriveTime),  containsString("Invalid Time"));
  }

  @Test
  void testGetDepartureStringWithPM()
  {

    String DepartureDate = "3/15/2023";
    String DepartureTime = "10:49";
    Flight flight = new Flight(666, "ABC", DepartureDate, DepartureTime,"EGF", "3/15/2023", "11:03");
    flight.setDepartPeriod("pm");
    assertThat(flight.getDepartureString(), containsString("3/15/2023 10:49 PM"));
  }

  @Test
  void testGetDepartureStringWithoutPM()
  {

    String DepartureDate = "3/15/2023";
    String DepartureTime = "10:49";
    Flight flight = new Flight(666, "ABC", DepartureDate, DepartureTime,"EGF", "3/15/2023", "11:03");
    assertThat(flight.getDepartureString(), containsString("3/15/2023 10:49"));
  }

  @Test
  void testGetArrivalStringWithPM()
  {

    String ArrivalDate = "3/15/2023";
    String ArrivalTime = "11:03";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:49","EGF", ArrivalDate, ArrivalTime);
    flight.setArrivePeriod("pm");
    assertThat(flight.getArrivalString(), containsString("3/15/2023 11:03 PM"));
  }


  @Test
  void testGetArrivalWithoutPM()
  {

    String ArrivalDate = "3/15/2023";
    String ArrivalTime = "11:03";
    Flight flight = new Flight(666, "ABC", "3/15/2023", "10:49","EGF", ArrivalDate, ArrivalTime);
    assertThat(flight.getArrivalString(), containsString("3/15/2023 11:03"));
  }


}

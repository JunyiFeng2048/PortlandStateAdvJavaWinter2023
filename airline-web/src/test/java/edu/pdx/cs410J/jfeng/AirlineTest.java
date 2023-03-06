package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirlineTest {

    @Test
    void NullAirlineNameThrowsNullPointerException()
    {
        Airline airline = new Airline(null);
        assertThat(airline.getName(), is(nullValue()));
    }

    @Test
    void testAirlineNamedGoodIsNamedGood()
    {
        String name = "Good";
        Airline airline = new Airline(name);
        assertThat(airline.getName(), equalTo(name));
    }

    @Test
    void testAddFlight()
    {
        String name = "CS410J Air Express";
        Flight flight = new Flight(666, "ABC", "3/15/2023", "10:39" ,"DEF", "03/2/2023", "1:03");
        Airline airline = new Airline(name);
        airline.addFlight(flight);
        assertTrue(airline.getFlights().contains(flight));
    }

    @Test
    void testGetFlight()
    {
        String name = "CS410J Air Express";
        ArrayList<Flight> flightArrayList = new ArrayList<>();
        Flight flight1 = new Flight(111, "ABC", "3/15/2023", "10:39" ,"KLM", "03/2/2023", "1:03");
        Flight flight2 = new Flight(222, "DEF", "3/15/2023", "10:39" ,"NOP", "03/2/2023", "1:03");
        Flight flight3 = new Flight(333, "GHJ", "3/15/2023", "10:39" ,"QRST", "03/2/2023", "1:03");
        flightArrayList.add(flight1);
        flightArrayList.add(flight2);
        flightArrayList.add(flight3);

        Airline airline = new Airline(name);
        airline.addFlight(flight1);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        assertThat(airline.getFlights(),equalTo(flightArrayList));
    }

    @Test
    void testCreateAirline()
    {
        String name = "Air Canada";
        Airline airline = new Airline(name);
        assertThat(airline,is(airline));
    }
}

package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightTest {

        /**
         * Tests that assign a flight number to a flight
         */
        @Test
        void flightNumber666Is666() {
            int flightNumber = 666;
            Flight flight = new Flight(flightNumber, "ABC", "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
            assertThat(flight.getFlightNumber(), equalTo(flightNumber));
        }

        @Test
        void testCorrectSrc() {
            String src = "ABC";
            Flight flight = new Flight(666, src, "3/15/2023", "10:39" ,"EGF", "03/2/2023", "1:03");
            assertThat(flight.getSourceCode(), equalTo(src));
        }

        @Test
        void testFlight1()
        {
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
            Flight flight = new Flight();
            flight.setFlightNumber(flightNumber);
            flight.setSourceCode(sourceCode);
            flight.setDepartDate(departDate);
            flight.setDepartTime(departTime);
            flight.setDestinationCode(destinationCode);
            flight.setArrivalDate(arrivalDate);
            flight.setArrivalTime(arrivalTime);
            flight.setArrivalPeriod(arrivalPeriod);
            flight.setDepartPeriod(departPeriod);
            assertTrue(flight.getFlightNumber()==122);
            assertTrue(flight.getSourceCode().equals("PDX"));
            assertTrue(flight.getDepartDate().equals("11/11/2011"));
            assertTrue(flight.getDepartTime().equals("11:11"));
            assertTrue(flight.getDepartPeriod().equals("am"));
            assertTrue(flight.getDestinationCode().equals("ABE"));
            assertTrue(flight.getArrivalDate().equals("11/11/2111"));
            assertTrue(flight.getArrivalTime().equals("11:11"));
            assertTrue(flight.getArrivalPeriod().equals("pm"));

        }

        @Test
        void testSetDuration(){
            Flight flight = new Flight();
            flight.setDuration("20");
            assertTrue(flight.getDuration().equals("20"));

        }


}


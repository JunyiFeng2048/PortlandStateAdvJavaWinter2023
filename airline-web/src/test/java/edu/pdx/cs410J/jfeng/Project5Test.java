package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Project5Test {
    Project5 project5 = new Project5();
    @Test
    void testGetREAMDE()
    {
        project5.getREADME();
    }

    @Test
    void testAddFlightToServer()
    {
        String[] args = {"-host", "localhost", "-port", "12345",
            "AirDave", "123", "PDX", "07/19/2023", "1:02", "pm", "ORD", "07/19/2023", "6:22", "pm"};
        project5.addFlightToServer(args);
    }

    @Test
    void testPrettyPrintAirline() throws ParserException, IOException {
        String[] args = {"-host", "localhost", "-port", "4561", "AirDave"};
        assertFalse(project5.prettyPrintAirline(args));
    }

    @Test
    void testSearchFlightBetweenTwoAirports() throws ParserException, IOException {
        String[] args = {"-host", "localhost", "-port", "12345", "-search",
                "AirDave", "PDX", "ORD"};
        assertFalse(project5.searchFlightBetweenTwoAirports(args));
    }

    @Test
    void testSearchFlightBetweenTwoAirports2() throws ParserException, IOException {
        String[] args = {"-search", "-host", "localhost", "-port", "12345",
                "AirDave", "PDX", "ORD"};
        assertFalse(project5.searchFlightBetweenTwoAirports0(args));
    }

    @Test
    void testSearchFlight() throws ParserException, IOException {
        String[] args = {"-host", "localhost", "-port", "12345", "-search", "AirDave"};
        assertFalse(project5.prettyPrintAirline(args));
    }

    @Test
    void testSearchFlight2() throws ParserException, IOException {
        String[] args = {"-search", "-host", "localhost", "-port", "12345", "AirDave"};
        assertFalse(project5.prettyPrintAirline(args));
    }

    @Test
    void testPrettyPrintAndAddFlight() throws ParserException, IOException {
        String[] args = {"-host", "localhost", "-port", "12345", "-pretty",
                "AirDave", "123", "PDX", "07/19/2023", "1:02", "pm", "ORD", "07/19/2023", "6:22", "pm"};
        project5.prettyPrintAndAddFlight(args);
    }

    @Test
    void testUsage(){
        project5.usage("OK");
    }


}

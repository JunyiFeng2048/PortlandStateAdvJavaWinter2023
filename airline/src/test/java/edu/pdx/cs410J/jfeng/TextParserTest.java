package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextParserTest
{

    /***
        if the file does not exist, it should not parse any data into an airline
     ***/

    @Test
    void parseNonExistingFile() throws ParserException
    {
        String filePath = String.format("NotExist.txt");
        Airline airline1 = new Airline("Test1");
        Airline airline2 = new Airline("Test2");
        TextParser textParser = new TextParser(filePath,airline1);
        airline1 = textParser.parse();
        assertEquals(airline1.getFlights().size(), airline2.getFlights().size());
        File deleteFile = new File("NotExist.txt");
        deleteFile.delete();
    }

    @Test
    void parseAnCorrectExistingFile() throws ParserException, IOException {
        String filePath = String.format("test.txt");
        Airline airline = new Airline("Test");
        Flight flight = new Flight(666, "ABC", "4/20/2013", "11:25", "EGF", "03/2/2023", "1:03");
        flight.setDepartPeriod("am");
        flight.setArrivePeriod("pm");
        airline.addFlight(flight);
        TextDumper textDumper = new TextDumper(filePath);
        textDumper.dump(airline);
        Airline airline2 = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline2);
        airline2 = textParser.parse();
        assertEquals(airline2.getFlights().size(),1);
        File deleteFile = new File("test.txt");
        deleteFile.delete();

    }

    @Test
    void parseAnEmptyFile() throws ParserException
    {
        String filePath = String.format("emptyTestParser.txt");
        Airline airline = new Airline("Test");
        TextDumper textDumper = new TextDumper(filePath);
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertEquals(airline.getFlights().size(),0);
    }

    @Test
    void parseAnMalformedFile() throws ParserException, FileNotFoundException {
        String filePath = String.format("test.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream((filePath)));
        printWriter.println("Testing");
        printWriter.flush();
        printWriter.close();
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertNull(airline);
        File deleteFile = new File("test.txt");
        deleteFile.delete();
    }

}

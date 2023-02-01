package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextParserTest
{
    private String  directory = "./src/testData/%s";

  /*
  @Test
  void validTextFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Test Airline"));
  }

  @Test
  void invalidTextFileThrowsParserException() {
    InputStream resource = getClass().getResourceAsStream("empty-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }
*/

    /***
        if the file does not exist, it should not parse any data into an airline
     ***/
    @Test
    void parseNonExistingFile() throws ParserException
    {
        String filePath = String.format(directory,"NotExist.txt");
        Airline airline1 = new Airline("Test1");
        Airline airline2 = new Airline("Test2");
        TextParser textParser = new TextParser(filePath,airline1);
        airline1 = textParser.parse();
        assertEquals(airline1.getFlights().size(), airline2.getFlights().size());
    }

    @Test
    void parseAnCorrectExistingFile() throws ParserException
    {
        String filePath = String.format(directory,"testParser1.txt");
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertEquals(airline.getFlights().size(),3);
    }

    @Test
    void parseAnIncorrectExistingFile() throws ParserException
    {
        String filePath = String.format(directory,"testParser2.txt");
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertNull(airline);
    }

    @Test
    void parseAnEmptyFile() throws ParserException
    {
        String filePath = String.format(directory,"emptyTestParser.txt");
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertEquals(airline.getFlights().size(),0);
    }

    @Test
    void parseAnMalformedFile() throws ParserException
    {
        String filePath = String.format(directory,"malformedTestParser.txt");
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertNull(airline);
    }

    @Test
    void parseAnMalformedDateAndTimeFile() throws ParserException
    {
        String filePath = String.format(directory,"malformedTestParser.txt");
        Airline airline = new Airline("Test");
        TextParser textParser = new TextParser(filePath,airline);
        airline = textParser.parse();
        assertNull(airline);
    }

}

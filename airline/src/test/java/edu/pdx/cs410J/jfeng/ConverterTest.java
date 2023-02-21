package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConverterTest {
    @Test
    void TestConverterFileExists()
    {
        Converter converter = new Converter();
        assertFalse(converter.fileExists("test.txt"));
    }

    @Test
    void TestConvertFile() throws IOException, ParserException, ParserConfigurationException, SAXException {
        Converter converter = new Converter();
        String filePath = String.format("test.txt");
        Airline airline = new Airline("Test");
        Flight flight = new Flight(666, "ABC", "4/20/2013", "11:25", "EGF", "03/2/2023", "1:03");
        flight.setDepartPeriod("am");
        flight.setArrivePeriod("pm");
        airline.addFlight(flight);
        TextDumper textDumper = new TextDumper(filePath);
        textDumper.dump(airline);
        converter.convert(filePath,"test.xml");
        XmlParser xmlParser = new XmlParser("test.xml",airline);
        Airline airlineParsed = xmlParser.parse();
        assertThat(airline.toString(), containsString(airlineParsed.toString()));
        File deleteFile1 = new File("test.xml");
        File deleteFile2 = new File(filePath);
        deleteFile1.delete();
        deleteFile2.delete();
    }
}

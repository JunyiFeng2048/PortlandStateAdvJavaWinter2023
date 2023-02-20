package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XmlDumperTest {

    @Test
    void testXmlDumper() throws IOException, ParserConfigurationException, SAXException {
        AirlineXmlHelper helper = new AirlineXmlHelper();


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(helper);
        builder.setEntityResolver(helper);

        String name = "Air Canada";
        Flight flight1 = new Flight(666, "ABE", "03/15/2023", "10:39" ,"DEF", "03/21/2023", "1:03");
        flight1.setArrivePeriod("am");
        flight1.setDepartPeriod("am");
        Flight flight2 = new Flight(123, "ABC", "01/15/2023", "2:13" ,"DEF", "04/12/2023", "11:32");
        flight2.setArrivePeriod("pm");
        flight2.setDepartPeriod("am");
        Airline airline = new Airline(name);
        airline.addFlight(flight1);
        airline.addFlight(flight2);

        XmlDumper xmlDumper = new XmlDumper("XmlTest.xml");
        xmlDumper.dump(airline);

        File deleteFile = new File("XmlTest.xml");
        deleteFile.delete();

    }
}

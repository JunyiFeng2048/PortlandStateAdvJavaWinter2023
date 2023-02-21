package edu.pdx.cs410J.jfeng;
import javax.xml.parsers.*;

import edu.pdx.cs410J.AirlineParser;
import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlParser implements AirlineParser<Airline> {

    private String filePath;
    private Airline airline;
    public XmlParser(String filePath, Airline airline)
    {
        this.filePath = filePath;
        this.airline = airline;
    }
    @Override
    public Airline parse()
    {
        AirlineXmlHelper helper = new AirlineXmlHelper();
        File inputFile = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);


        //Document document = builder.parse(inputFile);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(helper);
            builder.setEntityResolver(helper);
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            NodeList flightList = document.getElementsByTagName("flight");
            for (int i = 0; i < flightList.getLength(); i++)
            {
                Node node = flightList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element flightElement = (Element) node;

                    Flight flight = new Flight();
                    String number = flightElement.getElementsByTagName("number").item(0).getTextContent();
                    flight.setFlightNumber(Integer.parseInt(number));

                    String src = flightElement.getElementsByTagName("src").item(0).getTextContent();
                    flight.setSource(src);

                    String dest = flightElement.getElementsByTagName("dest").item(0).getTextContent();
                    flight.setDestination(dest);

                    String departDate = flightElement.getElementsByTagName("date").item(0).getAttributes().getNamedItem("month").getNodeValue()
                            + "/" + flightElement.getElementsByTagName("date").item(0).getAttributes().getNamedItem("day").getNodeValue()
                            + "/" + flightElement.getElementsByTagName("date").item(0).getAttributes().getNamedItem("year").getNodeValue();
                    flight.setDepartDate(departDate);

                    String departHour = flightElement.getElementsByTagName("time").item(0).getAttributes().getNamedItem("hour").getNodeValue();
                    flight.setDepartPeriod("am");
                    if(Integer.parseInt(departHour) > 12)
                    {
                        int tempDepartHour = Integer.parseInt(departHour) - 12;
                        departHour = String.valueOf(tempDepartHour);
                        flight.setDepartPeriod("pm");
                    }
                    String departTime = departHour
                            + ":" + flightElement.getElementsByTagName("time").item(0).getAttributes().getNamedItem("minute").getNodeValue();
                    flight.setDepartTime(departTime);

                    String arriveDate = flightElement.getElementsByTagName("date").item(1).getAttributes().getNamedItem("month").getNodeValue()
                            + "/" + flightElement.getElementsByTagName("date").item(1).getAttributes().getNamedItem("day").getNodeValue()
                            + "/" + flightElement.getElementsByTagName("date").item(1).getAttributes().getNamedItem("year").getNodeValue();
                    flight.setArriveDate(arriveDate);

                    String arriveHour = flightElement.getElementsByTagName("time").item(1).getAttributes().getNamedItem("hour").getNodeValue();
                    flight.setArrivePeriod("am");
                    if(Integer.parseInt(arriveHour) > 12)
                    {
                        int tempArriveHour = Integer.parseInt(arriveHour) - 12;
                        arriveHour = String.valueOf(tempArriveHour);
                        flight.setArrivePeriod("pm");
                    }
                    String arriveTime = arriveHour
                            + ":" + flightElement.getElementsByTagName("time").item(1).getAttributes().getNamedItem("minute").getNodeValue();
                    flight.setArriveTime(arriveTime);
                    airline.addFlight(flight);
                }
            }
        } catch (FileNotFoundException e) {
            return airline; //if the file does not exist
        } catch (ParserConfigurationException e) {
            return airline; //if the file does not exist
        } catch (IOException e) {
            return airline; //if the file does not exist
        } catch (SAXException e) {
            return airline; //if the file does not exist
        }
        return airline;
    }

    public String getAirlineName() throws ParserConfigurationException
    {
        AirlineXmlHelper helper = new AirlineXmlHelper();
        File inputFile = new File(filePath);
        if (inputFile.exists())
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(helper);
            builder.setEntityResolver(helper);
            try {
                Document document = builder.parse(inputFile);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList airlineList = rootElement.getElementsByTagName("name");
                Element element = (Element) airlineList.item(0);
                return element.getTextContent();
            } catch (IOException e) {
                System.err.println("Error parsing " + filePath);
                e.printStackTrace();
            } catch (SAXException e) {
                System.err.println("Error parsing " + filePath);
                e.printStackTrace();
            }
        } else
        {
            return "null";
        }
        return "null";
    }
}

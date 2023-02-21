package edu.pdx.cs410J.jfeng;


import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;


import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;


public class XmlDumper implements AirlineDumper<Airline> {
    private final String DTD_URL = "http://www.cs.pdx.edu/˜whitlock/dtds/airline.dtd";
    private String filePath;

    public XmlDumper(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public void dump(Airline airline) throws IOException
    {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            XmlParser xmlParser = new XmlParser(filePath,airline);
            if(!xmlParser.getAirlineName().equals(airline.getName()) && !xmlParser.getAirlineName().equals("null"))
            {
                System.out.println("This is not the file for Airline " + airline.getName());
                return;
            }

            Element root = document.createElement("airline");
            Element nameElement = document.createElement("name");
            nameElement.setTextContent(airline.getName());
            root.appendChild(nameElement);

            ArrayList<Flight> flightArrayList = (ArrayList<Flight>) airline.getFlights();
            for (Flight flight : flightArrayList)
            {
                Element flightElement = document.createElement("flight");
                root.appendChild(flightElement);

                Element numberElement = document.createElement("number");
                numberElement.appendChild(document.createTextNode(String.valueOf(flight.getNumber())));
                flightElement.appendChild(numberElement);

                Element srcElement = document.createElement("src");
                srcElement.appendChild(document.createTextNode(flight.getSource()));
                flightElement.appendChild(srcElement);

                Element departElement = document.createElement("depart");
                flightElement.appendChild(departElement);

                String[] departDate = flight.getDepartDate().split("/");
                Element departDateElement = document.createElement("date");
                Attr departDayAttr = document.createAttribute("day");
                departDayAttr.setValue(departDate[1]);
                Attr departMonthAttr = document.createAttribute("month");
                departMonthAttr.setValue(departDate[0]);
                Attr departYearAttr = document.createAttribute("year");
                departYearAttr.setValue(departDate[2]);
                departDateElement.setAttributeNode(departDayAttr);
                departDateElement.setAttributeNode(departMonthAttr);
                departDateElement.setAttributeNode(departYearAttr);
                departElement.appendChild(departDateElement);

                String[] departTimeFull = flight.getDeparture().toString().split(" ");
                String[] departTime = departTimeFull[3].split(":");
                Element departTimeElement = document.createElement("time");
                Attr departHourAttr = document.createAttribute("hour");
                departHourAttr.setValue(departTime[0]);
                Attr departMinuteAttr = document.createAttribute("minute");
                departMinuteAttr.setValue(departTime[1]);
                departTimeElement.setAttributeNode(departHourAttr);
                departTimeElement.setAttributeNode(departMinuteAttr);
                departElement.appendChild(departTimeElement);

                Element destElement = document.createElement("dest");
                destElement.appendChild(document.createTextNode(flight.getDestination()));
                flightElement.appendChild(destElement);

                Element arriveElement = document.createElement("arrive");
                flightElement.appendChild(arriveElement);

                String[] arriveDate = flight.getArriveDate().split("/");
                Element arriveDateElement = document.createElement("date");
                Attr arriveDayAttr = document.createAttribute("day");
                arriveDayAttr.setValue(arriveDate[1]);
                Attr arriveMonthAttr = document.createAttribute("month");
                arriveMonthAttr.setValue(arriveDate[0]);
                Attr arriveYearAttr = document.createAttribute("year");
                arriveYearAttr.setValue(arriveDate[2]);
                arriveDateElement.setAttributeNode(arriveDayAttr);
                arriveDateElement.setAttributeNode(arriveMonthAttr);
                arriveDateElement.setAttributeNode(arriveYearAttr);
                arriveElement.appendChild(arriveDateElement);

                String[] arriveTimeFull = flight.getArrival().toString().split(" ");
                String[] arriveTime = arriveTimeFull[3].split(":");
                Element arriveTimeElement = document.createElement("time");
                Attr hourAttr = document.createAttribute("hour");
                hourAttr.setValue(arriveTime[0]);
                Attr minuteAttr = document.createAttribute("minute");
                minuteAttr.setValue(arriveTime[1]);
                arriveTimeElement.setAttributeNode(hourAttr);
                arriveTimeElement.setAttributeNode(minuteAttr);
                arriveElement.appendChild(arriveTimeElement);
            }

            document.appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_SYSTEM, "http://www.cs.pdx.edu/~whitlock/dtds/airline.dtd");

            DOMSource domSource = new DOMSource(document);

            PrintWriter printWriter = new PrintWriter(new FileOutputStream((filePath)));
            StreamResult streamResult = new StreamResult(printWriter);
            transformer.setOutputProperty(OutputKeys.ENCODING, "us-ascii");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);
            printWriter.close();
            System.out.println("Successfully added a flight to " + filePath);

        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

package edu.pdx.cs410J.jfeng;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class test {
    public static void main(String[] args) throws Exception {
        // Create the DOM document
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Create the airline element
        Element airline = doc.createElement("airline");
        doc.appendChild(airline);

        // Create the name element
        Element name = doc.createElement("name");
        name.setTextContent("Valid Airlines");
        airline.appendChild(name);

        // Create the flight element
        Element flight = doc.createElement("flight");
        airline.appendChild(flight);

        // Create the number element
        Element number = doc.createElement("number");
        number.setTextContent("1437");
        flight.appendChild(number);

        // Create the transformer
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        // Set the DOCTYPE declaration
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_SYSTEM, "http://www.cs.pdx.edu/~whitlock/dtds/airline.dtd");

        // Write the XML to a file
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult("airline.xml");
        transformer.transform(source, result);
    }
}

package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Converter {
    public static void main(String[] args) throws IOException, ParserException
    {
        ProjectValidation pv = new ProjectValidation();
        String textFilePath = args[0];
        String xmlFilePath = args[1];
        if (args.length == 2 && pv.isValidTextFileNameAndPath(textFilePath) && pv.isValidXmlFileNameAndPath(xmlFilePath))
        {
            FileReader file = new FileReader(textFilePath);
            BufferedReader buffer = new BufferedReader(file);
            String airlineName = buffer.readLine();

            Airline airline = new Airline(airlineName);
            TextParser textParser = new TextParser(textFilePath, airline);
            airline = textParser.parse();
            if (airline == null)
            {
                System.err.println("Error parsing airline text");
                return;
            }

            XmlDumper xmlDumper = new XmlDumper(xmlFilePath);
            xmlDumper.dump(airline);
            System.out.println("Successfully converted " + textFilePath + " to " + xmlFilePath);
        }


    }
}

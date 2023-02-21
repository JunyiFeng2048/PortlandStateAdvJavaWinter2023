package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Converter {

    public static void convert(String textFilePath, String xmlFilePath) throws IOException, ParserException
    {
        ProjectValidation pv = new ProjectValidation();
        if (pv.isValidTextFileNameAndPath(textFilePath) && pv.isValidXmlFileNameAndPath(xmlFilePath))
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
            buffer.close();
            System.out.println("Successfully converted " + textFilePath + " to " + xmlFilePath);
        }
    }

    public static boolean fileExists(String textFilePath)
    {
        File file = new File(textFilePath);
        if(!file.exists())
        {
            System.err.println(textFilePath + " does not exist");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws ParserException, IOException
    {
        String textFilePath = args[0];
        String xmlFilePath = args[1];
        if(args.length != 2)
        {
            System.err.println("Missing or too many args");
            return;
        }
        if(fileExists(textFilePath))
        {
            convert(textFilePath, xmlFilePath);
        }
    }
}
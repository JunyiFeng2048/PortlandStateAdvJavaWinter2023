package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5
{
    public static final String MISSING_ARGS = "Missing command line arguments";

    /*
    public static void main(String... args)
    {
        String hostName = null;
        String portString = null;
        String airlineName = null;
        String flightNumberAsString = null;

        for (String arg : args)
        {
            if (hostName == null)
            {
                hostName = arg;
            }
            else if ( portString == null)
            {
                portString = arg;
            }
            else if (airlineName == null)
            {
                airlineName = arg;
            }
            else if (flightNumberAsString == null)
            {
                flightNumberAsString = arg;
            }
            else
            {
                usage("Extraneous command line argument: " + arg);
            }
        }
        if (hostName == null)
        {
            usage( MISSING_ARGS );
            return;
        }
        else if ( portString == null)
        {
            usage( "Missing port" );
            return;
        }

        int port;
        try {
            port = Integer.parseInt( portString );
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        AirlineRestClient client = new AirlineRestClient(hostName, port);

        try {
            if (airlineName == null)
            {
                error("Airline name required");
            }
            else if (flightNumberAsString == null)
            {
                // Pretty Print the entire airline
                Airline airline = client.getAirline(airlineName);
                System.out.println(airline.toString());

            }
            else
            {
                client.addFlight(airlineName, flightNumberAsString);
                System.out.println("added");
            }

        } catch (IOException | ParserException ex ) {
            error("While contacting server: " + ex.getMessage());
            return;
        }

    }
    */
    private static void addFlightToServer(String[] args)
    {
        Validatior validatior = new Validatior();
        String hostCommand = null;
        String hostName = null;
        String portCommand = null;
        String portString = null;
        String airlineName = null;
        String flightNumberAsString = null;
        String sourceCode = null;
        String departDate = null;
        String departTime = null;
        String departPeriod = null;
        String destinationCode = null;
        String arrivalDate = null;
        String arrivalTime = null;
        String arrivalPeriod = null;

        for (String arg : args)
        {
            if (hostCommand == null)
                hostCommand = arg;
            else if (hostName == null)
                hostName = arg;
            else if (portCommand == null)
                portCommand = arg;
            else if (portString == null)
                portString = arg;
            else if (airlineName == null)
                airlineName = arg;
            else if (flightNumberAsString == null)
                flightNumberAsString = arg;
            else if (sourceCode == null)
                sourceCode = arg;
            else if (departDate == null)
                departDate = arg;
            else if (departTime == null)
                departTime = arg;
            else if (departPeriod == null)
                departPeriod = arg;
            else if (destinationCode == null)
                destinationCode = arg;
            else if (arrivalDate == null)
                arrivalDate = arg;
            else if (arrivalTime == null)
                arrivalTime = arg;
            else if (arrivalPeriod == null)
                arrivalPeriod = arg;
        }
        String[] infoArgs = Arrays.copyOfRange(args, 4, args.length);

        if(!validatior.validation(infoArgs) || !validatior.isValidPort(portString))
            return;

        AirlineRestClient client = new AirlineRestClient(hostName, Integer.parseInt(portString));
        try {
                client.addFlight(infoArgs);
                System.out.println("Added Flight " + flightNumberAsString + " to " + airlineName);
        } catch (IOException e ) {
            error("While contacting server: " + e.getMessage());
        }
    }

    private static void prettyPrintAirline(String[] args) throws ParserException, IOException {
        String hostCommand = null;
        String hostName = null;
        String portCommand = null;
        String portString = null;
        String airlineName = null;
        for (String arg : args) {
            if (hostCommand == null)
                hostCommand = arg;
            else if (hostName == null)
                hostName = arg;
            else if (portCommand == null)
                portCommand = arg;
            else if (portString == null)
                portString = arg;
            else if (airlineName == null)
                airlineName = arg;
        }
        AirlineRestClient client = new AirlineRestClient(hostName, Integer.parseInt(portString));
        String flights = client.getAirline(airlineName);
        String[] flightsArray = flights.split("\n");
        System.out.println("Airline: " + airlineName);
        for (String flight: flightsArray) {
            String[] detail = flight.split(" ");
            System.out.println("\tFlight Number: " + detail[1]);
            System.out.println("\tDeparture: ");
            System.out.println("\t\tSource: " + detail[2]);
            System.out.println("\t\tDepart Date: " + detail[3]);
            System.out.println("\t\tDepart Time: " + detail[4] + detail[5]);
            System.out.println("\tArrival: ");
            System.out.println("\t\tDestination: " + detail[6]);
            System.out.println("\t\tArrival Date: " + detail[7]);
            System.out.println("\t\tArrival Time: " + detail[8] + detail[9]);
            System.out.println();
        }
    }

    private static void searchFlightBetweenTwoAirports(String[] args) throws ParserException, IOException {
        String hostCommand = null;
        String hostName = null;
        String portCommand = null;
        String portString = null;
        String searchCommand = null;
        String airlineName = null;
        String sourceCode = null;
        String destinationCode = null;
        for (String arg : args) {
            if (hostCommand == null)
                hostCommand = arg;
            else if (hostName == null)
                hostName = arg;
            else if (portCommand == null)
                portCommand = arg;
            else if (portString == null)
                portString = arg;
            else if (searchCommand == null)
                searchCommand = arg;
            else if (airlineName == null)
                airlineName = arg;
            else if (sourceCode == null)
                sourceCode = arg;
            else if (destinationCode == null)
                destinationCode = arg;
        }
        AirlineRestClient client = new AirlineRestClient(hostName, Integer.parseInt(portString));
        String flights = client.getAirline(airlineName);
        String[] flightsArray = flights.split("\n");
        System.out.println("Airline: " + airlineName);
        for (String flight: flightsArray) {
            String[] detail = flight.split(" ");
            //System.out.println(detail[2] + " " + sourceCode + " "+ detail[6] + " " + destinationCode);
            if(detail[2].equals(sourceCode) && detail[6].equals(destinationCode)){
                System.out.println("\tFlight Number: " + detail[1]);
                System.out.println("\tDeparture: ");
                System.out.println("\t\tSource: " + detail[2]);
                System.out.println("\t\tDepart Date: " + detail[3]);
                System.out.println("\t\tDepart Time: " + detail[4] + detail[5]);
                System.out.println("\tArrival: ");
                System.out.println("\t\tDestination: " + detail[6]);
                System.out.println("\t\tArrival Date: " + detail[7]);
                System.out.println("\t\tArrival Time: " + detail[8] + detail[9]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws ParserException, IOException {
        if(args.length < 5)
        {
            error("Not enough arguments provided");
            return;
        }
        //String firstArg = args[0];
        switch (args.length)
        {
            case 14:
                addFlightToServer(args);
                break;
            case 8:
                if(args[4].equals("-search"))
                    searchFlightBetweenTwoAirports(args);
                break;
            case 5:
                prettyPrintAirline(args);
                break;

        }
    }
    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project5 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();
    }
}
package edu.pdx.cs410J.jfeng;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>Airline</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class AirlineServlet extends HttpServlet {
    static final String AIRLINE_NAME_PARAMETER = "airline";
    static final String FLIGHT_DETAIL_PARAMETER = "flightDetail";

    private final Map<String, Airline> airlines = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );
        String airline = getParameter(AIRLINE_NAME_PARAMETER, request );
        String src = getParameter("src", request );
        String dest = getParameter("dest", request );
        if (airline != null) {
            writeAirline(airline, src, dest, response);
        } else {
            writeAllDictionaryEntries(response);
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String airlineName = getParameter(AIRLINE_NAME_PARAMETER, request );
        if (airlineName == null)
        {
            missingRequiredParameter(response, AIRLINE_NAME_PARAMETER);
            return;
        }

        String flightDetailAsString = getParameter(FLIGHT_DETAIL_PARAMETER, request );
        if ( flightDetailAsString == null)
        {
            missingRequiredParameter( response, FLIGHT_DETAIL_PARAMETER);
            return;
        }

        Airline airline = this.airlines.get(airlineName);
        if (airline == null)
        {
            airline = new Airline(airlineName);
            this.airlines.put(airlineName, airline);
        }

        String[] flightDetail = flightDetailAsString.split(" ");
        int flightNumber = Integer.parseInt(flightDetail[0]);
        String sourceCode = flightDetail[1];
        String departDate = flightDetail[2];
        String departTime = flightDetail[3];
        String departPeriod = flightDetail[4];
        String destinationCode = flightDetail[5];
        String arrivalDate = flightDetail[6];
        String arrivalTime = flightDetail[7];
        String arrivalPeriod = flightDetail[8];

        Flight flight = new Flight(flightNumber, sourceCode, departDate, departTime, destinationCode, arrivalDate, arrivalTime);
        flight.setDepartPeriod(departPeriod);
        flight.setArrivalPeriod(arrivalPeriod);
        airline.addFlight(flight);
        System.out.println(airlineName + ": " + flightDetailAsString);
        PrintWriter pw = response.getWriter();
        pw.println(Messages.definedAirlineAs(airlineName, flightDetailAsString));
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    /*
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/plain");

        this.airlines.clear();

        PrintWriter pw = response.getWriter();
        //pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }
*/
    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName ) throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void writeAirline(String airlineName, String src, String dest, HttpServletResponse response) throws IOException
    {
        Airline airline = this.airlines.get(airlineName);

        if (airline == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            PrintWriter pw = response.getWriter();
            TextDumper dumper = new TextDumper(pw);
            dumper.dump(airline, src, dest);
            pw.flush();

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
    {
        PrintWriter pw = response.getWriter();

        response.setStatus( HttpServletResponse.SC_OK );
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;
        } else {
            return value;
        }
    }

    @VisibleForTesting
    Airline getAirline(String airlineName) {
        return this.airlines.get(airlineName);
    }
}
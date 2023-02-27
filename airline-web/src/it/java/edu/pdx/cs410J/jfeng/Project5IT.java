package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project5IT extends InvokeMainTestCase
{
    /*
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    void testRemoveAllMappings() throws IOException {
        AirlineRestClient client = new AirlineRestClient(HOSTNAME, Integer.parseInt(PORT));
        client.removeAllAirlines();
    }

    @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project5.class );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    @Test
    @Disabled
    void testEmptyServer() {
        MainMethodResult result = invokeMain( Project5.class, HOSTNAME, PORT );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatWordCount(0)));
    }

    @Test
    void testNoDefinitionsThrowsAppointmentBookRestException() {
        String word = "WORD";
        try {
            invokeMain(Project5.class, HOSTNAME, PORT, word);
            fail("Should have thrown a RestException");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    //@Disabled
    @Test
    void testAddAirline() {
        String airlineName = "Airline";
        String flightNumber = "456";

        MainMethodResult result = invokeMain( Project5.class, HOSTNAME, PORT, airlineName, flightNumber );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        String out = result.getTextWrittenToStandardOut();
        //assertThat(out, containsString(Messages.definedAirlineAs(airlineName, flightNumber)));
        assertThat(out, containsString("added"));

        result = invokeMain( Project5.class, HOSTNAME, PORT, airlineName );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        //out = result.getTextWrittenToStandardOut();
        //assertThat(out, containsString(PrettyPrinter.formatDictionaryEntry(airlineName, flightNumber)));

        result = invokeMain( Project5.class, HOSTNAME, PORT );

        assertThat(result.getTextWrittenToStandardError(), containsString("Airline name required"));

        //out = result.getTextWrittenToStandardOut();
        //assertThat(out, containsString(PrettyPrinter.formatDictionaryEntry(airlineName, flightNumber)));
    }
     */

    private static final String _HOST = "-host";
    private static final String HOST = "localhost";
    private static final String _PORT = "-port";

    private static final String PORT = "8080";

    /*
    @Test
    void testAddAirline()
    {
        String airlineName = "Airline";
        String flightNumber = "888";
        MainMethodResult result = invokeMain( Project5.class, _HOST, HOST, _PORT, PORT, airlineName, flightNumber );
        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        String out = result.getTextWrittenToStandardOut();
        //assertThat(out, containsString(Messages.definedAirlineAs(airlineName, flightNumber)));
        assertThat(out, containsString("Added Flight"));
    }
*/

}
package edu.pdx.cs410J.jfeng;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AirlineRestClient
{
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";


    private final String urlString;

    /**
     * Creates a client to the airline REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public AirlineRestClient( String hostName, int port )
    {
        urlString = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }


    public String getAirline(String airlineName) throws IOException, ParserException {
        /*
        //System.out.println(get(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName)));
        Response response = get(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName));
        //throwExceptionIfNotOkayHttpStatus(response);
        String content = response.getContent();


        TextParser parser = new TextParser(new StringReader(content));
        return parser.parse();
        */
        Response response = get(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName));
        //System.out.println(response.getContent());

        return response.getContent();

    }

    /*
    public void addFlight(String word, String definition) throws IOException {
        Response response = post(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, word,
                AirlineServlet.FLIGHT_NUMBER_PARAMETER, definition));
        //throwExceptionIfNotOkayHttpStatus(response);
    }
    */

    public void addFlight(String[] info) throws IOException
    {
        String airlineName = info[0];
        String flightDetail = "";

        for (int i = 1; i < info.length; i++)
        {
                flightDetail += info[i];
                flightDetail += " ";
        }

        Response response = post(Map.of(AirlineServlet.AIRLINE_NAME_PARAMETER, airlineName,
                AirlineServlet.FLIGHT_DETAIL_PARAMETER, flightDetail));
        //throwExceptionIfNotOkayHttpStatus(response);
    }

    public void removeAllAirlines() throws IOException {
        Response response = delete(Map.of());
        //throwExceptionIfNotOkayHttpStatus(response);
    }

    /*
    private void throwExceptionIfNotOkayHttpStatus(Response response)
    {
        int code = response.getHttpStatusCode();
        if (code != HTTP_OK) {
            String message = response.getContent();
            throw new RestException(code, message);
        }
    }
     */

    /*
    public Response get(Map<String, String> parameters) throws IOException {
        StringBuilder query = encodeParameters(parameters);
        if (query.length() > 0) {
            query.insert(0, '?');
        }
        URL url = new URL(urlString + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return new Response(conn);
    }
*/
    public Response get(Map<String, String> parameters) throws IOException {
        StringBuilder query = encodeParameters(parameters);
        if (query.length() > 0) {
            query.insert(0, '?');
        }
        URL url = new URL(urlString + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return new Response(conn);
    }
    /**
     * Performs an HTTP POST
     *
     * @param parameters The key/value parameters
     * @return A <code>Response</code> summarizing the result of the POST
     */
    /*
    public Response post(Map<String, String> parameters) throws IOException {
        return sendEncodedRequest(urlString, "POST", parameters);
    }
     */

    public Response post(Map<String, String> parameters) throws IOException {
        System.out.println(parameters);
        return sendEncodedRequest(urlString, "POST", parameters);
    }

    /**
     * Performs an HTTP DELETE
     *
     * @param parameters The key/value parameters
     * @return A <code>Response</code> summarizing the result of the POST
     */
    public Response delete(Map<String, String> parameters) throws IOException {
        return sendEncodedRequest(urlString, "DELETE", parameters);
    }

    private Response sendEncodedRequest(String urlString, String requestMethod, Map<String, String> parameters) throws IOException {
        StringBuilder data = encodeParameters(parameters);

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setDoOutput(true);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        wr.write(data.toString());
        wr.flush();

        Response response = new Response(conn);
        wr.close();

        return response;
    }

    /**
     * Encodes parameters to be sent to the server via an HTTP GET, POST, or DELETE
     * @param parameters The parameter key/value pairs
     * @return The encoded parameters
     */
    private StringBuilder encodeParameters(Map<String, String> parameters) {
        StringBuilder query = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> iter = parameters.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<String, String> pair = iter.next();
            String key = pair.getKey();
            String value = pair.getValue();
            query.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
            query.append("=");
            query.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
            if (iter.hasNext()) {
                query.append("&");
            }
        }
        return query;
    }

    /**
     * Performs an HTTP PUT on the given URL
     *
     * @param parameters key/value parameters to the put
     * @return A <code>Response</code> summarizing the result of the PUT
     */
    public Response put(Map<String, String> parameters) throws IOException {
        StringBuilder data = new StringBuilder();
        parameters.forEach((key, value) -> {
            data.append(key);
            data.append("=");
            data.append(value);
            data.append("\n");
        });

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Context-Length", String.valueOf(data.length()));

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        wr.write(data.toString());
        wr.flush();

        Response response = new Response(conn);
        wr.close();

        return response;
    }



}
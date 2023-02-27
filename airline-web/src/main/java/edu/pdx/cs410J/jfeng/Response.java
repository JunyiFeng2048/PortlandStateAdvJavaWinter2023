package edu.pdx.cs410J.jfeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private final int httpStatusCode;

    private final String content;

    private int contentLines = 0;

    public Response(HttpURLConnection conn) throws IOException {
        this.httpStatusCode = conn.getResponseCode();
        InputStream stream;
        if (this.httpStatusCode != java.net.HttpURLConnection.HTTP_OK)
            stream = conn.getErrorStream();
        else
            stream = conn.getInputStream();
        StringBuilder content = new StringBuilder();
        if (stream != null) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = rd.readLine()) != null) {
                content.append(line);
                content.append("\n");
                contentLines++;
            }
            rd.close();
        }
        this.content = content.toString().trim();
    }

    public Response(String content)
    {
        this.content = content;
        this.httpStatusCode = HttpURLConnection.HTTP_OK;
    }

    /**
     * Returns the HTTP status code of the response
     *
     * @see java.net.HttpURLConnection#getResponseCode()
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Returns the (presumably textual) response from the URL
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the number of lines in the response's content
     */
    public int getContentLines() {
        return contentLines;
    }

    public static Map<String, String> arrayToMap(String[] parameters) {
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            String key = parameters[i];
            i++;
            String value = parameters[i];
            params.put(key, value);
        }
        return params;
    }
}

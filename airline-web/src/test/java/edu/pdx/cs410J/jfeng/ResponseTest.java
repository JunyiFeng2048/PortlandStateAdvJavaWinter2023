package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseTest {

    @Test
    void TestResponse()
    {
        Response response = new Response("google.com");
    }

    @Test
    void TestGetContent()
    {
        Response response = new Response("google.com");
        assertTrue(response.getContent().equals("google.com"));
    }
}

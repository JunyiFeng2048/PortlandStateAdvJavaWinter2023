package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data
 * written to {@link System#out} and the like.
 */
class Project2Test {

    @Test
    void readmeCanBeReadAsResource() throws IOException {
        try (
                InputStream readme = Project2.class.getResourceAsStream("README.txt")
        ) {
            assertThat(readme, not(nullValue()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
            String line = reader.readLine();
            assertThat(line, containsString("This is a README file!"));
        }
    }

    @Test
    void checkIsValidDateMethod()
    {
        String date = "1/03/1998/09";
        String time = "11:03";

        Project2 project2 = new Project2();
        boolean result = project2.isValidDateAndTime(date, time);
        assertFalse(result);
    }
    @Test
    void checkIsValidDateMethod2()
    {
        String date = "11/03";
        String time = "11:03";
        Project2 project2 = new Project2();
        boolean result = project2.isValidDateAndTime(date, time);
        assertFalse(result);
    }

    @Test
    void checkIsValidDateMethod3()
    {
        String date = "not date";
        String time = "11:03";

        Project2 project2 = new Project2();
        boolean result = project2.isValidDateAndTime(date, time);
        assertFalse(result);
    }


    @Test
    void checkIsValidFlightNumberMethod1()
    {
        String flightNumber = "666";
        Project2 project2 = new Project2();
        boolean result = project2.isValidFlightNumber(flightNumber);
        assertTrue(result);
    }

    @Test
    void checkIsValidFlightNumberMethod2()
    {
        String flightNumber = "99999";
        Project2 project2 = new Project2();
        boolean result = project2.isValidFlightNumber(flightNumber);
        assertFalse(result);
    }

    @Test
    void checkIsValidFlightNumberMethod3()
    {
        String flightNumber = "-666";
        Project2 project2 = new Project2();
        boolean result = project2.isValidFlightNumber(flightNumber);
        assertFalse(result);
    }
    @Test
    void checkIsValidFlightNumberMethod4()
    {
        String flightNumber = "abc";
        Project2 project2 = new Project2();
        boolean result = project2.isValidFlightNumber(flightNumber);
        assertFalse(result);
    }

    @Test
    void checkIsValidSrcAndDestCodeMethod1()
    {
        String testSrcOrDest = "abc";
        Project2 project2 = new Project2();
        boolean result = project2.isValidSrcAndDestCode(testSrcOrDest);
        assertTrue(result);
    }

    @Test
    void checkIsValidSrcAndDestCodeMethod2()
    {
        String testSrcOrDest = "ABCD";
        Project2 project2 = new Project2();
        boolean result = project2.isValidSrcAndDestCode(testSrcOrDest);
        assertFalse(result);
    }

    @Test
    void checkGetREAMDE()
    {
        Project2 project2 = new Project2();
        assertTrue(project2.getREADME());
    }

}

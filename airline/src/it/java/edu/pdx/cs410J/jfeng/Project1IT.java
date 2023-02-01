package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An integration test for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args)
    {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
      MainMethodResult result = invokeMain();
      assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

    /**
     * Tests that invoking the main method with not enough arguments issues an error
     */
  @Test
  void testNotEnoughCommandLineArguments1()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid or missing command line argument. See '-README'"));
  }

  @Test
  void testNotEnoughCommandLineArguments2()
  {
      MainMethodResult result = invokeMain("CS410J Air Express", "666", "ABC", "3/15/2023 10:39");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid or missing command line argument. See '-README'"));
  }

    /**
     * Tests that invoking the main method with too many arguments issues an error
     */
  @Test
  void testToManyCommandLineArguments1()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023 10:39", "EFG", "03/2/2023 1:03", "Extra arg");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid or missing command line argument. See '-README'"));
  }

  @Test
  void testToManyCommandLineArguments2()
  {
      MainMethodResult result = invokeMain( "CS410J Air Express", "666", "ABC", "3/15/2023 10:39", "EFG", "03/2/2023 1:03", "Extra arg");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid or missing command line argument. See '-README'"));
  }

    @Test
    void testREADME1()
    {
        MainMethodResult result = invokeMain("-README");
        assertThat(result.getTextWrittenToStandardOut(), containsString("This is a README file!"));

    }

    @Test
    void testREADME2()
    {
        MainMethodResult result = invokeMain("-README");
        try (
                InputStream readme = Project1.class.getResourceAsStream("README.txt")
        )
        {
            assertThat(readme, not(nullValue()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
            String line = reader.readLine();
        } catch (IOException e) {
            assertThat(result.getTextWrittenToStandardError(), containsString("README.txt does not exist"));

        }
    }
    /**
     * Tests that invoking the main method with invalid flight number issues an error
     */
  @Test
  void testIsValidCommandLineFlightNumber1()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "not", "ABC", "3/15/2023", "10:39", "EFG", "03/2/2023", "1:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
  }

  @Test
  void testIsValidCommandLineFlightNumber2()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "-666", "ABC", "3/15/2023", "10:39", "EFG", "03/2/2023", "1:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
  }

    @Test
    void testIsValidCommandLineFlightNumber3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "not", "ABC", "3/15/2023", "10:39", "EFG", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    @Test
    void testIsValidCommandLineFlightNumber4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "-666", "ABC", "3/15/2023", "10:39", "EFG", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    /**
     * Tests that invoking the main method with invalid date and time issues an error
     */
  @Test
  void testIsValidCommandLineDateAndTime1()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "not date", "10:39", "EFG", "not time", "11:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date and Time"));
  }
  @Test
  void testIsValidCommandLineDateAndTime2()
  {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023", "not time", "EFG", "03/2/2023", "not time");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date and Time"));
  }

    @Test
    void testIsValidCommandLineDateAndTime3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "ABC", "101/04/20/1", "11:03", "EFG", "3/13/2021", "2:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date and Time"));
    }
    @Test
    void testIsValidCommandLineDateAndTime4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "ABC", "3/15/2023", "23", "EFG", "03/2/2023", "11:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date and Time"));
    }

    /**
     * Tests that invoking the main method with invalid src and dest issues an error
     */
  @Test
  void testIsValidCommandLineSrcAndDestCode1()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABCE", "3/15/2023", "10:39", "12G", "03/2/2023", "1:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
  }

  @Test
  void testIsValidCommandLineSrcAndDestCode2()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "123", "3/15/2023", "10:39", "12G", "03/2/2023", "1:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
  }

    @Test
    void testIsValidCommandLineSrcAndDestCode3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEFG", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

    @Test
    void testIsValidCommandLineSrcAndDestCode4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "123", "3/15/2023", "10:39", "DEF", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

    @Test
    void testAddFlight()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to " + "CS410J Air Express"));
    }

    @Test
    void testPrintFlight()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/2/2023", "1:03");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Flight"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("departs"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("at"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("arrives"));

    }

    @Test
    void testInvalidFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT.pdf", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/22/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid file name, must just the file name and end with '.txt'"));
    }

    @Test
    void testValidTextFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT.txt", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/22/2023", "1:03");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT.txt"));
        File deleteFile = new File("./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/IT.txt");
        deleteFile.delete();
    }
    @Test
    void testErrorParsing()
    {
        MainMethodResult result = invokeMain("-textFile", "./testFile/IT.txt", "Test", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/22/2023", "1:03");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid file name, must just the file name and end with '.txt'"));
    }

    @Test
    void testPrintAndTextFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT.txt", "-print", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/22/2023", "1:03");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT.txt"));
        File deleteFile = new File("./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/IT.txt");
        deleteFile.delete();
    }

    @Test
    void testTextFileAndPrintCommand()
    {
        MainMethodResult result = invokeMain("-print","-textFile", "IT.txt", "CS410J Air Express", "666", "ABC", "3/15/2023", "10:39", "DEF", "03/22/2023", "1:03");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT.txt"));
        File deleteFile = new File("./src/main/java/edu/pdx/cs410J/jfeng/AirlineData/IT.txt");
        deleteFile.delete();
    }
}
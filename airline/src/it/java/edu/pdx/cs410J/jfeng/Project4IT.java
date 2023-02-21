package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * An integration test for the {@link Project4} main class.
 */
class Project4IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project4} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args)
    {
        return invokeMain( Project4.class, args );
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
    /*
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
*/
    /**
     * Tests that invoking the main method with too many arguments issues an error
     */
    /*
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
*/
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
                InputStream readme = Project4.class.getResourceAsStream("README.txt")
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
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "not", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    @Test
    void testIsValidCommandLineFlightNumber2()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "-666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    @Test
    void testIsValidCommandLineFlightNumber3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "not", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    @Test
    void testIsValidCommandLineFlightNumber4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "-666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Flight Number"));
    }

    /**
     * Tests that invoking the main method with invalid date and time issues an error
     */
    @Test
    void testIsValidCommandLineDateAndTime1()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "PDX", "not date", "10:39", "am", "PDX", "not time", "11:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date or Time"));
    }
    @Test
    void testIsValidCommandLineDateAndTime2()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "PDX", "3/15/2023", "not time", "am", "PDX", "03/2/2023", "not time", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date or Time"));
    }

    @Test
    void testIsValidCommandLineDateAndTime3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "PDX", "101/04/20/1", "11:03", "am", "PDX", "3/13/2021", "2:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date or Time"));
    }
    @Test
    void testIsValidCommandLineDateAndTime4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "PDX", "3/15/2023", "23", "am", "PDX", "03/2/2023", "11:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date or Time"));
    }

    @Test
    void testIsValidCommandLineDuration()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "PDX", "03/15/2023", "11:23", "am", "PDX", "03/15/2023", "1:22", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date or Time"));
    }

    /**
     * Tests that invoking the main method with invalid src and dest issues an error
     */
    @Test
    void testIsValidCommandLineSrcAndDestCode1()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABCE", "3/15/2023", "10:39", "am", "12G", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

    @Test
    void testIsValidCommandLineSrcAndDestCode2()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "123", "3/15/2023", "10:39", "am", "12G", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

    @Test
    void testIsValidCommandLineSrcAndDestCode3()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "PDX", "3/15/2023", "10:39", "am", "DEFG", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

    @Test
    void testIsValidCommandLineSrcAndDestCode4()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "123", "3/15/2023", "10:39", "am", "PDX", "03/2/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Src or Dest Code"));
    }

/*
    @Test
    void testAddFlight()
    {
        MainMethodResult result = invokeMain("CS410J Air Express", "666", "PDX", "03/15/2023", "10:39", "am", "PDX", "03/15/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to " + "CS410J Air Express"));
    }
*/
    @Test
    void testPrintFlight()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/21/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString(" Flight"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("departs"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("at"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("arrives"));
    }

    @Test
    void testInvalidFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT.pdf", "CS410J Air Express", "666", "PDX", "3/15/2023", "10:39","am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid file name, must end with '.txt'"));
    }




    @Test
    void testValidTextFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT.txt", "CS410J Air Express", "666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT.txt"));

        File deleteFile = new File("IT.txt");
        deleteFile.delete();
    }


    @Test
    void testErrorParsing()
    {
        MainMethodResult result = invokeMain("-textFile", "./NotDir/IT.txt", "Test", "666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Not a directory"));
    }


    @Test
    void testCreateTextFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT2.txt", "CS410J Air Express", "666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT2.txt"));
        File deleteFile = new File("IT2.txt");
        deleteFile.delete();
    }




    @Test
    void testPrintAndTextFileCommand()
    {
        MainMethodResult result = invokeMain("-textFile", "IT2.txt", "-print", "CS410J Air Express", "666", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT2.txt"));
        File deleteFile = new File("IT2.txt");
        deleteFile.delete();
    }


    @Test
    void testTextFileAndPrintCommand()
    {
        MainMethodResult result = invokeMain("-print","-textFile", "IT2.txt", "CS410J Air Express", "888", "PDX", "3/15/2023", "10:39", "am", "PDX", "03/22/2023", "1:03", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight to IT2.txt"));
        File deleteFile = new File("IT2.txt");
        deleteFile.delete();
    }


    @Test
    void testPrettyPrinter()
    {
        MainMethodResult result = invokeMain("-pretty", "prettyTest.txt",  "Air Canada", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/15/2023", "10:49", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight"));
        File deleteFile = new File("prettyTest.txt");
        deleteFile.delete();
    }

    @Test
    void testPrintPrettyTextFile()
    {
        MainMethodResult result = invokeMain("-print","-pretty", "textFile", "test.txt",  "Air Canada", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight"));
        File deleteFile = new File("test.txt");
        deleteFile.delete();
    }

    @Test
    void testPrettyPrintTextFile()
    {
        MainMethodResult result = invokeMain("-pretty","-print", "textFile", "test.txt",  "Air Canada", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight"));
        File deleteFile = new File("test.txt");
        deleteFile.delete();
    }

    @Test
    void testXmlFile()
    {
        MainMethodResult result = invokeMain("-xmlFile","test.xml",  "Air Canada", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Successfully added a flight"));
        File deleteFile = new File("test.xml");
        deleteFile.delete();
    }
}

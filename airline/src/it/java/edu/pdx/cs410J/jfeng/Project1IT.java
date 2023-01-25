package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
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

  @Test
  void testNotEnoughCommandLineArguments()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023 10:39");
      assertThat(result.getTextWrittenToStandardError(), containsString("Not enough arguments"));
  }

    @Test
    void testToManyCommandLineArguments()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "3/15/2023 10:39", "EFG", "03/2/2023 1:03", "Extra arg");
        assertThat(result.getTextWrittenToStandardError(), containsString("Too many command line arguments"));
    }
  @Test
  void testIsValidFlightNumber()
  {
      MainMethodResult result = invokeMain("-print", "CS410J Air Express", "not", "ABC", "3/15/2023 10:39", "EFG", "03/2/2023 1:03");
      assertThat(result.getTextWrittenToStandardError(), containsString("Invalid FlightNumber"));
  }

    @Test
    void testIsValidDataAndTime()
    {
        MainMethodResult result = invokeMain("-print", "CS410J Air Express", "666", "ABC", "not date", "EFG", "not time");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date and Time"));
    }

}
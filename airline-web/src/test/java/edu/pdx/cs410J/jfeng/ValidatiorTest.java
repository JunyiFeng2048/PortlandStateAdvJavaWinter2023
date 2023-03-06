package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatiorTest {
    Validatior validatior = new Validatior();

    @Test
    void TestValidation1()
    {
        String[] array = {"airline", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm"};
        assertTrue(validatior.validation(array));
    }

    @Test
    void testIsValidDateAndTime()
    {
        assertTrue(validatior.isValidDateAndTime("11/11/2011 10:36 am"));
    }

    @Test
    void testIsInvalidDateAndTime()
    {
        assertFalse(validatior.isValidDateAndTime("10/90/11/2011 10:36 am"));
    }

    @Test
    void TestValidation2()
    {
        String[] array = {"airline", "66A", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm"};
        assertFalse(validatior.validation(array));
    }

    @Test
    void TestValidation3()
    {
        String[] array = {"airline", "666", "ABC", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm"};
        assertFalse(validatior.validation(array));
    }

    @Test
    void TestValidation4()
    {
        String[] array = {"airline", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "Om"};
        assertFalse(validatior.validation(array));
    }

    @Test
    void TestValidation5()
    {
        String[] array = {"airline", "ABC", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm"};
        assertFalse(validatior.validation(array));
    }

    @Test
    void TestValidation6()
    {
        String[] array = {"airline", "666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/11/2023", "10:49", "pm"};
        assertFalse(validatior.validation(array));
    }

    @Test
    void TestIsValidPort()
    {
        assertFalse(validatior.isValidPort("port"));
    }
}

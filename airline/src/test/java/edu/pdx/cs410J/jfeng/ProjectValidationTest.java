package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectValidationTest
{
    @Test
    void TestValidation()
    {
        ProjectValidation projectValidation = new ProjectValidation();
        String[] array = {"666", "PDX", "3/15/2023", "10:49", "am", "PDX", "3/16/2023", "10:49", "pm"};
        assertTrue(projectValidation.validation(array));
    }
}

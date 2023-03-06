package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessagesTest {
    Messages messages = new Messages();
    @Test
    void testMissingRequiredParameter()
    {
        String mes = messages.missingRequiredParameter("parameterName");
        assertThat(mes,containsString("parameterName"));
    }

    @Test
    void testDefinedAirlineAs()
    {
        String mes = messages.definedAirlineAs("airline","air canada");
        assertThat(mes,containsString("Defined airline as air canada"));
    }


}

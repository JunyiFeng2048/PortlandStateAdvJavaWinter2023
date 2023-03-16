package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {

    @Override
    public int getNumber() {
        return 42;
    }

    @Override
    public String getSource() {
        return "PDX";
    }

    @Override
    public String getDepartureString() {
        return "3/10/2022 10:10 am";
    }

    @Override
    public String getDestination() {
        return "LAS";
    }

    @Override
    public String getArrivalString() {
        return "3/10/2022 11:15 pm";
    }
}

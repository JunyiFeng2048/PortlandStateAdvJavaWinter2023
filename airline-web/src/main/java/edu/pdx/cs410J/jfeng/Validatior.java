package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AirportNames;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Validatior {
    public Validatior() {
    }

    public Boolean isValidPort(String portString)
    {
        try {
            Integer.parseInt(portString);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid port");
            return false;
        }
        return true;
    }

    public Boolean isValidFlightNumber(String flightNumberAsString)
    {
        try {
            Integer.parseInt(flightNumberAsString);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid flight number");

            return false;
        }
        return true;
    }

    public boolean isValidSrcAndDestCode(String str)
    {
        if(str.length() != 3)
        {
            System.err.println("Invalid source or destination");
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if ((Character.isLetter(str.charAt(i)) == false))
            {
                System.err.println("Invalid source or destination");

                return false;
            }
        }
        AirportNames airportNames = null;
        Map<String,String > namesMap = airportNames.getNamesMap();
        if(namesMap.containsKey(str) || namesMap.containsKey(str.toUpperCase()))
        {
            return true;
        }
        System.err.println("Invalid source or destination");
        return false;
    }
    public boolean isValidDateAndTime(String dateAndTime)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        try {
            sdf.parse(dateAndTime);
            sdf.setLenient(false);
        } catch (ParseException e) {
            System.err.println("Invalid Date or Time");
            return false;
        }
        return true;
    }

    public boolean isValidDuration(String departure, String arrival)
    {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date date1 = sdf.parse(departure);
            Date date2 = sdf.parse(arrival);
            long time_difference = date2.getTime() - date1.getTime();
            if(time_difference < 0)
            {
                System.err.println("Invalid Date or Time");
                return false;
            }
        }
        catch (ParseException e) {
            System.err.println("Invalid Date or Time");
            return false;
        }
        return true;
    }

    public boolean validation(String[] argArray)
    {
        String departure = argArray[3] + " " + argArray[4] + " " + argArray[5];
        String arrival = argArray[7] + " " + argArray[8] + " " + argArray[9];
        if (isValidFlightNumber(argArray[1]) && isValidDateAndTime(departure)
                && isValidDateAndTime(arrival) && isValidSrcAndDestCode(argArray[2])
                && isValidSrcAndDestCode(argArray[6]) && isValidDuration(departure,arrival))
        {
            return true;
        }
        return false;
    }

}

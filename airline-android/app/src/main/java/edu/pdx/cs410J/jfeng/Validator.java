package edu.pdx.cs410J.jfeng;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import edu.pdx.cs410J.AirportNames;

public class Validator {

    static final String FLIGHT_NUMBER_ERROR = "Invalid Src or Dest Code";
    static final String SRC_DEST_ERROR = "Invalid Src or Dest Code";
    static final String DEPARTURE_ARRIVAL_ERROR = "Invalid Date or Time";
    static final String VALID = "valid";


    public Validator() {
    }

    public boolean isValidSrcAndDestCode(String str)
    {
        if(str.length() != 3)
        {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if ((Character.isLetter(str.charAt(i)) == false))
            {
                return false;
            }
        }
        AirportNames airportNames = null;
        Map<String,String > namesMap = airportNames.getNamesMap();
        if(namesMap.containsKey(str) || namesMap.containsKey(str.toUpperCase()))
        {
            return true;
        }
        return false;
    }

    public boolean isValidFlightNumber(String flightNumber)
    {
        try
        {
            int fn = Integer.parseInt(flightNumber);
            if(fn < 0 || fn > 9999)
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public boolean isValidDateAndTime(String[] dateAndTime)
    {
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        boolean valid;
        try {
            sdf.parse(dateAndTime);
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e) {
            System.err.println("Invalid Date or Time");
            valid = false;
        }
        return valid;

         */
        String formattedDateAndTime = dateAndTime[0] + "/" + dateAndTime[1] + "/" + dateAndTime[2] + " " +
                dateAndTime[3] + ":" + dateAndTime[4] + dateAndTime[5];
        System.out.println(formattedDateAndTime);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        try {
            sdf.parse(formattedDateAndTime);
            sdf.setLenient(false);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String validation(String flightNumber, String src, String dest, String[] departureArray, String[] arrivalArray)
    {
        if(!isValidFlightNumber(flightNumber))
            return FLIGHT_NUMBER_ERROR;
        else if(!isValidSrcAndDestCode(src))
            return SRC_DEST_ERROR;
        else if(!isValidSrcAndDestCode(dest))
            return SRC_DEST_ERROR;
        else if(!isValidDateAndTime(departureArray))
            return DEPARTURE_ARRIVAL_ERROR;
        else if(!isValidDateAndTime(arrivalArray))
            return DEPARTURE_ARRIVAL_ERROR;

        return VALID;
    }
}

package edu.pdx.cs410J.jfeng;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import edu.pdx.cs410J.AirportNames;

public class Validator {

    static final String AIRLINE_NAME_ERROR = "Invalid Airline Name";
    static final String FLIGHT_NUMBER_ERROR = "Invalid Flight Number";
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
    public boolean isValidAirlineName(String airlineName)
    {
        if(airlineName == null || airlineName.equals(""))
            return false;
        return true;
    }


    public boolean isValidDateAndTime(String[] dateAndTime)
    {
        if(Integer.parseInt(dateAndTime[0]) <= 0 || Integer.parseInt(dateAndTime[0]) > 12)
            return false;
        else if(Integer.parseInt(dateAndTime[1]) <= 0 || Integer.parseInt(dateAndTime[1]) > 31)
            return false;
        else if(Integer.parseInt(dateAndTime[3]) < 0 || Integer.parseInt(dateAndTime[3]) > 12)
            return false;
        else if(Integer.parseInt(dateAndTime[4]) < 0 || Integer.parseInt(dateAndTime[4]) > 59)
            return false;

        String formattedDateAndTime = dateAndTime[0] + "/" + dateAndTime[1] + "/" + dateAndTime[2] + " " +
                dateAndTime[3] + ":" + dateAndTime[4] + " " + dateAndTime[5];
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

    public boolean isValidDuration(String[] departureArray, String[] arrivalArray)
    {
        String departure = departureArray[0] + "/" + departureArray[1] + "/" + departureArray[2] + " " +
                departureArray[3] + ":" + departureArray[4] + " " + departureArray[5];
        String arrival = arrivalArray[0] + "/" + arrivalArray[1] + "/" + arrivalArray[2] + " " +
                arrivalArray[3] + ":" + arrivalArray[4] + " " + arrivalArray[5];
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

    public String validationAddFlight(String airlineNameString, String flightNumber, String src, String dest, String[] departureArray, String[] arrivalArray)
    {
        if(!isValidAirlineName(airlineNameString))
            return AIRLINE_NAME_ERROR;
        else if(!isValidFlightNumber(flightNumber))
            return FLIGHT_NUMBER_ERROR;
        else if(!isValidSrcAndDestCode(src))
            return SRC_DEST_ERROR;
        else if(!isValidSrcAndDestCode(dest))
            return SRC_DEST_ERROR;
        else if(!isValidDateAndTime(departureArray))
            return DEPARTURE_ARRIVAL_ERROR;
        else if(!isValidDateAndTime(arrivalArray))
            return DEPARTURE_ARRIVAL_ERROR;
        else if(!isValidDuration(departureArray, arrivalArray))
            return DEPARTURE_ARRIVAL_ERROR;

        return VALID;
    }

    public String validationSearchFlight(String airlineNameString, String src, String dest)
    {
        if(!isValidAirlineName(airlineNameString))
            return AIRLINE_NAME_ERROR;
        else if(!isValidSrcAndDestCode(src))
            return SRC_DEST_ERROR;
        else if(!isValidSrcAndDestCode(dest))
            return SRC_DEST_ERROR;

        return VALID;
    }
}

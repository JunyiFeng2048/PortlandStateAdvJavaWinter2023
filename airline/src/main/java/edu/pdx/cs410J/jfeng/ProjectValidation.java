package edu.pdx.cs410J.jfeng;

import edu.pdx.cs410J.AirportNames;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ProjectValidation {
    static boolean isValidDateAndTime(String dateAndTime)
    {
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
    }

    static boolean isValidFlightNumber(String flightNumber)
    {
        try
        {
            int fn = Integer.parseInt(flightNumber);
            if(fn < 0 || fn > 9999)
            {
                System.err.println("Invalid Flight Number");
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            System.err.println("Invalid Flight Number");
            return false;
        }
        return true;
    }

    static boolean isValidSrcAndDestCode(String str)
    {
        if(str.length() != 3)
        {
            System.err.println("Invalid Src or Dest Code");
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if ((Character.isLetter(str.charAt(i)) == false))
            {
                System.err.println("Invalid Src or Dest Code");
                return false;
            }
        }
        AirportNames airportNames = null;
        Map<String,String > namesMap = airportNames.getNamesMap();
        if(namesMap.containsKey(str) || namesMap.containsKey(str.toUpperCase()))
        {
            return true;
        }
        System.err.println("Invalid Src or Dest Code");
        return false;
    }

    static boolean isValidTextFileNameAndPath(String filePath)
    {
        String fileType = ".txt";
        if(filePath.length() < 4 || !fileType.equals(filePath.substring(filePath.length() - 4)))
        {
            System.err.println("Invalid file name, must end with '.txt'");
            return false;
        }
        String[] directory = filePath.split("/");
        String dir = "";
        for(int i=0; i<directory.length-1; i++)
        {
            dir =  dir + directory[i] + "/";
        }
        Path path = Paths.get(dir);
        if(!Files.isDirectory(path))
        {
            System.out.println(path);
            System.err.println("Not a directory");
            return false;
        }
        return true;
    }

    static boolean isValidXmlFileNameAndPath(String filePath)
    {
        String fileType = ".xml";
        if(filePath.length() < 4 || !fileType.equals(filePath.substring(filePath.length() - 4)))
        {
            System.err.println("Invalid file name, must end with '.xml'");
            return false;
        }
        String[] directory = filePath.split("/");
        String dir = "";
        for(int i=0; i<directory.length-1; i++)
        {
            dir =  dir + directory[i] + "/";
        }
        Path path = Paths.get(dir);
        if(!Files.isDirectory(path))
        {
            System.out.println(path);
            System.err.println("Not a directory");
            return false;
        }
        return true;
    }

    static boolean isValidDuration(String departure, String arrival)
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

    static boolean validation(String[] argArray)
    {
        String departure = argArray[2] + " " + argArray[3] + " " + argArray[4];
        String arrival = argArray[6] + " " + argArray[7] + " " + argArray[8];
        if (isValidFlightNumber(argArray[0]) && isValidDateAndTime(departure)
                && isValidDateAndTime(arrival) && isValidSrcAndDestCode(argArray[1])
                && isValidSrcAndDestCode(argArray[5]) && isValidDuration(departure,arrival))
        {
            return true;
        }
        return false;
    }
}

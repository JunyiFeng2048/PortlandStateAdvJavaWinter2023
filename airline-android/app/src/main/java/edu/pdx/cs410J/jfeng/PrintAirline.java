package edu.pdx.cs410J.jfeng;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintAirline extends AppCompatActivity {
    private ArrayAdapter<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_airline);
    }

    public void print(View view){
        ListView listOfFlights = findViewById(R.id.flights);
        EditText airlineName = findViewById(R.id.airline_name_input);
        String airlineNameString = airlineName.getText().toString();
        Airline airline = new Airline(airlineNameString);
        readAirlineFile(airline);
        airline.sort();
        flights = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,(List)airline.getFlights());
        listOfFlights.setAdapter(flights);
    }

    @Nullable
    private File getFilePath(String fileName){
        File dataDir = this.getDataDir();
        return new File(dataDir,fileName+".txt");
    }

    private void readAirlineFile(Airline airline) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilePath(airline.getName())))){
            for(String line = bufferedReader.readLine(); line!=null; line=bufferedReader.readLine()){
                Flight flight = new Flight();
                String[] flightDetail = line.split(" ");
                flight.setFlightNumber(Integer.parseInt(flightDetail[0]));
                flight.setSourceCode(flightDetail[1]);
                flight.setDepartDate(flightDetail[2]);
                flight.setDepartTime(flightDetail[3]);
                flight.setDepartPeriod(flightDetail[4]);
                flight.setDestinationCode(flightDetail[5]);
                flight.setArrivalDate(flightDetail[6]);
                flight.setArrivalTime(flightDetail[7]);
                flight.setArrivalPeriod(flightDetail[8]);
                airline.addFlight(flight);
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            Toast.makeText(this, "Airline does not exist", Toast.LENGTH_SHORT).show();
        }
    }



    public void returnToMain(View view){
        finish();
    }
}
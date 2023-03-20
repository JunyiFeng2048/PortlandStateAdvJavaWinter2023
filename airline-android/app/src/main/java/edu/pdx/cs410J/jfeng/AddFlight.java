package edu.pdx.cs410J.jfeng;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TimePicker;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddFlight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
    }

    public void returnToMain(View view){
        finish();
    }

    @Nullable
    private File getFilePath(String fileName){
        File dataDir = this.getDataDir();
        return new File(dataDir,fileName+".txt");
    }

    public void addFlight(View view) {
        EditText airlineName = findViewById(R.id.airline_name_input);
        EditText flightNumber = findViewById(R.id.flight_number_input);
        EditText src = findViewById(R.id.source_code_input);
        EditText departureMonth = findViewById(R.id.departure_date_month);
        EditText departureDay = findViewById(R.id.departure_date_day);
        EditText departureYear = findViewById(R.id.departure_date_year);
        EditText departureHour = findViewById(R.id.departure_time_hour);
        EditText departureMinute = findViewById(R.id.departure_time_minute);
        EditText departurePeriod = findViewById(R.id.departure_time_period);
        EditText dest = findViewById(R.id.destination_code_input);
        EditText arrivalMonth = findViewById(R.id.arrival_date_month);
        EditText arrivalDay = findViewById(R.id.arrival_date_day);
        EditText arrivalYear = findViewById(R.id.arrival_date_year);
        EditText arrivalHour = findViewById(R.id.arrival_time_hour);
        EditText arrivalMinute = findViewById(R.id.arrival_time_minute);
        EditText arrivalPeriod = findViewById(R.id.arrival_time_period);

        String airlineNameString = airlineName.getText().toString();
        String flightNumberString = flightNumber.getText().toString();
        String srcString = src.getText().toString();
        String destString = dest.getText().toString();
        String departString = departureMonth.getText().toString() + "/" + departureDay.getText().toString() + "/" + departureYear.getText().toString() + " " +
                departureHour.getText().toString() + ":" + departureMinute.getText().toString() + " " + departurePeriod.getText().toString();
        String arrivalString = arrivalMonth.getText().toString() + "/" + arrivalDay.getText().toString() + "/" + arrivalYear.getText().toString() + " " +
                arrivalHour.getText().toString() + ":" + arrivalMinute.getText().toString() + " " + arrivalPeriod.getText().toString();
        String[] departStringArray = {departureMonth.getText().toString(),departureDay.getText().toString(),departureYear.getText().toString(),
                departureHour.getText().toString(),departureMinute.getText().toString(),departurePeriod.getText().toString()};
        String[] arrivalStringArray = {arrivalMonth.getText().toString(),arrivalDay.getText().toString(),arrivalYear.getText().toString(),
                arrivalHour.getText().toString(),arrivalMinute.getText().toString(),arrivalPeriod.getText().toString()};

        Validator validator = new Validator();
        String validationResult = validator.validation(airlineNameString,flightNumberString,srcString,destString,departStringArray,arrivalStringArray);
        if(!validationResult.equals("valid")){
            Toast.makeText(this, validationResult, Toast.LENGTH_SHORT).show();
            return;
        }

        File filePath = getFilePath(airlineNameString);

        String airlineString = flightNumberString + " " + srcString + " "
                + departString + " " + destString + " " + arrivalString;

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(filePath,true))){
            printWriter.println(airlineString);
        } catch (IOException e) {
            Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
        }

        /*
        int leftOperand;
        try {
            leftOperand = Integer.parseInt(leftOperandString);

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid number: " + leftOperandString, Toast.LENGTH_SHORT).show();
            return;
        }

        int rightOperand;
        try {
            rightOperand = Integer.parseInt(rightOperandString);

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid number: " + rightOperandString, Toast.LENGTH_SHORT).show();
            return;
        }

        sum = leftOperand + rightOperand;

        TextView sumEditText = findViewById(R.id.sum);
        sumEditText.setText(String.valueOf(sum));

         */
    }
}
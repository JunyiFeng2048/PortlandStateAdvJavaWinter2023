package edu.pdx.cs410J.jfeng;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TimePicker;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFlight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
    }

    public void returnToMain(View view){
        finish();
    }

    public void addFlight(View view) {
        EditText airlineName = findViewById(R.id.airline_name_input);
        EditText flightNumber = findViewById(R.id.flight_number_input);
        EditText src = findViewById(R.id.source_code_input);
        //EditText depart = findViewById(R.id.departure_date_time_input);
        EditText dest = findViewById(R.id.destination_code_input);
        //EditText arrive = findViewById(R.id.arrival_date_time_input);


        String airlineNameString = airlineName.getText().toString();
        String flightNumberString = flightNumber.getText().toString();
        String srcString = src.getText().toString();
        //String departString = depart.getText().toString();
        String destString = dest.getText().toString();
        //String arriveString = arrive.getText().toString();


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
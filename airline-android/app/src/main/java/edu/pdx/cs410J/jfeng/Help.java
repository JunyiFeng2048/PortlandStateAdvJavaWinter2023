package edu.pdx.cs410J.jfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView README = findViewById(R.id.README);

        String READMEString = "usage: java -jar target/airline-client.jar [options] <args>\n" +
                "args are (in this order):\n" +
                "airline The name of the airline\n" +
                "flightNumber The flight number\n" +
                "src Three-letter code of departure airport\n" +
                "depart Departure date/time\n" +
                "dest Three-letter code of arrival airport\n" +
                "arrive Arrival date/time\n" +
                "options are (options may appear in any order):\n";

        README.setText(READMEString);
    }

    public void returnToMain(View view){
        finish();
    }

    public void printREADME(View view) {



        /*
        EditText leftOperandEditText = findViewById(R.id.leftOperand);
        EditText rightOperandEditText = findViewById(R.id.rightOperand);

        String leftOperandString = leftOperandEditText.getText().toString();
        String rightOperandString = rightOperandEditText.getText().toString();

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
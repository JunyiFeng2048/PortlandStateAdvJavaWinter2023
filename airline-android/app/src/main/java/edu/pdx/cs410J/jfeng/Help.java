package edu.pdx.cs410J.jfeng;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView README = findViewById(R.id.README);
        //Toast.makeText(this, this.getDataDir().toString(), Toast.LENGTH_SHORT).show();

        String READMEString = "This application is able to create an airline, entering flights, pretty printing an airline and its flights, searching for flights\n" +
                "Usage: \n" +
                "-PRINT AIRLINE\n" +
                "\t\tEnter an airline to print its flights\n" +
                "-ADD FLIGHT\n" +
                "\t\tEnter info for an airline and add it to the local storage\n" +
                "-SEARCH\n" +
                "\t\tEnter the airline name, src and dest and return the corresponding flight\n";

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
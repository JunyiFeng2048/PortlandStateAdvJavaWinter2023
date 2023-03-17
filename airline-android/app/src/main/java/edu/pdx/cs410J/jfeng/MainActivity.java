package edu.pdx.cs410J.jfeng;

import static edu.pdx.cs410J.jfeng.CalculatorActivity.SUM_VALUE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.ControlAction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int GET_SUM = 42;
    private ArrayAdapter<Integer> sums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> sumsFromFile = null;
        /*
        ListView listOfSums = findViewById(R.id.sums);
        try {
            sumsFromFile = readSumsFromFile();
        } catch (IOException e) {
            Toast.makeText(this, "While reading file: " + e, Toast.LENGTH_SHORT).show();

        }
        sums = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sumsFromFile);
        listOfSums.setAdapter(sums);

         */
    }

    /*
    private List<Integer> readSumsFromFile() throws IOException {
        List<Integer> sums = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getSumsFile()))){
            for(String line = bufferedReader.readLine(); line!=null; line=bufferedReader.readLine()){
                sums.add(Integer.parseInt(line));
            }
        }
        return sums;
    }

    public void launchCalculator(View view) {
        //light flight = new Flight();
        //Toast.makeText(this, flight.toString(), Toast.LENGTH_LONG).show();
        startActivityForResult(new Intent(this, CalculatorActivity.class),GET_SUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            if(requestCode == GET_SUM){
                if(data != null){
                    Integer sum = data.getSerializableExtra(SUM_VALUE,Integer.class);
                    if(sum != null){
                        this.sums.add(sum);
                        try {
                            writeSumsToInternalStorage();
                        } catch (IOException e) {
                            Toast.makeText(this, "While writing file: " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        }
    }

    private void writeSumsToInternalStorage() throws IOException {
        File sumsFile = getSumsFile();

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(sumsFile))){
            for(int i=0;i<this.sums.getCount();i++){
                Integer sum = this.sums.getItem(i);
                printWriter.println(sum);

            }
        }
    }

    @Nullable
    private File getSumsFile(){
        File dataDir = this.getDataDir();
        return new File(dataDir,"sums.txt");
    }

*/

/*
    public void computeSum(View view) {
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

        int sum = leftOperand + rightOperand;

        TextView sumEditText = findViewById(R.id.sum);
        sumEditText.setText(String.valueOf(sum));

    }
*/

    public void launchHelp(View view) {
        startActivity(new Intent(this, Help.class));
    }

    public void launchAddFlight(View view) {
        startActivity(new Intent(this, AddFlight.class));
    }

}
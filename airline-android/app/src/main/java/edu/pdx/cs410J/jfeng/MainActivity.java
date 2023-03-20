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
    }

    public void launchHelp(View view) {
        startActivity(new Intent(this, Help.class));
    }

    public void launchAddFlight(View view) {
        startActivity(new Intent(this, AddFlight.class));
    }

    public void launchPrintAirline(View view){
        startActivity(new Intent(this, PrintAirline.class));
    }

    public void launchSearchFlight(View view){
        startActivity(new Intent(this, SearchFlight.class));
    }

}
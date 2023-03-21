package edu.pdx.cs410J.jfeng;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

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
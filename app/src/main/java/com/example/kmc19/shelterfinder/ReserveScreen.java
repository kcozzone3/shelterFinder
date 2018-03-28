package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ReserveScreen extends AppCompatActivity {
    private Spinner reservation, mspin;
    String sheltername;

    //Need to get Extra
    private String email, capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_screen);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //Set number of beds one can reserve
        mspin=(Spinner) findViewById(R.id.reserve_number_spinner);

        //get capcity
        capacity = extras.getString("capacity");
        int CAP = Integer.parseInt(capacity);
        Integer[] items = new Integer[CAP];
        for (int a = 0; a< items.length; a++) {
            items[a] = (a+1);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        mspin.setAdapter(adapter);

        //get the number of beds reserved
        reservation = findViewById(R.id.reserve_number_spinner);

        //get the sheltername
;
        sheltername = extras.getString("shelterName");

        //get email
        email =extras.getString("email");
    }

    public void OnReserve(View view) {
        String numSpots = reservation.getSelectedItem().toString();
        BackgroundCheck backgroundCheck = new BackgroundCheck(this, email, sheltername, numSpots);
        backgroundCheck.execute(email);
    }
    public void OnCancel(View view) {
        Intent intent = new Intent(getBaseContext(), ShelterList.class);
        finish();
        startActivity(intent);
    }
}



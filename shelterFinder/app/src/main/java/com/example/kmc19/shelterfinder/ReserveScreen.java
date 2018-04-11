package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReserveScreen extends AppCompatActivity {
    private Spinner mSpin;
    private String shelterName;

    //Need to get Extra
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_screen);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //Set number of beds one can reserve
        mSpin = findViewById(R.id.reserve_number_spinner);

        //get Capacity
        if(extras.getString("capacity")==null) {
            throw new NullPointerException("capacity is null");
        }
        String Capacity = extras.getString("capacity");
        int CAP = Integer.parseInt(Capacity);
        Integer[] items = new Integer[CAP];
        for (int a = 0; a < items.length; a++) {
            items[a] = (a + 1);
        }
        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        mSpin.setAdapter(adapter);

        //get the number of beds reserved

        //get the shelterName

        shelterName = extras.getString("shelterName");

        //get email
        email = extras.getString("email");
    }

    public void OnReserve(View view) {
        String numSpots = mSpin.getSelectedItem().toString();
        BackgroundCheck backgroundCheck = new BackgroundCheck(this, email, shelterName, numSpots);
        backgroundCheck.execute(email);
    }
    public void OnCancel(View view) {
        Intent intent = new Intent(getBaseContext(), ShelterList.class);
        finish();
        startActivity(intent);
    }
}



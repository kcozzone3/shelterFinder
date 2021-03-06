package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A representation of the inner logic of the reservation screen in the app. Creates a spinner that
 * will only contain numerical values that are within the capacity of the shelter and pushes changes
 * to the database.
 */
public class ReserveScreen extends AppCompatActivity {
    private Spinner reservation;
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
        reservation = findViewById(R.id.reserve_number_spinner);

        //get capacity
        if(extras.getString("capacity") == null) {
            throw new NullPointerException("capacity is null");
        }
        String capacity = extras.getString("capacity");

        int CAP = Integer.parseInt(capacity);
        Integer[] items = new Integer[CAP];
        for (int a = 0; a < items.length; a++) {
            items[a] = (a + 1);
        }
        ArrayAdapter<Integer> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        reservation.setAdapter(adapter);

        //get the shelterName

        shelterName = extras.getString("shelterName");

        //get email
        email = extras.getString("email");
    }

    /**
     * Attempts to reserve spots in the database on the click of the reserve button
     * @param view the current view which the user sees
     */
    public void OnReserve(View view) {
        String numSpots = reservation.getSelectedItem().toString();
        BackgroundCheck backgroundCheck = new BackgroundCheck(this, email, shelterName, numSpots);
        backgroundCheck.execute(email);
    }

    /**
     * Finishes the activity without reserving any spots.
     * @param view the current view which the user sees
     */
    public void OnCancel(View view) {
        Intent intent = new Intent(getBaseContext(), ShelterList.class);
        finish();
        startActivity(intent);
    }
}



package com.example.kmc19.shelterfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailedInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);
        TextView shelterName = findViewById(R.id.detail_shelter_name);
        TextView shelterCapacity = findViewById(R.id.detail_shelter_capacity);
        TextView shelterRestrictions = findViewById(R.id.detail_restrictions);
        TextView longitude = findViewById(R.id.detail_longitude);
        TextView latitude = findViewById(R.id.detail_latitude);
        TextView address = findViewById(R.id.detail_address);
        TextView specialNotes = findViewById(R.id.detail_special_notes);
        TextView phoneNumber = findViewById(R.id.detail_phone);
        Button back = findViewById(R.id.detail_back_button);
        ShelterInfo info = getIntent().getParcelableExtra("shelterInfo");

        shelterName.setText(info.getShelterName());
        shelterCapacity.setText("Current Capacity = " + info.getCapacity());
        shelterRestrictions.setText("Restrictions: " + info.getRestrictions());
        longitude.setText("Longitude: " + Double.toString(info.getLongitude()));
        latitude.setText("Latitude: " + Double.toString(info.getLatitude()));
        address.setText("Address: " + info.getAddress());
        specialNotes.setText("Special Notes: " + info.getSpecialNotes());
        phoneNumber.setText("Phone Number: " + info.getPhone());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

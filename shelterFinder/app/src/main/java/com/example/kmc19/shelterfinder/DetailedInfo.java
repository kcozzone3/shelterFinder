package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The detailed info activity. Users can view all the given information of a shelter and reserve if
 * they are logged in normally.
 */
public class DetailedInfo extends AppCompatActivity {
    private TextView shelterName;
    private String email, capacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Need to get Extra
        //email = getIntent().getStringExtra("email");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);
        shelterName = findViewById(R.id.detail_shelter_name);
        TextView shelterCapacity = findViewById(R.id.detail_shelter_capacity);
        TextView shelterRestrictions = findViewById(R.id.detail_restrictions);
        TextView longitude = findViewById(R.id.detail_longitude);
        TextView latitude = findViewById(R.id.detail_latitude);
        TextView address = findViewById(R.id.detail_address);
        TextView specialNotes = findViewById(R.id.detail_special_notes);
        TextView phoneNumber = findViewById(R.id.detail_phone);
        Button back = findViewById(R.id.detail_back_button);
        Button reserve = findViewById(R.id.detailed_info_reserve_button);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ShelterInfo info = bundle.getParcelable("shelterInfo");
        email = bundle.getString("email");
        shelterName.setText(info.getShelterName());
        capacity = info.getCapacity();
        shelterCapacity.setText("Current Capacity = " + info.getCapacity());
        shelterRestrictions.setText("Restrictions: " + info.getRestrictions());
        longitude.setText("Longitude: " + Double.toString(info.getLongitude()));
        latitude.setText("Latitude: " + Double.toString(info.getLatitude()));
        address.setText("Address: " + info.getAddress());
        specialNotes.setText("Special Notes: " + info.getSpecialNotes());
        phoneNumber.setText("Phone Number: " + info.getPhone());

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ReserveScreen.class);
                finish();
                Bundle extra = new Bundle();
                extra.putString("shelterName", shelterName.getText().toString());
                extra.putString("email", email);
                extra.putString("capacity", capacity);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                finish();
                intent.putExtra("email",email);
            }

        });
    }
}

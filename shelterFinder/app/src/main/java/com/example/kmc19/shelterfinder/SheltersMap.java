package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * The shelter map activity. Displays a map that will contain pins representing the shelters that
 * the user can see on the shelter list activity.
 */
public class SheltersMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<ShelterInfo> shelterList;
    private String email;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        email = getIntent().getStringExtra("email");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_map);
        exit = findViewById(R.id.map_exit);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        shelterList = getIntent().getParcelableArrayListExtra("shelterList");
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                intent.putExtra("email", email);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLngBounds.Builder boundBuilder = new LatLngBounds.Builder();
        LatLngBounds bound;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int padding = 128;

        for (ShelterInfo info : shelterList) {
            LatLng coordinates = new LatLng(info.getLatitude(), info.getLongitude());
            mMap.addMarker(new MarkerOptions().position(coordinates).title(info.getShelterName()
            + " (Current capacity: " + info.getCapacity() + ")"));
            boundBuilder.include(coordinates);
        }
        bound = boundBuilder.build();
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bound, width, height, padding);
        mMap.animateCamera(update);
    }
}

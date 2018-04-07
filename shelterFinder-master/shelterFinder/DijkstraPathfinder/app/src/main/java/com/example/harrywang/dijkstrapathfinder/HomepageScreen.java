package com.example.harrywang.dijkstrapathfinder;

/**
 * Created by Daniel Robertson on 2/12/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomepageScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_homepage_screen);

        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomepageScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

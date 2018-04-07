package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button login_button = findViewById(R.id.login_button);
        Button registration_button = findViewById(R.id.register_button);

        /**
         * On click, activity ends and view changes to login screen.
         */
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginScreen.class);
                finish();
                startActivity(intent);
            }
        });

        /**
         * On click, activity ends and view changes to registration screen.
         */
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegistrationScreen.class);
                finish();
                startActivity(intent);
            }
        });
    }
}

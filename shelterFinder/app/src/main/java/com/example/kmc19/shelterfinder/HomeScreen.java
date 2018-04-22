package com.example.kmc19.shelterfinder;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Representation of the home screen
 */
@TargetApi(21)
public class HomeScreen extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        final boolean isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        super.onCreate(savedInstanceState);
        if (isLollipop) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }
        setContentView(R.layout.activity_home_screen);
        Button login_button = findViewById(R.id.login_button);
        Button registration_button = findViewById(R.id.register_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginScreen.class);
                if (isLollipop) {
                    animatedStart(intent);
                } else {
                    finish();
                    startActivity(intent);
                }
            }
        });
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegistrationScreen.class);
                if (isLollipop) {
                    animatedStart(intent);
                } else {
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    private void animatedStart(Intent intent) {
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this)
                .toBundle());
        finish();
    }
}

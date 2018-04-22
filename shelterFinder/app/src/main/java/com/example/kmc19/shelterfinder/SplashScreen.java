package com.example.kmc19.shelterfinder;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Window;

public class SplashScreen extends AppCompatActivity {

    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        final boolean isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        super.onCreate(savedInstanceState);
        if (isLollipop) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Fade());
        }
        setContentView(R.layout.activity_splash_screen);

        final int TIMEOUT = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
                if (isLollipop) {
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this)
                                    .toBundle());
                } else {
                    startActivity(intent);
                }
                finish();
            }
        }, TIMEOUT);
    }
}

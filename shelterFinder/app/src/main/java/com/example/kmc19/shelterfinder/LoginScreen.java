package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The login screen activity. Users can enter in their username and password to have it checked in
 * the database. If matched, they will be sent to the shelter list activity. Users can also use the
 * guest button to log in with limited capabilities.
 */
public class LoginScreen extends AppCompatActivity {
    private EditText emailEt;
    private EditText passwordEt;
    private TextView incorrectLogin;
    protected int clickCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button loginButton = findViewById(R.id.login_button);
        Button guestButton = findViewById(R.id.guest_button);
        Button cancelButton = findViewById(R.id.login_cancel_button);
        emailEt = findViewById(R.id.id_inputfield);
        passwordEt = findViewById(R.id.password_inputfield);
        incorrectLogin = findViewById(R.id.login_incorrect_creds);
        clickCount = 0;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLogin(view);
            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                finish();
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void OnLogin(View view){
        clickCount = clickCount+1;
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String type = "login";
        String countString = String.valueOf(clickCount);
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, password, countString);

    }

    /**
     * Sets the incorrect login error message to be visible.
     */
    public void setIncorrectLogin(){
        incorrectLogin.setVisibility(View.VISIBLE);
    }
}

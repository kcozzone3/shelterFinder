package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private TextView incorrectLogin;
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLogin(view);
            }
        });

        /**
         * When user taps proceed as guest button, activity finishes and switches to ShelterList
         * View with the permissions of user type Guest.
         */
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                finish();
                startActivity(intent);
            }
        });

        /**
         * On click, activity finishes and user is sent back to the home screen.
         */
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
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, password);

    }
    public void setIncorrectLogin(){
        incorrectLogin.setVisibility(View.VISIBLE);
    }
}

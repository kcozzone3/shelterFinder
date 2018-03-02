package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {
    EditText emailEt, passwordEt;
    TextView incorrectLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button guestButton = (Button) findViewById(R.id.guest_button);
        emailEt = (EditText) findViewById(R.id.id_inputfield);
        passwordEt = (EditText) findViewById(R.id.password_inputfield);
        incorrectLogin = (TextView) findViewById(R.id.incorrectText);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                finish();
                startActivity(intent);
            }
        });
    }
    public void OnLogin(View view){
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

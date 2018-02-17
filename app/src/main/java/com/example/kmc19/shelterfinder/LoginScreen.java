package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button guestButton = (Button) findViewById(R.id.guest_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        final TextView incorrectLogin = (TextView) findViewById(R.id.incorrectText);
        final EditText username = (EditText) findViewById(R.id.id_inputfield);
        final EditText password = (EditText) findViewById(R.id.password_inputfield);
        Model_UserList userList = new Model_UserList();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = username.getText().toString();
                String pass = password.getText().toString();
                if (id.equals("user") && pass.equals("pass")) {
                    Intent intent = new Intent(getBaseContext(), ShelterList.class);
                    finish();
                    startActivity(intent);
                } else {
                    incorrectLogin.setVisibility(View.VISIBLE);
                }

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
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                finish();
                startActivity(intent);
            }
        });

    }
}

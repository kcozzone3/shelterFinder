package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The registration screen in which users can create accounts to be stored in the database.
 *
 * @author hwang703
 */
public class RegistrationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        Button registerButton = findViewById(R.id.registration_register_button);
        Button cancelButton = findViewById(R.id.registration_cancel_button);

        final TextView usernameError = findViewById(R.id.registration_email_error_message);
        final TextView fullNameError = findViewById(R.id.registration_name_error_message);
        final TextView passwordError = findViewById(R.id.registration_password_error_message);

        final EditText username = findViewById(R.id.registration_email_box);
        final EditText fullName = findViewById(R.id.registration_name_box);
        final EditText password = findViewById(R.id.registration_password_box);

        final Model_UserList userList = new Model_UserList();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = username.getText().toString();
                String name = fullName.getText().toString();
                String pass = password.getText().toString();
                boolean invalidUser = !(id.matches(".*@.*"));
                boolean invalidPass = !(pass.matches(".*[A-Za-z].*") && pass.matches(".*[0-9].*"));
                boolean invalidName = !name.matches("[A-Za-z]*\\s[A-Za-z]*");
                if (invalidUser) {
                    usernameError.setVisibility(View.VISIBLE);
                }
                if (invalidName) {
                    fullNameError.setVisibility(View.VISIBLE);
                }
                if (invalidPass) {
                    passwordError.setVisibility(View.VISIBLE);
                }
                if (!invalidUser && !invalidPass && !invalidName) {
                    Model_User newUser = new Model_User(id, name, pass, false);
                    userList.addUser(newUser);
                    Intent intent = new Intent(getBaseContext(), ShelterList.class);
                    finish();
                    startActivity(intent);
                }
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
}

package com.example.kmc19.shelterfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }
}

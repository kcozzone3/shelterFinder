package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;


/**
 * The registration screen activity. Users can enter in their name, email, and password to register
 * in the database.
 */
public class RegistrationScreen extends AppCompatActivity {
    private EditText editEmail, editUsername, editPassword;
    private Spinner userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        editEmail = findViewById(R.id.registration_email_box);
        editUsername = findViewById(R.id.registration_name_box);
        editPassword = findViewById(R.id.registration_password_box);
        userType = findViewById(R.id.user_type_spinner);

        ArrayAdapter<String> adapterCS
                = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, UserType.values());
        adapterCS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapterCS);


    }

    /**
     * If required fields are filled, user will be registered in the database.
     * @param view the current view which the user sees
     */
    public void OnRegister (View view) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String email = editEmail.getText().toString();
        String userTypeStr = userType.getSelectedItem().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, username, password, userTypeStr);
    }

    /**
     * Finishes the activity without registering the user in the database. Starts the HomeScreen
     * activity.
     * @param view the current view which the user sees
     */
    public void OnCancel(View view) {
        finish();
        startActivity(new Intent(this, HomeScreen.class));
    }


}



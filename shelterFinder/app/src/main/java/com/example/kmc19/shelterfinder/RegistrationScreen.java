package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;


public class RegistrationScreen extends AppCompatActivity {
    private EditText editEmail, editUsername, editPassword;
    private Spinner usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        editEmail = findViewById(R.id.registration_email_box);
        editUsername = findViewById(R.id.registration_name_box);
        editPassword = findViewById(R.id.registration_password_box);
        usertype = findViewById(R.id.user_type_spinner);

        ArrayAdapter<String> adapterCS = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, UserType.values());
        adapterCS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(adapterCS);


    }

    public void OnRegister (View view) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String email = editEmail.getText().toString();
        String userTypeStr = usertype.getSelectedItem().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, username, password, userTypeStr);
    }
    public void OnCancel(View view) {
        startActivity(new Intent(this, HomeScreen.class));
    }


}



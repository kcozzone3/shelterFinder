package com.example.kmc19.shelterfinder;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;


/**
 * The registration screen activity. Users can enter in their name, email, and password to register
 * in the database.
 */
@TargetApi(21)
public class RegistrationScreen extends AppCompatActivity {
    private EditText editEmail;
    private EditText editUsername;
    private EditText editPassword;
    private Spinner userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        super.onCreate(savedInstanceState);
        if (isLollipop) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));
        }
        setContentView(R.layout.activity_registration_screen);
        editEmail = findViewById(R.id.registration_email_box);
        editUsername = findViewById(R.id.registration_name_box);
        editPassword = findViewById(R.id.registration_password_box);
        userType = findViewById(R.id.user_type_spinner);

        ArrayAdapter<String> adapterCS
                = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, UserType.values());
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
        Intent intent = new Intent(this, HomeScreen.class);
        animatedStart(intent);
    }

    private void animatedStart(Intent intent) {
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegistrationScreen.this)
                .toBundle());
        finish();
    }
}



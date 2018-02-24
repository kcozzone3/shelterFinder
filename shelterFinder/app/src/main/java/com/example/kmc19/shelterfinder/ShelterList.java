package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShelterList extends AppCompatActivity {
    private ListView shelterView;
    private List<ShelterInfo> shelterList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);
        shelterView = findViewById(R.id.shelter_list_view);
        shelterList = new ArrayList<>();
        readShelterdata();
        ArrayAdapter<ShelterInfo> arrayAdapter = new ArrayAdapter<ShelterInfo>(this,
                android.R.layout.simple_list_item_1, shelterList);
        shelterView.setAdapter(arrayAdapter);
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                finish();
                startActivity(intent);
            }
        });

    }
    private void readShelterdata() {
        InputStream is = getResources().openRawResource(R.raw.shelter);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line ="";
        try {
            //step over headers
            reader.readLine();
            while((line = reader.readLine()) != null) {
                //Split by ","
                String[] tokens = line.split(",");
                //Read data
                ShelterInfo shelterInfo= new ShelterInfo();
                shelterInfo.setShelterName(tokens[1]);
                if(tokens[2].equals("")){
                    shelterInfo.setCapacity("N/A");
                }else{
                    shelterInfo.setCapacity(tokens[2]);
                }
                shelterList.add(shelterInfo);

                Log.d("ShelterList", "Just created" + shelterInfo);


            }
        } catch (IOException e) {
            Log.wtf("ShelterList", "Error reading shelter.csv" + line, e);
            e.printStackTrace();
        }

    }
}

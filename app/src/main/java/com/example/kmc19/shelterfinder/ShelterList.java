package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        final ArrayAdapter<ShelterInfo> arrayAdapter = new ArrayAdapter<ShelterInfo>(this,
                android.R.layout.simple_list_item_1, shelterList);
        shelterView.setAdapter(arrayAdapter);
        shelterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), DetailedInfo.class);
                intent.putExtra("shelterInfo", shelterList.get(position));
                startActivity(intent);
            }
        });
        Button logoutButton = findViewById(R.id.logout_button);
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
                shelterInfo.setRestrictions(tokens[3]);
                shelterInfo.setLongitude(tokens[4]);
                shelterInfo.setLatitude(tokens[5]);
                String stringBuffer = "";
                int n = 6;
                for (int i = 0; i < 2; i++) {
                    if (tokens[n].charAt(0) == '\"') {
                        boolean endOfBuffer = false;
                        while (!endOfBuffer) {
                            if (tokens[n].charAt(tokens[n].length() - 1) == '\"') {
                                tokens[n] = tokens[n].substring(0, tokens[n].length() - 1);
                                stringBuffer += tokens[n];
                                endOfBuffer = true;
                            } else {
                                stringBuffer += tokens[n].substring(1) + ", ";
                            }
                            n++;
                        }
                    } else {
                        stringBuffer += tokens[n];
                        n++;
                    }
                    if (i == 0) {
                        shelterInfo.setAddress(stringBuffer);
                    } else {
                        shelterInfo.setSpecialNotes(stringBuffer);
                    }
                    stringBuffer = "";
                }
                shelterInfo.setPhone(tokens[n]);
                System.out.println("Success");
                shelterList.add(shelterInfo);

                Log.d("ShelterList", "Just created" + shelterInfo);


            }
        } catch (IOException e) {
            Log.wtf("ShelterList", "Error reading shelter.csv" + line, e);
            e.printStackTrace();
        }

    }
}

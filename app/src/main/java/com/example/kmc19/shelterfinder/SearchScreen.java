package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends AppCompatActivity{
    CheckBox male, female, famNewborn, children, youngAdult, anyone;
    EditText shelter;
    String age, gender, shelterName;
    ArrayList<ShelterInfo> shelterList;
    String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        shelterList = extras.getParcelableArrayList("shelterList");
        email = extras.getString("email");
        Button searchButton = findViewById(R.id.search_search_button);
        Button cancelButton = findViewById(R.id.search_cancel_button);
        shelter = findViewById(R.id.search_name_field);
        male = findViewById(R.id.search_male_box);
        female = findViewById(R.id.search_female_box);
        famNewborn = findViewById(R.id.search_families_newborns_box);
        children = findViewById(R.id.search_children_box);
        youngAdult = findViewById(R.id.search_young_adults_box);
        anyone = findViewById(R.id.search_anyone_box);
        //getJSON("http://128.61.9.17:8888/search_match.php");
        age = "";
        gender = "";

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    female.setChecked(false);
                }
                gender = "men";
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    male.setChecked(false);
                }
                gender = "women";
            }
        });

        famNewborn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    children.setChecked(false);
                    youngAdult.setChecked(false);
                    anyone.setChecked(false);
                }
                age = "families w/ newborns";
            }
        });

        children.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    famNewborn.setChecked(false);
                    youngAdult.setChecked(false);
                    anyone.setChecked(false);
                }
                age = "children";
            }
        });

        youngAdult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    famNewborn.setChecked(false);
                    children.setChecked(false);
                    anyone.setChecked(false);
                }
                age = "young adult";
            }
        });

        anyone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    famNewborn.setChecked(false);
                    children.setChecked(false);
                    youngAdult.setChecked(false);
                }
                age = "anyone";
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch(v);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShelterList.class);
                finish();
                intent.putExtra("email",email);
            }
        });

    }
    public void onSearch(View view) {
        shelterName = shelter.getText().toString().toLowerCase();
        List<ShelterInfo> filteredNameList = new ArrayList<>();
        List<ShelterInfo> filteredGenderList = new ArrayList<>();
        List<ShelterInfo> filteredAgeList = new ArrayList<>();

        //filter using shelter name
        if(shelterName.equals("")){
            filteredNameList = shelterList;
        } else {
            for(int i = 0; i < shelterList.size(); i++) {
                if (shelterList.get(i).getShelterName().toLowerCase().contains(shelterName)) {
                    filteredNameList.add(shelterList.get(i));
                }
            }
        }
        //filter using age
        if (age.equals("")) {
            filteredAgeList = filteredNameList;
        } else {
            for (int i = 0; i < filteredNameList.size(); i++) {
                if(filteredNameList.get(i).getRestrictions().toLowerCase().contains(age)) {
                    filteredAgeList.add(filteredNameList.get(i));
                }
            }
        }
        //filter using gender
        if (gender.equals("")) {
            filteredGenderList = filteredAgeList;
        } else {
            for (int i = 0; i < filteredAgeList.size(); i++) {
                if(filteredAgeList.get(i).getRestrictions().toLowerCase().contains(gender)) {
                    if (gender.equals("women") || (gender.equals("men") && !filteredAgeList.get(i).getRestrictions().toLowerCase().contains("women"))) {
                        filteredGenderList.add(filteredAgeList.get(i));
                    }
                }
            }
        }

        Intent intent = new Intent();
        if (filteredGenderList.size() != 0) {
            intent.putParcelableArrayListExtra("filteredShelters", (ArrayList<ShelterInfo>) filteredGenderList);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

}

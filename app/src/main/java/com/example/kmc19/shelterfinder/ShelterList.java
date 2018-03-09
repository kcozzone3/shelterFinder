package com.example.kmc19.shelterfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShelterList extends AppCompatActivity {
    private List<ShelterInfo> shelterList = new ArrayList<>();
    private List<ShelterInfo> filteredList = new ArrayList<>();
    ArrayAdapter<ShelterInfo> arrayAdapter;
    ListView shelterView;
    boolean filtered;

    public ShelterList getThis() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);
        shelterView = findViewById(R.id.shelter_list_view);
        getJSON("http://128.61.119.185:8888/retrieve_data.php");
        shelterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), DetailedInfo.class);
                if (filtered) {
                    intent.putExtra("shelterInfo", filteredList.get(position));
                } else {
                    intent.putExtra("shelterInfo", shelterList.get(position));
                }
                startActivity(intent);
            }
        });
        Button logoutButton = findViewById(R.id.logout_button);
        Button searchButton = findViewById(R.id.shelter_list_search_button);
        Button clearButton = findViewById(R.id.shelter_list_clear_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchScreen.class);
                intent.putParcelableArrayListExtra("shelterList", (ArrayList<ShelterInfo>) shelterList);
                startActivityForResult(intent, 1);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter = new ArrayAdapter<>(getThis(), android.R.layout.simple_list_item_1, shelterList);
                shelterView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("filteredShelters")) {
                    filtered = true;
                    filteredList = data.getParcelableArrayListExtra("filteredShelters");
                    arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
                    shelterView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }
            } else {
                filtered = false;
            }
        }
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            ShelterInfo shelterInfo = new ShelterInfo();
            shelterInfo.setShelterName(obj.getString("shelter_name"));
            if(obj.getString("capacity").equals("")){
                shelterInfo.setCapacity("N/A");
            }else{
                shelterInfo.setCapacity(obj.getString("capacity"));
            }
            shelterInfo.setRestrictions(obj.getString("restrictions"));
            shelterInfo.setLongitude(obj.getString("longitude"));
            shelterInfo.setLatitude(obj.getString("latitude"));
            shelterInfo.setAddress(obj.getString("address"));
            shelterInfo.setSpecialNotes(obj.getString("special_notes"));
            shelterInfo.setPhone(obj.getString("phone_number"));
            shelterList.add(shelterInfo);
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shelterList);
        shelterView.setAdapter(arrayAdapter);
    }
}
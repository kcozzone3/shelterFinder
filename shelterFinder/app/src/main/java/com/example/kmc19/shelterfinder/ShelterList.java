package com.example.kmc19.shelterfinder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
    private List<ShelterInfo> shelterInfoList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        listView = (ListView) findViewById(R.id.shelter_list_view);
        getJSON("http://192.168.1.79:8888/retrieve_data.php");
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
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
                    Log.d("aaa", sb.toString());
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
            shelterInfoList.add(shelterInfo);

        }
        ArrayAdapter<ShelterInfo> arrayAdapter = new ArrayAdapter<ShelterInfo>(this, android.R.layout.simple_list_item_1, shelterInfoList);
        listView.setAdapter(arrayAdapter);
    }
}
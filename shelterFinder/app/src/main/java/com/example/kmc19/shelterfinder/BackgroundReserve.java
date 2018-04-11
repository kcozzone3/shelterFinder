package com.example.kmc19.shelterfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class BackgroundReserve extends AsyncTask<String, String, String> {

    private final Activity context;
    private AlertDialog alertDialog;
    BackgroundReserve(Activity ctx) {
            context = ctx;
        }
    private String email;

    @Override

    protected String doInBackground (String... params) {
        String shelterName = params[0];
        String reservation = params[1];
        email = params[2];
        String urlPath = "http://192.168.1.68:8888/";
        String reserve_url = urlPath + "reserve.php";
        try {
            URL url = new URL(reserve_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("shelter_name", "UTF-8")+ "="
                    + URLEncoder.encode(shelterName, "UTF-8") + "&" +
                    URLEncoder.encode("reservation", "UTF-8")+ "="
                    + URLEncoder.encode(reservation, "UTF-8") + "&" +
                    URLEncoder.encode("email", "UTF-8")
                    +  "=" +URLEncoder.encode(email, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line;
            while((bufferedReader.readLine()) != null) {
                line = bufferedReader.readLine();
                result = result + line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Reservation Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if(result.equals("Successful Reservation")) {
            alertDialog.show();
            Intent intent = new Intent(context.getBaseContext(), ShelterList.class);
            intent.putExtra("email", email);
            context.startActivity(intent);
        }
    }
}

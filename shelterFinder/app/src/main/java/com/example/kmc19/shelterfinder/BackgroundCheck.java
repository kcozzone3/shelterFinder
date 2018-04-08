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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundCheck extends AsyncTask<String, String, String> {
    Activity context;
    AlertDialog alertDialog;
    String email, shelterName, numSpots;
    BackgroundCheck(Activity ctx, String email, String shelterName, String numSpots) {
        context = ctx;
        this.email = email;
        this.shelterName = shelterName;
        this.numSpots = numSpots;
    }



    @Override

    protected String doInBackground (String... params) {
        String urlpath = "http://128.61.124.225:8888/";
        String reserve_url = urlpath + "check_reservation.php";
        try {
            URL url = new URL(reserve_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =  new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8")+ "=" + URLEncoder.encode(email, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Checking Reservation Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if(result.equals("False")) {
            alertDialog.show();
            Intent intent = new Intent(context.getBaseContext(), ShelterList.class);
            intent.putExtra("email",email);
            context.startActivity(intent);
        } else {
            BackgroundReserve backgroundReserve = new BackgroundReserve(context);
            backgroundReserve.execute(shelterName, numSpots, email);
        }
    }


}

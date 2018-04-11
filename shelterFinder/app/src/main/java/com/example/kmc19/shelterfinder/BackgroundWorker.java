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


class BackgroundWorker extends AsyncTask<String, String, String> {
    private String uniqueEmail = "";
    private final Activity context;

    private AlertDialog alertDialog;
    BackgroundWorker(Activity ctx) {
        context = ctx;
    }
    @Override

    protected String doInBackground (String... params) {
        String type = params[0];
        String urlPath = "http://192.168.1.68:8888/";
        String login_url = urlPath + "login.php";
        String register_url = urlPath + "register.php";
        if (type.equals("login")) {
            try {
                String email = params[1];
                uniqueEmail = email;
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data =
                        URLEncoder.encode("email", "UTF-8")+ "="
                                + URLEncoder.encode(email, "UTF-8") + "&" +
                URLEncoder.encode("password", "UTF-8")
                                + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line ;
                while((bufferedReader.readLine()) != null) {
                    line = bufferedReader.readLine();
                    result = result + line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")) {
            try {
                String email = params[1];
                String username = params[2];
                String password = params[3];
                String userType = params[4];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8")+ "="
                        + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8")+ "="
                        + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("userType", "UTF-8")+ "="
                        + URLEncoder.encode(userType, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line ;
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
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }
    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if (result.equals("login success")) {
            Intent intent = new Intent(context.getBaseContext(), ShelterList.class);
            intent.putExtra("email", uniqueEmail);
            context.startActivity(intent);
        } else if (result.equals("Register Successful")) {
            alertDialog.show();
            Intent intent = new Intent(context.getBaseContext(), HomeScreen.class);
            context.startActivity(intent);
        }
        else {
            ((LoginScreen)context).setIncorrectLogin();
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}


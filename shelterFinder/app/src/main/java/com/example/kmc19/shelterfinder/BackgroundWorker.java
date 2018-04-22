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
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class BackgroundWorker extends AsyncTask<String, String, String> {
    private String uniqueEmail = "";
    private final Activity context;
    private AlertDialog alertDialog;
    private int count =0;
    private String globalEmail;
    BackgroundWorker(Activity ctx) {
        context = ctx;
    }
    @Override

    protected String doInBackground (String... params) {
        String type = params[0];
        String urlPath = "http://128.61.3.20:8888/";
        String login_url = urlPath + "login.php";
        String register_url = urlPath + "register.php";
        if ("login".equals(type)) {
            try {
                String email = params[1];
                uniqueEmail = email;
                globalEmail = uniqueEmail;
                String password = params[2];
                String clickCount = params[3];
                count = Integer.valueOf(clickCount);
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8")+ "="
                        + URLEncoder.encode(email, "UTF-8") + "&" +
                URLEncoder.encode("password", "UTF-8")+ "="
                        + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader
                        = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
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
        }else if("register".equals(type)) {
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
                String post_data = URLEncoder.encode("email", "UTF-8")+ "="
                        + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8")+ "="
                        + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8")+ "="
                        + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("usertype", "UTF-8")+ "="
                        + URLEncoder.encode(userType, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
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
        if ("login success".equals(result)) {
            Intent intent = new Intent(context.getBaseContext(), ShelterList.class);
            intent.putExtra("email", uniqueEmail);
            context.startActivity(intent);
        } else if ("Register Successful".equals(result)) {
            alertDialog.show();
            Intent intent = new Intent(context.getBaseContext(), HomeScreen.class);
            context.startActivity(intent);
        } else if (count == 3) {
            alertDialog.setMessage("This is your third attempt; the system will delete your credential with one more incorrect try");
            alertDialog.show();
        } else if (count == 4) {
            BackgroundDelete backgroundDelete = new BackgroundDelete(context);
            backgroundDelete.execute(globalEmail);
            alertDialog.setMessage("Fourth time error try. User account deleted");
            alertDialog.show();
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


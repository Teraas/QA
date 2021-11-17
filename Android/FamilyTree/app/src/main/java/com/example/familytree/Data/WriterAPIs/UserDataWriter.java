package com.example.familytree.Data.WriterAPIs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.familytree.LoginActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserDataWriter {
    String apiToken = "";
    SharedPreferences sharedpreferences;
    public static final String mypreference = "famlyPref";
    String URL = "http://192.168.1.7:3000/";
    String responseBodyString;
    public UserDataWriter( SharedPreferences sharedpreference) {
        sharedpreferences = sharedpreference;
    }
    public void writeUserDetails(String path) throws JSONException, IOException {
        List<NameValuePair> paramsUser = new ArrayList<NameValuePair>();

        postAPICall(URL + path, paramsUser);
    }

    public String userSigniup( String email, String password ) throws JSONException, IOException {
        List<NameValuePair> paramsUser = new ArrayList<NameValuePair>();
        paramsUser.add(new BasicNameValuePair("email",email));
        paramsUser.add(new BasicNameValuePair("password",password));

        // Update the token in ref

        return apiToken;
    }
    public String userSignin( String email, String password ) throws JSONException, IOException {
        List<NameValuePair> paramsUser = new ArrayList<NameValuePair>();
        paramsUser.add(new BasicNameValuePair("email",email));
        paramsUser.add(new BasicNameValuePair("password",password));

        apiToken = "";
        //Log.d("Token Auth API1 ", postAPICall(URL + "signin", paramsUser).toString());
        String res= postAPICall(URL + "signin", paramsUser);
        //System.out.println("my res"+ res);
        //Log.d("Token Auth API1 ", res);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", apiToken);
        editor.commit();
        Log.d("Token Auth API ", apiToken);
        //Toast.makeText(UserDataWriter.this, "Authentication failed, please check the form and try again.").show();
        return apiToken;
    }
    /*
    TODO - Refactor it to have separate Network service for external calls
     */
    private String postAPICall(String URI, List<NameValuePair> params) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        for (NameValuePair s:params
             ) {
            jsonObject.put(s.getName(), s.getValue());
        }

        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create( jsonObject.toString(), JSON);


        Request request = new Request.Builder()
                .url(URI)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        Log.d("Harish Request ", jsonObject.toString());
        Call call = client.newCall(request);
        Response[] response = new Response[1];

        try {
            Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            //Log.d("Harish Thread ", "here");
                            response[0] = call.execute();
                            responseBodyString = response[0].body().string();
                            //Log.d("Harish Response ", responseBodyString);
                            updateTokenInSharedRef(responseBodyString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            thread.start();


        }  catch (Exception e) {
            // writing exception to log
            //Log.d("Harish Exception ", e.toString());
            e.printStackTrace();
        }
        return responseBodyString;
    }

    private void updateTokenInSharedRef(String res) throws JSONException {
        JSONObject Jobject = new JSONObject(res);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        //Log.d("Harish Response2 ", Jobject.get("token").toString());
        editor.putString("token", Jobject.get("token").toString());
        editor.commit();
        //Log.d("Harish Response3 ", sharedpreferences.getString("token", ""));
    }
}
package com.example.familytree.Data.WriterAPIs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
    String URL = "http://192.168.0.101:3000/";
    public UserDataWriter( SharedPreferences sharedpreference) {
        sharedpreferences = sharedpreference;
    }
    public void writeUserDetails(String path) throws JSONException, IOException {
        List<NameValuePair> paramsUser = new ArrayList<NameValuePair>();

        postAPICall(URL + path, paramsUser);
    }

    public String userSignin( String email, String password ) throws JSONException, IOException {
        List<NameValuePair> paramsUser = new ArrayList<NameValuePair>();
        paramsUser.add(new BasicNameValuePair("email",email));
        paramsUser.add(new BasicNameValuePair("password",password));

        apiToken = "";
        postAPICall(URL + "signup", paramsUser);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", apiToken);
        editor.commit();

        return apiToken;
    }

    private Response postAPICall(String URI, List<NameValuePair> params) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        for (NameValuePair s:params
             ) {
            jsonObject.put(s.getName(), s.getValue());
        }

        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create( jsonObject.toString(), JSON);

//        RequestBody formBody = new FormBody.Builder()
//                .add("email", params.get(0).getValue())
//                .add("password", params.get(1).getValue())
//                .build();

        Request request = new Request.Builder()
                .url(URI)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        Log.d("Harish Request ", jsonObject.toString());
        Call call = client.newCall(request);
        Response[] response = new Response[1];
        //response[0] = "";
//        try {
//            Response resp = call.execute();
//            return resp .toString();
//        } catch( Exception e ) {
//            Log.d("Harish Exception1 ", e.toString());
//        }


        try {
            Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            Log.d("Harish Thread ", "here");
                            response[0] = call.execute();
                            Log.d("Harish Response ", response[0].message());
                            //return response[0] .toString();
                            //resp = response[0].toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            thread.start();


        }  catch (Exception e) {
            // writing exception to log
            Log.d("Harish Exception ", e.toString());
            e.printStackTrace();
        }
        //Log.d("Harish Response2 ", response[0].message());
        return response[0];
    }
}
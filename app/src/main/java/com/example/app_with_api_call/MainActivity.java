package com.example.app_with_api_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        This is the info that will show.
        TextView outputCity = findViewById(R.id.textViewCity);
        TextView outputState = findViewById(R.id.textViewState);
        TextView outputCountry = findViewById(R.id.textViewCountry);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(e -> {
            EditText inPut = findViewById(R.id.edit_Text_City);
            System.out.println("button");
//            This is the text that the user enters.
            final Editable city = inPut.getText();

//            I found this API on rapidapi.com. The Website showed me vaguely how to use it.
            OkHttpClient client = new OkHttpClient();

            String urlString = "https://andruxnet-world-cities-v1.p.rapidapi.com/?query=" + city + "&searchby=city";

            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .addHeader("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "1a80c5008dmsh9cfd3107d4c60fdp14ea41jsn5ec6952c5235")
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                //            Child thread I believe.
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
//                        body is a big string that the API returned.
                        ResponseBody responseBody = response.body();
                        assert responseBody != null;
                        String body = responseBody.string();
//                        This prints out the response just to make sure it's working.
                        Log.d(TAG, "onResponse: " + body);
                        System.out.println(body);

//                    This takes the first city in the list and stores it.

                        if (body.length() < 3 || inPut.length() < 3) {
                            System.out.println(city);
                            if (inPut.length() > 2) {
                                String s = "A city with that sequence";
                                String s1 = "could not be found. Sorry.";
                                outputCity.setText(s);
                                outputState.setText(s1);
                                outputCountry.setText("");
                            }
                            else {
                                String s = "Please enter a sequence of 3";
                                String s1 = "or more characters.";
                                outputCity.setText(s);
                                outputState.setText(s1);
                                outputCountry.setText("");
                            }

                        }
                        else {
                            CityInfo city1 = new CityInfo(body);
                            System.out.println(city1);

//                            This sets the output text to show the city/state/country
                            outputCity.setText(city1.getCity());
                            outputState.setText(city1.getState());
                            outputCountry.setText(city1.getCountry());
                        }
                    }
                }
            });

        });


//                    StringBuilder cityReal = new StringBuilder();
//                    StringBuilder state = new StringBuilder();
//                    StringBuilder country = new StringBuilder();
//
//                    char c = body.charAt(0);
//                    int i = 0;
//
//                    while (c != ':') {
//                        i++;
//                        c = body.charAt(i);
//                    }
////                    Get CITY
//                    i ++;
//                    while (c != '"') {
//                        cityReal.append(c);
//                        i++;
//                        c = body.charAt(i);
//                    }
//                    i++;
//
//                    while (c != ':') {
//                        i++;
//                        c = body.charAt(i);
//                    }
////                    Get STATE
//                    i ++;
//                    while (c != '"') {
//                        state.append(c);
//                        i++;
//                        c = body.charAt(i);
//                    }
//                    while (c != ':') {
//                        i++;
//                        c = body.charAt(i);
//                    }
////                    get COUNTRY
//                    i ++;
//                    while (c != '"') {
//                        country.append(c);
//                        i++;
//                        c = body.charAt(i);
//                    }
//
//
//                    cityReal.delete(0, 1);
//                    state.delete(0, 1);
//                    country.delete(0, 1);
//                    System.out.println("City: " + cityReal.toString());
//                    System.out.println("State: " + state.toString());

//                    System.out.println("Country: " + country.toString());
//                //Fetching nested Json using JSONArray
//                JSONArray arrObj = null;
//                try {
//                    arrObj = jsonObj.getJSONArray("city");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                for (int i = 0; i < arrObj .length(); i++) {
//                    String programming = null;
//                    try {
//                        programming = arrObj.getJSONObject(i).getString("state");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    String scripting = null;
//                    try {
//                        scripting = arrObj.getJSONObject(i).getString("country");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
////                    String ml = null;
////                    try {
////                        ml = arrObj.getJSONObject(i).getString("ml");
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//
//                    System.out.println("###### Emp Skills (nested) ###########");
//                    System.out.println("Programming : " + programming);
//                    System.out.println("Scripting   : " + scripting);
////                    System.out.println("Ml          : " + ml);
//                }


//        This should not be run on the main thread.
//        Request request = new Request.Builder()
//                .url("https://publicobject.com/helloworld.txt")
//                .build();
//
////        This client makes sure it is not ran in the main thread.
//        client.newCall(request).enqueue(new Callback() {
//
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
////            Child thread
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()) {
////                    Input string
//                    ResponseBody responseBody = response.body();
//                    String body = responseBody.string();
//                    Log.d(TAG, "onResponse: " + body);
//                    Log.d(TAG, "onResponse: " + body);
//
//                }
//            }
//        });
    }
}
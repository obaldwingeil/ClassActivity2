package com.example.classactivity2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private EditText plainText_cityName;
    private Button button_Go;

    private static String api_url;
    private String base_url = "http://api.openweathermap.org/data/2.5/weather?units=imperial&q=";
    private String api_key = "&appid=e4b69a72ee5277506c130dd3dbcc5e56";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plainText_cityName = findViewById(R.id.plainText_cityName);
        button_Go = findViewById(R.id.button_Go);

        button_Go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("button", "button clicked");
                launchNextActivity(v);

            }
        });
    }

    public void launchNextActivity(View view){
        String city = plainText_cityName.getText().toString();
        api_url = base_url + city + api_key;
        Log.d("url", api_url);
        // api_url = "https://icanhazdadjoke.com/";
        // api_url = "http://api.openweathermap.org/data/2.5/weather?q=pasadena&appid=e4b69a72ee5277506c130dd3dbcc5e56";

        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("data", json.toString());
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api", "city not found");
                // Log.d("api", "city not found");
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });


    }

}
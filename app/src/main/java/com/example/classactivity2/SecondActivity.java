package com.example.classactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    private TextView textView_cityCountry;
    private String data;
    private TextView textView_description;
    private TextView textView_high;
    private TextView textView_low;
    private TextView textView_feelsLike;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView_cityCountry = findViewById(R.id.textView_cityCountry);
        textView_description = findViewById(R.id.textView_description);
        textView_high = findViewById(R.id.textView_high);
        textView_low = findViewById(R.id.textView_low);
        textView_feelsLike = findViewById(R.id.textView_feelsLike);

        Intent intent = getIntent();
        data = intent.getStringExtra("data");

        try {
            JSONObject jsonData = new JSONObject(data);
            // Log.d("json",jsonData.toString());
            String city = jsonData.get("name").toString();
            String country = jsonData.getJSONObject("sys").get("country").toString();
            // Log.d("json", country);
            // Log.d("json", city);
            String cityCountry = city + ", " + country;
            textView_cityCountry.setText(cityCountry);
            String description = jsonData.getJSONArray("weather").getJSONObject(0).get("description").toString();
            textView_description.setText(description);
            String high = "High \t\t" + jsonData.getJSONObject("main").get("temp_max").toString() + "F";
            textView_high.setText(high);
            String low = "Low \t\t" + jsonData.getJSONObject("main").get("temp_min").toString() + "F";
            textView_low.setText(low);
            String feelsLike = "Feels like \t\t" + jsonData.getJSONObject("main").get("feels_like").toString() + "F";
            textView_feelsLike.setText(feelsLike);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // getData();**/

    }

    public void getData(){

    }
}

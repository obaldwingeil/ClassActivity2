package com.example.classactivity2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class ThirdActivity extends AppCompatActivity {

    private TextView textView_error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textView_error = findViewById(R.id.textView_error);
        textView_error.setText("City Not Found");
    }

}

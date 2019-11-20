package com.example.tutor3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityOutput extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        TextView result = (TextView) findViewById(R.id.vHasil);

        Intent intent = getIntent();

        String addition = (String) intent.getSerializableExtra("jumlah");
        result.setText(addition);
    }
}


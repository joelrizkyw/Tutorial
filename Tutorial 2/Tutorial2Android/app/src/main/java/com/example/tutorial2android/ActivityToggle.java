package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

public class ActivityToggle extends Activity {
    ToggleButton tg1, tg2;
    Button b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_example);

        tg1 = (ToggleButton) findViewById(R.id.toggleButton);
        tg2 = (ToggleButton) findViewById(R.id.toggleButton2);

        b1 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();

                result.append("You have clicked first ON button : ").append(tg1.getText());
                result.append("You have clicked second ON button : ").append(tg2.getText());

                Toast.makeText(ActivityToggle.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

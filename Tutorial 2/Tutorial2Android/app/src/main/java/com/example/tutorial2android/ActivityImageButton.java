package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ActivityImageButton extends Activity {
    ImageButton img_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagebutton_example);

        img_btn = (ImageButton) findViewById(R.id.imgbtn);

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast message = Toast.makeText(getApplicationContext(), "Image has been clicked", Toast.LENGTH_LONG);
                message.show();
            }
        });
    }
}

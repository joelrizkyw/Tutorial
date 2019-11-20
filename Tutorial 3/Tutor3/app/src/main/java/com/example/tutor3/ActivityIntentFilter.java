package com.example.tutor3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityIntentFilter extends AppCompatActivity {
    private Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentfilter);

        btnSend = (Button) findViewById(R.id.sendMail);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abram@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Good Morning !");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi !, how are you ? I hope you're doing great !");

                startActivity(Intent.createChooser(intent, "Choose App To Open"));
            }
        });
    }
}



package com.example.tutor4preferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView nama;
    Button btn_keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (TextView) findViewById(R.id.nama_main);
        btn_keluar = (Button) findViewById(R.id.btn_logout);

        nama.setText(Preferences.getLoggedInUser(getBaseContext()));

        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoggedInUser(getBaseContext());

                Intent intent = new Intent(getBaseContext(), ActivityLogin.class);
                startActivity(intent);

                finish();
            }
        });
    }
}



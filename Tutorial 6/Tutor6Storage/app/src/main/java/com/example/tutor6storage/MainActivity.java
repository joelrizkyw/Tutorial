package com.example.tutor6storage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText fileName, fileData;
    Button btnSave, btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = findViewById(R.id.file_name);
        fileData = findViewById(R.id.isi_file);
        btnSave = findViewById(R.id.btn_save);
        btnLoad = findViewById(R.id.btn_load_ex);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String file_name = fileName.getText().toString();
                String data = fileData.getText().toString();

                FileOutputStream fos;

                try {
                    fos = openFileOutput(file_name, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(getApplicationContext(), file_name + " saved", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file_name = fileName.getText().toString();

                StringBuffer buffer = new StringBuffer();

                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(file_name)));

                    String inputString;

                    while((inputString = inputReader.readLine()) != null) {
                        buffer.append(inputString + "\n");
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }

                fileData.setText(buffer.toString());

                Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}




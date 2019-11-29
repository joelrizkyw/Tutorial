package com.example.tutor6storage;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityExternal extends AppCompatActivity {
    EditText inputText;
    TextView response;
    Button saveButton, loadButton;

    private String FILE_NAME = "SampleFile.txt";
    private String FILE_PATH = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        inputText = (EditText) findViewById(R.id.myInputText);
        response = (TextView) findViewById(R.id.response);
        saveButton = (Button) findViewById(R.id.btn_save_ex);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String isi_file = inputText.getText().toString();

                    FileOutputStream fos = new FileOutputStream(myExternalFile);

                    fos.write(isi_file.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                inputText.setText("");

                response.setText("SampleFile.txt saved to External Storage ....");
            }
        });

        loadButton = (Button) findViewById(R.id.btn_load_ex);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);

                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    String strLine;

                    while((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }

                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                inputText.setText(myData + "\n");
                response.setText("SampleFile.txt data retrieved from External Storage ....");
            }
        });

        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveButton.setEnabled(false);
        } else {
            myExternalFile = new File(getExternalFilesDir(FILE_PATH), FILE_NAME);
        }
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }

        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }

        return false;
    }
}

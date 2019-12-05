package com.example.tutor7sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ActivityAddStudents extends Activity implements View.OnClickListener {
    private Button addToDoBtn;
    private EditText kelasEditText;
    private EditText namaEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tambah Data");
        setContentView(R.layout.activity_addstudents);

        kelasEditText = (EditText) findViewById(R.id.kelas_editText);
        namaEditText = (EditText) findViewById(R.id.nama_editText);
        addToDoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();

        addToDoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_record :
                final String kelas = kelasEditText.getText().toString();
                final String nama = namaEditText.getText().toString();

                dbManager.insert(kelas, nama);

                Intent main = new Intent(ActivityAddStudents.this,
                        ActivityDataStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

                break;
        }
    }
}

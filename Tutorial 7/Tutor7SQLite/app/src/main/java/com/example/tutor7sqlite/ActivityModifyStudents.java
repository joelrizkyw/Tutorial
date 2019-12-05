package com.example.tutor7sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ActivityModifyStudents extends Activity implements View.OnClickListener {
    private EditText kelasText, namaText;
    private Button updateBtn, deleteBtn;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.activity_modifydata);

        dbManager = new DBManager(this);
        dbManager.open();

        kelasText = (EditText) findViewById(R.id.kelas_editText);
        namaText = (EditText) findViewById(R.id.nama_editText);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String kelas = intent.getStringExtra("kelas");
        String nama = intent.getStringExtra("nama");

        _id = Long.parseLong(id);

        kelasText.setText(kelas);
        namaText.setText(nama);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Pilihan update
            case R.id.btn_update :
                String kelas = kelasText.getText().toString();
                String nama =  namaText.getText().toString();

                dbManager.update(_id, kelas, nama);

                this.returnHome();

                break;

            //Pilihan delete
            case R.id.btn_delete :
                dbManager.delete(_id);
                this.returnHome();

                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                ActivityDataStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}

package com.example.androidsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddStudents extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText kelasEditText;
    private EditText namaEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tambah Data");
        setContentView(R.layout.activity_addstudents);
        kelasEditText = (EditText) findViewById(R.id.kelas_edittext);
        namaEditText = (EditText) findViewById(R.id.nama_edittext);
        addTodoBtn = (Button) findViewById(R.id.add_record);

        // Membuat objek dari kelas DBManager
        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                // Mengambil data inputan user
                final String kelas = kelasEditText.getText().toString();

                final String nama = namaEditText.getText().toString();

                /* Memanggil fungsi insert melalui objek dbManager
                dengan parameter nilai kelas dan nama */
                dbManager.insert(kelas, nama);

                // Memindahkan halaman kembali ke daftar siswa
                Intent main = new Intent(ActivityAddStudents.this, ActivityDataStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}

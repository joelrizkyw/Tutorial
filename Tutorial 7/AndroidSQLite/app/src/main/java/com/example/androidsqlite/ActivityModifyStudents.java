package com.example.androidsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityModifyStudents extends Activity implements View.OnClickListener {
    private EditText kelasText;
    private Button updateBtn, deleteBtn;
    private EditText namaText;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.activity_modifydata);
        dbManager = new DBManager(this);
        dbManager.open();
        kelasText = (EditText) findViewById(R.id.kelas_edittext);
        namaText = (EditText) findViewById(R.id.nama_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        /* Membuat objek Intent dengan nilai yang dikirim objek Intent
        yang telah memanggil kelas ini sebelumnya */
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
    public void onClick(View v) {
        switch (v.getId()) {
            // Pilihan Update
            case R.id.btn_update:
                // Menyimpan nilai kelas dan nama baru ke variabel
                String kelas = kelasText.getText().toString();
                String nama = namaText.getText().toString();

                /* Memanggil fungsi update melalui objek dbManager.
                Fungsi ini membawa tiga parameter yakni _id, kelas, nama */
                dbManager.update(_id, kelas, nama);

                /* Setelah selesai, akan memanggil fungsi returnHome()
                untuk kembali ke halaman utama */
                this.returnHome();
                break;

            //Pilihan Delete
            case R.id.btn_delete:
                // Memanggil fungsi delete dengan parameter _id
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ActivityDataStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}

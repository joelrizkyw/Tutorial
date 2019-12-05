package com.example.androidsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDataStudents extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.KELAS, DatabaseHelper.NAMA};
    final int[] to = new int[]{R.id.id, R.id.kelas, R.id.nama};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datastudents);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        /* Adapter untuk menunjuk data di database
        untuk ditampilkan dalam list siswa
        yang mana data tersebut menunjuk ke fragment dari ListView */
        adapter = new SimpleCursorAdapter(this, R.layout.activity_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner untuk data siswa yang telah ada di database
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Mengambil nilai list yang dipilih
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView kelasTextView = (TextView) view.findViewById(R.id.kelas);
                TextView namaTextView = (TextView) view.findViewById(R.id.nama);

                // Menyimpan nilai list yang dipilih ke variabel
                String id = idTextView.getText().toString();
                String kelas = kelasTextView.getText().toString();
                String nama = namaTextView.getText().toString();

                // Proses Intent untuk mengirim data ke halaman edit
                Intent modify_intent = new Intent(getApplicationContext(), ActivityModifyStudents.class);
                modify_intent.putExtra("kelas", kelas);
                modify_intent.putExtra("nama", nama);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, ActivityAddStudents.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }

}
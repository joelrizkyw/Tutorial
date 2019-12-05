package com.example.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    //Membuka koneksi database
    public DBManager open() throws SQLException {
        // dbHelper merupakan instance dari subclass SQLiteOpenHelper
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    //Menutup koneksi database
    public void close() {
        dbHelper.close();
    }

    // Query insert data
    public void insert(String kelas, String nama) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KELAS, kelas);
        contentValue.put(DatabaseHelper.NAMA, nama);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Query retrieve/read data
    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.KELAS, DatabaseHelper.NAMA};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Query update data
    public int update(long _id, String kelas, String nama) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KELAS, kelas);
        contentValues.put(DatabaseHelper.NAMA, nama);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    // Query delete data
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);

    }
}

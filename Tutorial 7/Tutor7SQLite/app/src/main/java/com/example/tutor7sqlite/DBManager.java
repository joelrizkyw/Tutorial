package com.example.tutor7sqlite;

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

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //Query untuk ambil / read data
    public Cursor fetch() {
        String[] columns = new String[] {DatabaseHelper.COL_ID,
                DatabaseHelper.COL_KELAS, DatabaseHelper.COL_NAMA};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    //Query insert data
    public void insert(String kelas, String nama) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.COL_KELAS, kelas);
        contentValue.put(DatabaseHelper.COL_NAMA, nama);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    //Query untuk update data
    public int update(long _id, String kelas, String nama) {
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COL_KELAS, kelas);
        cv.put(DatabaseHelper.COL_NAMA, nama);

        int i = database.update(DatabaseHelper.TABLE_NAME, cv,
                DatabaseHelper.COL_ID + " = " + _id, null);

        return i;
    }

    //Query untuk delete data
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COL_ID + " = " + _id, null);
    }
}

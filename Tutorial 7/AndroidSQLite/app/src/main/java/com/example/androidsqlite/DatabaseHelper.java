package com.example.androidsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nama tabel
    public static final String TABLE_NAME = "tbl_student";

    // Nama kolom dalam tabel
    public static final String _ID = "_id";
    public static final String KELAS = "kelas";
    public static final String NAMA = "nama";

    // Nama database
    static final String DB_NAME = "DataStudentDemo.db";

    // Versi database
    static final int DB_VERSION = 1;

    // Membuat query tabel
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KELAS + " TEXT NOT NULL, " + NAMA + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Override fungsi onCreate() dan onUpgrade()
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

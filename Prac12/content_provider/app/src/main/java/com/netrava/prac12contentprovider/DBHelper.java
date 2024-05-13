package com.witelokk.prac12contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "books";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";

    public DBHelper(Context context) {
        super(context, "books.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT)";
        String addBook1 = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_TITLE + ", " + COLUMN_AUTHOR + ") VALUES (\"Book1\", \"Author1\")";
        String addBook2 = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_TITLE + ", " + COLUMN_AUTHOR + ") VALUES (\"Book2\", \"Author2\")";

        db.execSQL(createTable);
        db.execSQL(addBook1);
        db.execSQL(addBook2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

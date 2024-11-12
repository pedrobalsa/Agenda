package com.example.projeto1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissosDBHelper extends SQLiteOpenHelper {

    public CompromissosDBHelper(Context context) {
        super(context, CompromissosDBSchema.DATABASE_NAME, null, CompromissosDBSchema.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CompromissosDBSchema.CompromissoTable.TABLE_NAME + " (" +
                CompromissosDBSchema.CompromissoTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CompromissosDBSchema.CompromissoTable.COLUMN_DESCRICAO + " TEXT, " +
                CompromissosDBSchema.CompromissoTable.COLUMN_DATA + " TEXT, " +
                CompromissosDBSchema.CompromissoTable.COLUMN_HORA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissoTable.TABLE_NAME);
        onCreate(db);
    }
}

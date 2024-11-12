package com.example.projeto1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaDBHelper extends SQLiteOpenHelper {

    public AgendaDBHelper(Context context) {
        super(context, AgendaDBSchema.DATABASE_NAME, null, AgendaDBSchema.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCompromissoTable = "CREATE TABLE " + AgendaDBSchema.CompromissoTable.TABLE_NAME + " (" +
                AgendaDBSchema.CompromissoTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AgendaDBSchema.CompromissoTable.COLUMN_DESCRICAO + " TEXT, " +
                AgendaDBSchema.CompromissoTable.COLUMN_DATA + " TEXT, " +
                AgendaDBSchema.CompromissoTable.COLUMN_HORA + " TEXT)";
        
        String createAgendaTable = "CREATE TABLE " + AgendaDBSchema.AgendaTable.TABLE_NAME + " (" +
                AgendaDBSchema.AgendaTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AgendaDBSchema.AgendaTable.COLUMN_NOME + " TEXT)";
        
        db.execSQL(createCompromissoTable);
        db.execSQL(createAgendaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AgendaDBSchema.CompromissoTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AgendaDBSchema.AgendaTable.TABLE_NAME);
        onCreate(db);
    }
}

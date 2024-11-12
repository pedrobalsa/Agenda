package com.example.projeto1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AgendaDB {
    private SQLiteDatabase db;

    public AgendaDB(Context context) {
        AgendaDBHelper dbHelper = new AgendaDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Métodos para compromissos
    public long inserirCompromisso(Compromisso compromisso) {
        ContentValues values = new ContentValues();
        values.put(AgendaDBSchema.CompromissoTable.COLUMN_DESCRICAO, compromisso.getDescricao());
        values.put(AgendaDBSchema.CompromissoTable.COLUMN_DATA, compromisso.getData());
        values.put(AgendaDBSchema.CompromissoTable.COLUMN_HORA, compromisso.getHora());
        return db.insert(AgendaDBSchema.CompromissoTable.TABLE_NAME, null, values);
    }

    public List<Compromisso> getCompromissosPorData(String data) {
        List<Compromisso> compromissos = new ArrayList<>();

        Cursor cursor = db.query(
                AgendaDBSchema.CompromissoTable.TABLE_NAME,
                null,
                AgendaDBSchema.CompromissoTable.COLUMN_DATA + " = ?",
                new String[]{data},
                null,
                null,
                AgendaDBSchema.CompromissoTable.COLUMN_HORA + " ASC"
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(AgendaDBSchema.CompromissoTable.COLUMN_DESCRICAO));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(AgendaDBSchema.CompromissoTable.COLUMN_HORA));
                Compromisso compromisso = new Compromisso(descricao, data, hora);
                compromissos.add(compromisso);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return compromissos;
    }

    // Métodos para agenda
    public long inserirAgenda(String nome) {
        ContentValues values = new ContentValues();
        values.put(AgendaDBSchema.AgendaTable.COLUMN_NOME, nome);
        return db.insert(AgendaDBSchema.AgendaTable.TABLE_NAME, null, values);
    }

    public List<String> getAgendas() {
        List<String> agendas = new ArrayList<>();
        Cursor cursor = db.query(AgendaDBSchema.AgendaTable.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(AgendaDBSchema.AgendaTable.COLUMN_NOME));
                agendas.add(nome);
            } while (cursor.moveToNext());
            cursor.close();
        }
        
        return agendas;
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}

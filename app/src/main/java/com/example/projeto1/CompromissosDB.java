package com.example.projeto1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class CompromissosDB {
    private SQLiteDatabase db;

    public CompromissosDB(Context context) {
        CompromissosDBHelper dbHelper = new CompromissosDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Método para inserir um novo compromisso
    public long inserirCompromisso(Compromisso compromisso) {
        ContentValues values = new ContentValues();
        values.put(CompromissosDBSchema.CompromissoTable.COLUMN_DESCRICAO, compromisso.getDescricao());
        values.put(CompromissosDBSchema.CompromissoTable.COLUMN_DATA, compromisso.getData());
        values.put(CompromissosDBSchema.CompromissoTable.COLUMN_HORA, compromisso.getHora());
        return db.insert(CompromissosDBSchema.CompromissoTable.TABLE_NAME, null, values);
    }

    // Método para consultar compromissos por data
    public List<Compromisso> getCompromissosPorData(String data) {
        List<Compromisso> compromissos = new ArrayList<>();

        Cursor cursor = db.query(
                CompromissosDBSchema.CompromissoTable.TABLE_NAME,
                null,
                CompromissosDBSchema.CompromissoTable.COLUMN_DATA + " = ?",
                new String[]{data},
                null,
                null,
                CompromissosDBSchema.CompromissoTable.COLUMN_HORA + " ASC"
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissoTable.COLUMN_DESCRICAO));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissoTable.COLUMN_HORA));
                Compromisso compromisso = new Compromisso(descricao, data, hora);
                compromissos.add(compromisso);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return compromissos;
    }

    // Método para fechar o banco de dados
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}

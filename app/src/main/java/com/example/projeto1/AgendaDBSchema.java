package com.example.projeto1;

public final class AgendaDBSchema {
    public static final String DATABASE_NAME = "agenda.db";
    public static final int DATABASE_VERSION = 1;

    private AgendaDBSchema() {}

    public static final class CompromissoTable {
        public static final String TABLE_NAME = "compromissos";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_HORA = "hora";
    }

    public static final class AgendaTable {
        public static final String TABLE_NAME = "agenda";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NOME = "nome";
    }
}

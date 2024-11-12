package com.example.projeto1;

public final class CompromissosDBSchema {
    public static final String DATABASE_NAME = "compromissos.db";
    public static final int DATABASE_VERSION = 1;

    private CompromissosDBSchema() {} 

    public static final class CompromissoTable {
        public static final String TABLE_NAME = "compromissos";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_HORA = "hora";
    }
}

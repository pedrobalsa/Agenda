package com.example.projeto1;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private static Agenda instance;
    private List<Compromisso> compromissos;

    private Agenda() {
        compromissos = new ArrayList<>();
    }

    public static Agenda getInstance() {
        if (instance == null) {
            instance = new Agenda();
        }
        return instance;
    }

    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
    }

    public List<Compromisso> getCompromissosPorData(String data) {
        List<Compromisso> compromissosNaData = new ArrayList<>();
        for (Compromisso compromisso : compromissos) {
            if (compromisso.getData().equals(data)) {
                compromissosNaData.add(compromisso);
            }
        }
        return compromissosNaData;
    }
}

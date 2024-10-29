package com.example.projeto1;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Agenda {
    private List<Compromisso> compromissos;

    public Agenda() {
        compromissos = new ArrayList<>();
    }

    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
    }

    public void removerCompromisso(Compromisso compromisso) {
        compromissos.remove(compromisso);
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
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

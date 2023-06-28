package br.com.unikedin.model;

import java.util.ArrayList;
import java.util.List;

public enum Campus {
    ANGICOS("Angicos"),
    CARAUBAS("Caraúbas"),
    MOSSORO("Mossoró"),
    PAUDOSFERROS("Pau dos Ferros");

    private String nomeCampus;

    Campus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }

    public String getNomeCampus() {
        return nomeCampus;
    }

    public static List<String> getNomesCampus() {
        List<String> nomes = new ArrayList<>();
        for (Campus campus : Campus.values()) {
            nomes.add(campus.getNomeCampus());
        }
        return nomes;
    }
}

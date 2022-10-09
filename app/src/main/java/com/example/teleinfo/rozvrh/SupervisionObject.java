package com.example.teleinfo.rozvrh;

public class SupervisionObject {

    public String Status, dozorCas, dozorMisto, udalostPopis, key;
    public int colspan;

    public SupervisionObject() {
    }

    public SupervisionObject(String Status, int colspan, String dozorCas, String dozorMisto, String udalostPopis, String key) {
        this.Status = Status;
        this.colspan = colspan;
        this.dozorCas = dozorCas;
        this.dozorMisto = dozorMisto;
        this.udalostPopis = udalostPopis;
        this.key = key;
    }
}










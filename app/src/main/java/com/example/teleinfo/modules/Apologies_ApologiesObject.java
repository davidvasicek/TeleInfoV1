package com.example.teleinfo.modules;

public class Apologies_ApologiesObject {

    public String key, studentName;
    public int id, Pritomen, Neritomen, Neomluveno, Nezapocitavana, Omluveno, Potvrzeno, CelkPocHod;

    public Apologies_ApologiesObject() {
    }

    public Apologies_ApologiesObject(int Pritomen, int Neritomen, int Neomluveno, int Nezapocitavana, int Omluveno, int Potvrzeno, int CelkPocHod, int id, String studentName, String key) {

        this.Pritomen = Pritomen;
        this.Neritomen = Neritomen;
        this.Neomluveno = Neomluveno;
        this.Nezapocitavana = Nezapocitavana;
        this.Omluveno = Omluveno;
        this.Potvrzeno = Potvrzeno;
        this.CelkPocHod = CelkPocHod;
        this.id = id;
        this.studentName = studentName;
        this.key = key;
    }
}










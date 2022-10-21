package com.example.teleinfo.modules;

public class Apologies_ApologiesObject {

    public String key, studentName;
    public int id, pocNepotvrzeno, pocPotvrzeno;

    public Apologies_ApologiesObject() {
    }

    public Apologies_ApologiesObject(int pocNepotvrzeno, int pocPotvrzeno, int id, String studentName, String key) {

        this.pocNepotvrzeno = pocNepotvrzeno;
        this.pocPotvrzeno = pocPotvrzeno;
        this.id = id;
        this.studentName = studentName;
        this.key = key;
    }
}










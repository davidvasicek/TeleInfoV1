package com.example.teleinfo.rozvrh;

public class HourObject {

    public String Cyklus, Datum, Den, Status, Trida, Ucebna, Ucitel, VyucHod, Zaci, neocekavanaUdalost, udalostPopis;
public int colspan;
    public HourObject() {
    }

    public HourObject(String Cyklus, String Datum, String Den, String Status, String Trida, String Ucebna, String Ucitel, String VyucHod, String Zaci, int colspan, String neocekavanaUdalost, String udalostPopis) {
        this.Cyklus = Cyklus;
        this.Datum = Datum;
        this.Den = Den;
        this.Status = Status;
        this.Trida = Trida;
        this.Ucebna = Ucebna;
        this.Ucitel = Ucitel;
        this.VyucHod = VyucHod;
        this.Zaci = Zaci;
        this.colspan = colspan;
        this.neocekavanaUdalost = neocekavanaUdalost;
        this.udalostPopis = udalostPopis;
    }
}










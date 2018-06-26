package com.example.studente.buynow;

public class Ordine {
    private String indirizzofatt, indirizzosped, corriere = "null", dataArrivo = "null", posizione = "null";
    private int codOrd, codCarr, codCarta_Sconto;
    private boolean carta_sconto;//TRUE CARTA FALSE BUONO SCONTO

    public Ordine(String indirizzofatt, String indirizzosped, int codOrd, int codCarr, int codCarta_Sconto, boolean carta_sconto) {
        this.indirizzofatt = indirizzofatt;
        this.indirizzosped = indirizzosped;
        this.codOrd = codOrd;
        this.codCarr = codCarr;
        this.codCarta_Sconto = codCarta_Sconto;
        this.carta_sconto = carta_sconto;
    }

    public Ordine(String indirizzofatt, String indirizzosped, String corriere, String dataArrivo, String posizione, int codOrd, int codCarr, int codCarta_Sconto, boolean carta_sconto) {
        this.indirizzofatt = indirizzofatt;
        this.indirizzosped = indirizzosped;
        this.corriere = corriere;
        this.dataArrivo = dataArrivo;
        this.posizione = posizione;
        this.codOrd = codOrd;
        this.codCarr = codCarr;
        this.codCarta_Sconto = codCarta_Sconto;
        this.carta_sconto = carta_sconto;
    }

    public String getIndirizzofatt() {
        return indirizzofatt;
    }

    public void setIndirizzofatt(String indirizzofatt) {
        this.indirizzofatt = indirizzofatt;
    }

    public String getIndirizzosped() {
        return indirizzosped;
    }

    public void setIndirizzosped(String indirizzosped) {
        this.indirizzosped = indirizzosped;
    }

    public String getCorriere() {
        return corriere;
    }

    public void setCorriere(String corriere) {
        this.corriere = corriere;
    }

    public String getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(String dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public int getCodOrd() {
        return codOrd;
    }

    public void setCodOrd(int codOrd) {
        this.codOrd = codOrd;
    }

    public int getCodCarr() {
        return codCarr;
    }

    public void setCodCarr(int codCarr) {
        this.codCarr = codCarr;
    }

    public int getCodCarta_Sconto() {
        return codCarta_Sconto;
    }

    public void setCodCarta_Sconto(int codCarta_Sconto) {
        this.codCarta_Sconto = codCarta_Sconto;
    }

    public String isCarta_sconto() {
        if (carta_sconto) {
            return "Carta";
        } else {
            return "Sconto";
        }
    }

    public void setCarta_sconto(boolean carta_sconto) {
        this.carta_sconto = carta_sconto;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "indirizzofatt='" + indirizzofatt + '\'' +
                ", indirizzosped='" + indirizzosped + '\'' +
                ", corriere='" + corriere + '\'' +
                ", dataArrivo='" + dataArrivo + '\'' +
                ", posizione='" + posizione + '\'' +
                ", codOrd=" + codOrd +
                ", codCarr=" + codCarr +
                ", codCarta_Sconto=" + codCarta_Sconto +
                ", carta_sconto=" + carta_sconto +
                '}';
    }
}

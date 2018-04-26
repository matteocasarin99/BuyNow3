package com.example.studente.buynow;

import java.io.Serializable;

/**
 * Created by Matteo on 19/03/2018.
 */

public class Prodotti implements Serializable{
    private String nome,descrizione,provenienza,ingredienti;
    private double prezzo;
    private double sconto;
    private int quantitàDisp;


    public Prodotti(String nome, String descrizione, String provenienza, double prezzo, double sconto, int quantitàDisp, String ingredienti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.provenienza = provenienza;
        this.prezzo = prezzo;
        this.sconto = sconto;
        this.quantitàDisp = quantitàDisp;
        this.ingredienti=ingredienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public int getQuantitàDisp() {
        return quantitàDisp;
    }

    public void setQuantitàDisp(int quantitàDisp) {
        this.quantitàDisp = quantitàDisp;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    @Override
    public String toString() {
        if(sconto==0){
            return "" + nome + ", Prezzo=" + prezzo +", Provenienza="+provenienza+ ", Quantità Disponibile=" + quantitàDisp;
        }else{
            return "" + nome + ", Prezzo=" + prezzo +", Provenienza="+provenienza+ ", Sconto="+sconto+ ", Quantità Disponibile=" + quantitàDisp;
        }

    }
}

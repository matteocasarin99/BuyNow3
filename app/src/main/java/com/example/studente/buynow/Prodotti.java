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
    private int id_prod;


    public Prodotti(String nome, String descrizione, String provenienza, double prezzo, double sconto, int quantitàDisp, String ingredienti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.provenienza = provenienza;
        this.prezzo = prezzo;
        this.sconto = sconto;
        this.quantitàDisp = quantitàDisp;
        this.ingredienti=ingredienti;
    }
    public Prodotti(int ID,String nome, String descrizione, String provenienza, double prezzo, double sconto, int quantitàDisp, String ingredienti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.provenienza = provenienza;
        this.prezzo = prezzo;
        this.sconto = sconto;
        this.quantitàDisp = quantitàDisp;
        this.ingredienti=ingredienti;
        this.id_prod=ID;
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

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }


    @Override
    public String toString() {
        return "Prodotti{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", provenienza='" + provenienza + '\'' +
                ", ingredienti='" + ingredienti + '\'' +
                ", prezzo=" + prezzo +
                ", sconto=" + sconto +
                ", quantitàDisp=" + quantitàDisp +
                ", id_prod=" + id_prod +
                '}';
    }
}

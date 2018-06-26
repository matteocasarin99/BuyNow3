package com.example.studente.buynow.Models;

import java.io.Serializable;

/**
 * Created by studente on 31/05/2018.
 */

public class Impostazioni implements Serializable{
    private String azione,descrizione,queryAzione;
    public Impostazioni(String azione,String descrizione){
        this.azione=azione;
        this.descrizione=descrizione;
    }
    public Impostazioni(String azione,String descrizione,String queryAzione){
        this.azione=azione;
        this.descrizione=descrizione;
        this.queryAzione=queryAzione;
    }
    public String getAzione() {
        return azione;
    }

    public void setAzione(String azione) {
        this.azione = azione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getQueryAzione() {
        return queryAzione;
    }

    public void setQueryAzione(String queryAzione) {
        this.queryAzione = queryAzione;
    }

    @Override
    public String toString() {
        return azione+"\n"+descrizione;
    }
}
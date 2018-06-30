package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class Rimuovi implements Callable<String> {
    private Utenti_Password ut;
    private int id_prod, id_ut;

    public Rimuovi(Utenti_Password ut, int idp, int idu) {
        this.ut = ut;
        id_prod = idp;
        id_ut = idu;
    }

    @Override
    public String call() throws Exception {
        String app = ut.rimuoviCart(id_prod, id_ut);
        return app;
    }
}

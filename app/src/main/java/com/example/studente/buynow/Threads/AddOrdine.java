package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class AddOrdine implements Callable<String> {
    private String app;
    private Utenti_Password ut;
    private Ordine o;

    public AddOrdine(Utenti_Password ut, Ordine o) {
        this.o = o;
        this.ut = ut;
    }

    @Override
    public String call() throws Exception {
        app = ut.addOrdine(o);
        return app;
    }
}

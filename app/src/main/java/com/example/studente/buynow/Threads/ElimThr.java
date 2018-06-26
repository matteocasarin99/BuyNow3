package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class ElimThr implements Callable<String> {
    private String app;
    private Utenti_Password ut;
    private int timeout, id;

    public ElimThr(Utenti_Password ut, int id) {
        timeout = 50;
        this.ut = ut;
    }

    @Override
    public String call() throws Exception {
        app = ut.eliminaUt(id);
        return app;
    }
}

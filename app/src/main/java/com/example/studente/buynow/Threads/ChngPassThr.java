package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class ChngPassThr implements Callable<Boolean> {
    private String app, pass, tipo;
    private Utenti_Password ut;
    private int timeout, id;

    public ChngPassThr(Utenti_Password ut, int id, String passNew, String tipo) {
        timeout = 50;
        this.ut = ut;
        pass = passNew;
        this.tipo = tipo;
    }

    @Override
    public Boolean call() throws Exception {
        return ut.cambio_Password(pass, id, tipo);
    }
}

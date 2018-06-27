package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Utente;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GetUtenti implements Callable<ArrayList<Utente>> {
    private Utenti_Password ut;
    private int timeout;
    private ArrayList<Utente> array;

    public GetUtenti() {
        timeout = 50;
        ut = new Utenti_Password();
    }

    @Override
    public ArrayList<Utente> call() throws Exception {
        array = ut.getUtenti();
        try {
            MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return array;
    }
}

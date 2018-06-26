package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GetProdotti implements Callable<ArrayList<Prodotti>> {
    private Utenti_Password ut;
    private int timeout;
    private ArrayList<Prodotti> array;

    public GetProdotti(Utenti_Password ut) {
        timeout = 50;
        this.ut = ut;
    }

    @Override
    public ArrayList<Prodotti> call() throws Exception {
        array = ut.getProdotti();
        try {
            MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return array;
    }
}

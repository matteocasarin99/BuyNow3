package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SearchProd implements Callable<ArrayList<Prodotti>> {
    private Utenti_Password ut;
    private int timeout;
    private String search;
    private ArrayList<Prodotti> array;

    public SearchProd(Utenti_Password ut, String search) {
        timeout = 50;
        this.ut = ut;
        this.search = search;
    }

    @Override
    public ArrayList<Prodotti> call() throws Exception {
        array = ut.searchProdotti(search);
        try {
            MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return array;
    }
}

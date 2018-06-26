package com.example.studente.buynow;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GetCarrello implements Callable<ArrayList<Prodotti>> {
    private Utenti_Password ut;
    private int timeout;
    private ArrayList<Prodotti> array;
    private int id;

    public GetCarrello(int idut) {
        timeout = 50;
        ut = new Utenti_Password();
        id = idut;
    }

    @Override
    public ArrayList<Prodotti> call() throws Exception {
        array = ut.getCarrello(id);
        try {
            MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return array;
    }
}

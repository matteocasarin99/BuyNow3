package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GetOrdini implements Callable<ArrayList<Ordine>> {
    private Utenti_Password ut;
    private int timeout;
    private int idA, idcart;

    public GetOrdini(int idUt, Utenti_Password ut, int idcart) {
        idA = idUt;
        this.ut = ut;
        timeout = 50;
        this.idcart = idcart;
    }

    @Override
    public ArrayList<Ordine> call() throws Exception {
        return ut.getOrdini(idA, idcart);
    }
}

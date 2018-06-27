package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class AddToCart implements Callable<String> {
    private Utenti_Password ut;
    private Prodotti p;
    private int idUt, quant;

    public AddToCart(Utenti_Password ut, Prodotti p, int idUt, int quant) {
        this.ut = ut;
        this.p = p;
        this.idUt = idUt;
        this.quant = quant;
    }

    @Override
    public String call() throws Exception {
        return ut.addToCart(p, idUt, quant);
    }
}

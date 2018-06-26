package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Prodotti;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Sum implements Callable<Double> {
    private ArrayList<Prodotti> array;

    public Sum(ArrayList<Prodotti> arrayList) {
        array = arrayList;
    }

    @Override
    public Double call() throws Exception {
        double tot = 0;
        for (int i = 0; i < array.size(); i++) {
            tot = tot + array.get(i).getPrezzo();
        }
        try {
            MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
        }
        return tot;
    }
}

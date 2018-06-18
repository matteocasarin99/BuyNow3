package com.example.studente.buynow;

import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ThreadRandom implements Callable<Integer> {
    private Utenti_Password ut = new Utenti_Password();
    private static int nth = 0;
    private final int id = ++nth;

    @Override
    public Integer call() throws Exception {
        boolean b = false;
        int value = new Random().nextInt(1000000);
        while (ut.controllo_codordine(value) == false) {
            value = new Random().nextInt(1000000);
            System.out.println(value + "");
        }
        try {
            MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
        }
        return value;
    }
}
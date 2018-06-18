package com.example.studente.buynow;

import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ThreadRandom implements Callable<Integer> {
    private Utenti_Password ut;
    private int timeout;

    public ThreadRandom() {
        timeout = 100;
        ut = new Utenti_Password();
    }

    @Override
    public Integer call() throws Exception {
        int value = new Random().nextInt(1000000);
        while (ut.controllo_codordine(value) == false) {
            value = new Random().nextInt(1000000);
            System.out.println(value + " AAAAAAAAAAAAAAAAAAAAA");
        }
        try {
            MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
        }
        return value;
    }
}
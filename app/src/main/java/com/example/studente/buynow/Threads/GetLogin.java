package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;

public class GetLogin implements Callable<String> {
    private int id;
    private String password, app, username;
    private Utenti_Password ut;
    private int timeout;

    public GetLogin(Utenti_Password ut, String user, String pass) {
        timeout = 50;
        this.ut = ut;
        username = user;
        password = pass;
    }

    @Override
    public String call() throws Exception {
        app = ut.search_utente(username, password);
        return app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

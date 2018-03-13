package com.example.studente.buynow;

/**
 * Created by studente on 13/03/2018.
 */

class Utente {
    private String nome_utente,cognome_utente,password,email;
    public Utente(String nome,String cognome,String pass,String mail){
        nome_utente=nome;
        cognome_utente=cognome;
        password=pass;
        email=mail;
    }

    public String getNome_utente() {
        return nome_utente;
    }

    public void setNome_utente(String nome_utente) {
        this.nome_utente = nome_utente;
    }

    public String getCognome_utente() {
        return cognome_utente;
    }

    public void setCognome_utente(String cognome_utente) {
        this.cognome_utente = cognome_utente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome_utente='" + nome_utente + '\'' +
                ", cognome_utente='" + cognome_utente + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

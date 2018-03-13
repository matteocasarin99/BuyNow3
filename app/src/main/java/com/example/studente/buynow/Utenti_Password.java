package com.example.studente.buynow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by studente on 13/03/2018.
 */

public class Utenti_Password implements Serializable{
    private ArrayList<Utente> array_utnorm=new ArrayList<Utente>();
    private ArrayList<Utente> array_utadmin=new ArrayList<Utente>();
    public Utenti_Password(){
        array_utadmin.add(new Utente("Matteo","Casarin","root","sonyxperiazcasa@gmail.com"));
        array_utadmin.add(new Utente("Admin","Stra","1234","admin@me.com"));
        array_utnorm.add(new Utente("Carlo","Albenga","sonogay","carlogay@icloud.com"));
        array_utnorm.add(new Utente("Alex","Pugnaghi","marcio","alex@icloud.com"));
    }
    public void addnewUtente(Utente e){
        array_utnorm.add(e);
    }
    public String search_utente(String nome,String pass){
        int i;
        String tipo="nessuno";
        for(i=0;i<array_utnorm.size();i++){
            if(nome.compareTo(array_utnorm.get(i).getNome_utente())==0 && pass.compareTo(array_utnorm.get(i).getPassword())==0){
                tipo="normale";
            }
        }
        for(i=0;i<array_utadmin.size();i++) {
            if (nome.compareTo(array_utadmin.get(i).getNome_utente()) == 0 && pass.compareTo(array_utadmin.get(i).getPassword()) == 0) {
                tipo = "root";
            }
        }
        return tipo;
    }
    public Utente getUtente(){
        return array_utnorm.get(0);
    }
}

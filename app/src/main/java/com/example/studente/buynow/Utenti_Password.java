package com.example.studente.buynow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by studente on 13/03/2018.
 */

public class Utenti_Password implements Serializable{
    private ArrayList<Utente> array_utnorm=new ArrayList<Utente>();
    private ArrayList<Utente> array_utadmin=new ArrayList<Utente>();
    private ArrayList<Prodotti> array_prodotti=new ArrayList<>();
    public Utenti_Password(){
        array_utadmin.add(new Utente("Matteo","Casarin","root","sonyxperiazcasa@gmail.com"));
        array_utadmin.add(new Utente("Admin","Stra","1234","admin@me.com"));
        array_utnorm.add(new Utente("Carlo","Albenga","sonogay","carlogay@icloud.com"));
        array_utnorm.add(new Utente("Alex","Pugnaghi","marcio","alex@icloud.com"));
        array_prodotti.add(new Prodotti("Caffè","Caffè miscela arabica 100% naturale e biologico","Perù",12.42,0,4));
        array_prodotti.add(new Prodotti("Cioccolata ","Cioccolato al latte naturale e biologico","Brasile",2.42,0,20));
        array_prodotti.add(new Prodotti("Tè Nero","Foglie di Tè Nero seccate al sole in bustina(4 per scatola)","India",5.50,0,6));
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

    public ArrayList<Prodotti> getArray_prodotti() {
        return array_prodotti;
    }
}

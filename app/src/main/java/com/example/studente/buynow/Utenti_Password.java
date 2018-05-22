package com.example.studente.buynow;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by studente on 13/03/2018.
 */

public class Utenti_Password implements Serializable {
    private static ArrayList<Utente> array_utnorm = new ArrayList<Utente>();
    private static ArrayList<Utente> array_utadmin = new ArrayList<Utente>();
    private static ArrayList<Prodotti> array_prodotti = new ArrayList<>();
    JSONObject obj;
    JSONArray array = new JSONArray();
    JsonParse jreader = new JsonParse();

    public Utenti_Password() {
        array_utadmin.add(new Utente("Matteo", "Casarin", "root", "sonyxperiazcasa@gmail.com"));
        array_utadmin.add(new Utente("Admin", "Stra", "1234", "admin@me.com"));
        array_utnorm.add(new Utente("Carlo", "Albenga", "sonogay", "carlogay@icloud.com"));
        array_utnorm.add(new Utente("Alex", "Pugnaghi", "marcio", "alex@icloud.com"));
        array_prodotti.add(new Prodotti("Caffè", "Caffè miscela arabica 100% naturale e biologico", "Perù", 12.42, 0, 4, "aaaaa"));
        array_prodotti.add(new Prodotti("Cioccolata", "Cioccolata al latte naturale e biologica", "Brasile", 2.42, 0, 20, "aaaaa"));
        array_prodotti.add(new Prodotti("Tè Nero", "Foglie di Tè Nero seccate al sole in bustina(4 per scatola)", "India", 5.50, 0, 6, "aaaaaa"));

    }

    public boolean addnewUtente(Utente e) {
        boolean b = false;
        try {
            System.out.println(e);
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=insert%20into%20utenti%20values(null,'" + e.getNome() + "','" + e.getPassword() + "','" + e.getCognome() + "','" + e.getEmail() + "');");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            b = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return b;
    }


    public String search_utente(String nome, String password) {
        String tipo = "nessuno";
        try {
            //UTENTI ROOT
            //DOWNLOAD JSON
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=select%20*%20from%20utenti_admin");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            //LETTURA DA JSON
            String nm, pass;
            array = jreader.responseJson(s);
            for (int i = 0; i < array.size(); i++) {
                obj = (JSONObject) array.get(i);
                nm = obj.get("nome").toString();
                pass = obj.get("password").toString();
                if (nm.compareTo(nome) == 0 && pass.compareTo(password) == 0) {
                    tipo = "root";
                }
            }
            //UTENTI NORMALI
            //DOWNLOAD JSON
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=select%20*%20from%20utenti");
            connection = (HttpURLConnection) url1.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            s = response.toString();
            //LETTURA DA JSON
            array = jreader.responseJson(s);
            for (int j = 0; j < array.size(); j++) {
                obj = (JSONObject) array.get(j);
                nm = obj.get("nome").toString();
                pass = obj.get("password").toString();
                System.out.println(nm);
                if (nm.compareTo(nome) == 0 && pass.compareTo(password) == 0) {
                    tipo = "standard";
                }
            }
            System.out.println(tipo);
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("\nDone");
        }
        return tipo;
    }
    //DA RIFARE!!
    public ArrayList<Prodotti> searchProdotti(String cerca) {
        ArrayList<Prodotti> array = new ArrayList<>();
        int i;
        for (i = 0; i < array_prodotti.size(); i++)
            if (array_prodotti.get(i).toString().contains(cerca)) {
                array.add(array_prodotti.get(i));
            }
        return array;
    }

    public ArrayList<Utente> getUtenti() {
        ArrayList<Utente> array_utenti = new ArrayList<Utente>();
        URL url1 = null;
        try {
            //DOWNLOAD JSON
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=select%20*%20from%20prodotti");

            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            //LETTURA UTENTI DA JSON
            String nome, cognome, email, password;
            int id;
            array = jreader.responseJson(s);
            for (int j = 0; j < array.size(); j++) {
                obj = (JSONObject) array.get(j);
                nome = obj.get("nome").toString();
                cognome = obj.get("cognome").toString();
                email = obj.get("email").toString();
                password = obj.get("password").toString();
                id = Integer.parseInt(obj.get("id_utente").toString());
                array_utenti.add(new Utente(id, nome, cognome, password, email));
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array_utenti;
    }

    public boolean addProdotto(Prodotti e) {
        boolean b = false;
        try {
            System.out.println(e);
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=insert%20into%20prodotti%20values(null,'" + e.getNome() + "','" + e.getDescrizione() + "','" + e.getPrezzo() + "','" + e.getSconto() + "','" + e.getIngredienti() + "','" + e.getProvenienza() + "','" + e.getQuantitàDisp() + "');");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            System.out.println(s);
            b = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return b;
    }

    public ArrayList<Prodotti> getProdotti() {
        ArrayList<Prodotti> array_prod = new ArrayList<Prodotti>();
        URL url1 = null;
        try {
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=select%20*%20from%20prodotti");

            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            String nome, descrizione, provenienza, ingredienti;
            double prezzo;
            double sconto;
            int quantitàDisp;
            int id;
            array = jreader.responseJson(s);
            for (int j = 0; j < array.size(); j++) {
                obj = (JSONObject) array.get(j);
                nome = obj.get("nomeProd").toString();
                descrizione = obj.get("descrizione").toString();
                provenienza = obj.get("provenienza").toString();
                ingredienti = obj.get("ingredienti").toString();
                prezzo = Double.parseDouble(obj.get("prezzo").toString());
                sconto = Double.parseDouble(obj.get("sconto").toString());
                quantitàDisp = Integer.parseInt(obj.get("quantitàDisp").toString());
                id = Integer.parseInt(obj.get("id_prod").toString());
                Prodotti p=new Prodotti(id, nome, descrizione, provenienza, prezzo, sconto, quantitàDisp, ingredienti);
                System.out.println(p+"ID: "+id);
                array_prod.add(p);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array_prod;
    }
}

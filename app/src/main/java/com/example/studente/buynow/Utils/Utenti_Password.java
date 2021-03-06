package com.example.studente.buynow.Utils;

import com.example.studente.buynow.Models.Impostazioni;
import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.Models.Utente;
import com.example.studente.buynow.Threads.AddToCart;
import com.example.studente.buynow.Threads.GetIDCart;
import com.example.studente.buynow.Utils.JsonParse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by studente on 13/03/2018.
 */

public class Utenti_Password implements Serializable {
    public static int id, idcart;
    public static String password;
    private static ArrayList<Utente> array_utnorm = new ArrayList<Utente>();
    private static ArrayList<Utente> array_utadmin = new ArrayList<Utente>();
    private static ArrayList<Prodotti> array_prodotti = new ArrayList<>();
    private ArrayList<Impostazioni> arraylistString = new ArrayList<Impostazioni>();
    private JSONObject obj;
    private JSONArray array = new JSONArray();
    private JsonParse jreader = new JsonParse();

    public Utenti_Password() {
        arraylistString.add(new Impostazioni("Elimina account", "Elimina l'account con cui si è connessi"));
        arraylistString.add(new Impostazioni("Cambia password", "Cambia la tua password di accesso"));
        arraylistString.add(new Impostazioni("Logout", "Esci dal tuo account"));
    }

    public boolean addnewUtente(Utente e) {
        boolean b = false;
        try {
            String risposta = "";
            System.out.println(e);
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=INSERT%20INTO%20`my_prova12344`.`utenti`%20(`id_utente`,%20`nome`,%20`password`,%20`cognome`,%20`email`)%20VALUES%20(NULL,%20'" + e.getNome() + "',%20'" + e.getPassword() + "',%20'" + e.getCognome() + "',%20'" + e.getEmail() + "');");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            b = risposta.compareTo("Comando Errato") != 0;
            connection.disconnect();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return b;
    }

    public String search_utente(String nome, String password) {
        String tipo = "nessuno";
        nome = nome.trim();
        password = password.trim();
        try {
            //QUERY UTENTI ROOT
            //DOWNLOAD JSON DI RISPOSTA DA FILE PHP
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
            //LETTURA ELENCO UTENTI DA RISPOSTA JSON
            String idS;
            String nm, pass;
            array = jreader.responseJson(s);
            for (int i = 0; i < array.size(); i++) {
                obj = (JSONObject) array.get(i);
                nm = obj.get("nome").toString();
                pass = obj.get("password").toString();
                if (nm.compareTo(nome) == 0 && pass.compareTo(password) == 0) {
                    tipo = "root";
                    id = Integer.parseInt(obj.get("id").toString());
                    this.password = pass;
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
                System.out.println(obj.toJSONString());
                nm = obj.get("nome").toString();
                pass = obj.get("password").toString();
                if (nm.compareTo(nome) == 0 && pass.compareTo(password) == 0) {
                    tipo = "standard";
                    id = Integer.parseInt(obj.get("id_utente").toString());
                    this.password = pass;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("\nDone");
        }
        return tipo;
    }

    public ArrayList<Prodotti> searchProdotti(String cerca) {
        ArrayList<Prodotti> array_prod = new ArrayList<Prodotti>();
        cerca = cerca.trim();
        URL url1 = null;
        try {
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=select%20*%20from%20prodotti%20where%20prodotti.nomeProd%20like%20'%" + cerca + "%'");

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
            int quantitaDisp;
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
                quantitaDisp = Integer.parseInt(obj.get("quantitaDisp").toString());
                id = Integer.parseInt(obj.get("id_prod").toString());
                Prodotti p = new Prodotti(id, nome, descrizione, provenienza, prezzo, sconto, quantitaDisp, ingredienti);
                array_prod.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return array_prod;
    }

    public ArrayList<Utente> getUtenti() {
        ArrayList<Utente> array_utenti = new ArrayList<Utente>();
        URL url1;
        try {
            //DOWNLOAD JSON
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=select%20*%20from%20utenti");

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return array_utenti;
    }

    public boolean addProdotto(Prodotti e) {
        boolean b = false;
        try {
            System.out.println(e);
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?query=INSERT%20INTO%20`my_prova12344`.`prodotti`%20(`id_prod`,%20`nomeProd`,%20`descrizione`,%20`prezzo`,%20`sconto`,%20`ingredienti`,%20`provenienza`,%20`quantitaDisp`)%20VALUES%20(NULL,%20'" + e.getNome() + "',%20'" + e.getDescrizione() + "',%20'" + e.getPrezzo() + "',%20'" + e.getSconto() + "',%20'" + e.getIngredienti() + "',%20'" + e.getProvenienza() + "',%20'" + e.getQuantitàDisp() + "')");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            String s = response.toString();
            b = true;
            connection.disconnect();
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
            int quantitaDisp;
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
                quantitaDisp = Integer.parseInt(obj.get("quantitaDisp").toString());
                id = Integer.parseInt(obj.get("id_prod").toString());
                Prodotti p = new Prodotti(id, nome, descrizione, provenienza, prezzo, sconto, quantitaDisp, ingredienti);
                array_prod.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return array_prod;
    }

    public ArrayList<Impostazioni> arraylist_settings() {
        return arraylistString;
    }

    public Impostazioni settings1() {
        return arraylistString.get(0);
    }

    public Impostazioni settings2() {
        return arraylistString.get(1);
    }

    public Impostazioni settings3() {
        return arraylistString.get(2);
    }

    public String cambio_Password(String newPassword, int id_utente, String tipo) {
        boolean c = false;
        URL url1 = null;
        if (tipo.compareTo("standard") == 0) {
            try {
                String risposta = "";
                url1 = new URL(
                        "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=update%20utenti%20set%20password='" + newPassword + "'%20where%20id_utente='" + id_utente + "';");

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
                obj = jreader.responseJSonInsert(s);
                risposta = obj.get("azione").toString();
                c = risposta.compareTo("Comando Errato") != 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                String risposta = "";
                url1 = new URL(
                        "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=update%20utenti_admin%20set%20password='" + newPassword + "'%20where%20id_utente='" + id_utente + "';");

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
                obj = jreader.responseJSonInsert(s);
                risposta = obj.get("azione").toString();
                c = risposta.compareTo("Comando Errato") != 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return c ? "Done" : "Error";
    }

    public String elimina(Prodotti prodotti) {
        boolean c = false;
        URL url1 = null;
        try {
            System.out.println(prodotti);
            String risposta = "";
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=delete%20from%20`prodotti`%20where%20id_prod='" + prodotti.getId_prod() + "';");

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
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            System.out.println(risposta);
            c = risposta.compareTo("Comando Errato") != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c ? "Done" : "Error";

    }

    public String eliminaUt(int idUt) {
        boolean c = false;
        URL url1 = null;
        try {
            String risposta = "";
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=delete%20from%20utenti%20where%20id_utente='" + idUt + "';");

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
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            System.out.println(risposta);
            c = risposta.compareTo("Comando Errato") != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c ? "Done" : "Error";
    }

    public ArrayList<Prodotti> getCarrello(int idutente) {
        ArrayList<Prodotti> arraypr = new ArrayList<Prodotti>();
        URL url1 = null;
        try {
            String risposta = "";
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=select%20prodotti.nomeProd,prodotti.descrizione,prodotti.ingredienti,prodotti.prezzo,prodotti.sconto,prodotti.id_prod,prodotti.provenienza,carrelli.quantitaOrd%20from%20prodotti,carrelli%20where%20carrelli.fk_idutente=" + idutente + "%20and%20prodotti.id_prod=carrelli.fk_idprodotto");
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
            System.out.println(s);
            String nome, descrizione, provenienza, ingredienti;
            double prezzo;
            double sconto;
            int id;
            int quantitaOrd;
            array = jreader.responseJson(s);
            for (int j = 0; j < array.size(); j++) {
                obj = (JSONObject) array.get(j);
                nome = obj.get("nomeProd").toString();
                descrizione = obj.get("descrizione").toString();
                provenienza = obj.get("provenienza").toString();
                ingredienti = obj.get("ingredienti").toString();
                prezzo = Double.parseDouble(obj.get("prezzo").toString());
                sconto = Double.parseDouble(obj.get("sconto").toString());
                id = Integer.parseInt(obj.get("id_prod").toString());
                quantitaOrd = Integer.parseInt(obj.get("quantitaOrd").toString());
                Prodotti p = new Prodotti(id, nome, descrizione, provenienza, prezzo, sconto, quantitaOrd, ingredienti);
                arraypr.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arraypr;
    }

    public String addToCart(Prodotti p, int idutente, int quant) {
        boolean c = false;
        URL url1;
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Integer> callable = new GetIDCart(idutente);
        Future<Integer> results = executor.submit(callable);
        try {
            idcart = results.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        try {
            String risposta;
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=INSERT%20INTO%20`my_prova12344`.`carrelli`%20(`id_carrello`,%20`fk_idutente`,%20`fk_idprodotto`,%20`quantitaOrd`)%20VALUES%20('" + idcart + "',%20'" + idutente + "',%20'" + p.getId_prod() + "',%20'" + quant + "');");

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
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            System.out.println(risposta + "AAAAAAAAAAAAAAAAAAAAA AAAAA");
            if (risposta.compareTo("Comando Errato") == 0) c = false;
            else c = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (c) return "Done";
        else return "Error";
    }

    public ArrayList<Ordine> getOrdini(int idut) {
        ArrayList<Ordine> arrayord = new ArrayList<Ordine>();
        URL url1;
        try {
            String risposta;
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=SELECT%20`id_ordine`,%20`fk_idcarrello`,%20`indirizzo_fatt`,%20`indirizzo_sped`,%20`corriere`,%20`posizione`,%20`dataConsegna`,%20`codiceSconto_Carta`,%20`Carta_Sconto`,%20`pagamento`%20from%20ordini,carrelli%20where%20ordini.fk_idcarrello=carrelli.id_carrello%20and%20carrelli.fk_idutente='" + idut + "';");
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
            String indfat, indsped, corriere, posizione, datacon, pagamento;
            int idord, idcarr;
            long codcart;
            boolean carta_sconto;
            System.out.println(s);
            array = jreader.responseJson(s);
            for (int j = 0; j < array.size(); j++) {
                obj = (JSONObject) array.get(j);
                indfat = obj.get("indirizzo_fatt").toString();
                indsped = obj.get("indirizzo_sped").toString();
                corriere = obj.get("corriere").toString();
                posizione = obj.get("posizione").toString();
                datacon = obj.get("dataConsegna").toString();
                pagamento = obj.get("pagamento").toString();
                idord = Integer.parseInt(obj.get("id_ordine").toString());
                idcarr = Integer.parseInt(obj.get("fk_idcarrello").toString());
                codcart = Long.parseLong(obj.get("codiceSconto_Carta").toString());
                if (obj.get("Carta_Sconto").toString().compareTo("Carta") == 0) {
                    carta_sconto = true;
                } else {
                    carta_sconto = false;
                }
                Ordine o = new Ordine(indfat, indsped, corriere, datacon, posizione, idord, idcarr, codcart, carta_sconto, pagamento);
                System.out.println(o);
                arrayord.add(o);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayord;
    }

    public String addOrdine(Ordine o) {
        boolean c = false;
        URL url1;
        try {
            String risposta;
            if (o.getPosizione().compareTo("default") == 0) {
                url1 = new URL(
                        "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=INSERT%20INTO%20`my_prova12344`.`ordini`%20(`id_ordine`,%20`fk_idcarrello`,%20`indirizzo_fatt`,%20`indirizzo_sped`,%20`corriere`,%20`posizione`,%20`dataConsegna`,%20`codiceSconto_Carta`,%20`Carta_Sconto`,%20`pagamento`)%20VALUES%20('" + o.getCodOrd() + "',%20'" + o.getCodCarr() + "',%20'" + o.getIndirizzofatt() + "',%20'" + o.getIndirizzosped() + "',%20" + o.getCorriere() + ",%20" + o.getPosizione() + ",%20" + o.getDataArrivo() + ",%20'" + o.getCodCarta_Sconto() + "',%20'" + o.isCarta_sconto() + "',%20" + o.getPagamento() + ");");
            } else {
                url1 = new URL(
                        "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=INSERT%20INTO%20`my_prova12344`.`ordini`%20(`id_ordine`,%20`fk_idcarrello`,%20`indirizzo_fatt`,%20`indirizzo_sped`,%20`corriere`,%20`posizione`,%20`dataConsegna`,%20`codiceSconto_Carta`,%20`Carta_Sconto`,%20`pagamento`)%20VALUES%20('" + o.getCodOrd() + "',%20'" + o.getCodCarr() + "',%20'" + o.getIndirizzofatt() + "',%20'" + o.getIndirizzosped() + "',%20'" + o.getCorriere() + "',%20'" + o.getPosizione() + "',%20'" + o.getDataArrivo() + "',%20'" + o.getCodCarta_Sconto() + "',%20'" + o.isCarta_sconto() + "',%20'" + o.getPagamento() + "');");
            }
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
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            System.out.println(risposta);
            if (risposta.compareTo("Comando Errato") == 0) c = false;
            else c = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (c) return "Done";
        else return "Error";
    }

    public boolean controllo_codordine(int codordine) {
        boolean b = true;
        String risposta = "";
        try {
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=select%20*%20from%20ordini");
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
            if (s.compareTo("") == 0) {
                b = true;
            } else {
                System.out.println(s);
                array = jreader.responseJson(s);
                for (int j = 0; j < array.size(); j++) {
                    obj = (JSONObject) array.get(j);
                    if (codordine == Integer.parseInt(obj.get("id_ordine").toString())) {
                        b = false;
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return b;
    }

    public String rimuoviCart(int id_prod, int id_ut, int idcart) {
        boolean c = false;
        URL url1 = null;
        try {
            String risposta = "";
            url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=delete%20from%20carrelli%20where%20id_carrello=" + idcart + "%20fk_idutente=" + id_ut + "%20and%20fk_idprodotto=" + id_prod + ";");

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
            obj = jreader.responseJSonInsert(s);
            risposta = obj.get("azione").toString();
            System.out.println(risposta + "ELIMINA PPRODDDDDDDD!!!!!!");
            c = risposta.compareTo("Comando Errato") != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c ? "Done" : "Error";
    }
}

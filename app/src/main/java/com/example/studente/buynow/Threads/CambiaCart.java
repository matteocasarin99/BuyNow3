package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.JsonParse;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Callable;

public class CambiaCart implements Callable<Integer> {
    private JsonParse jreader;
    private org.json.simple.JSONArray array;
    private JSONObject obj;
    private int codcart;

    public CambiaCart(int codcart) {
        this.codcart = codcart;
    }

    @Override
    public Integer call() throws Exception {
        int value = new Random().nextInt(1000000);
        while (controllo_codcart(value) == false) {
            value = new Random().nextInt(1000000);
        }

        return value;
    }

    /*
    public boolean chngCartN(){
        boolean b = true;
        String risposta = "";
        try {
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=UPDATE%20carrelli%20set%20id_carrello");
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
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }*/
    public boolean controllo_codcart(int codordine) {
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
                    if (codordine == Integer.parseInt(obj.get("id_carrello").toString())) {
                        b = false;
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return b;
    }
}
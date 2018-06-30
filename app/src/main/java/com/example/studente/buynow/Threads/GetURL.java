package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.Utils.JsonParse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class GetURL implements Callable<String> {
    private JSONObject obj;
    private JsonParse jreader = new JsonParse();
    private Prodotti p;

    public GetURL(Prodotti p) {
        this.p = p;
    }

    @Override
    public String call() throws Exception {
        String risposta = "https://yt3.ggpht.com/a-/ACSszfH4Lho4xr3MF9AnSvJWoBbrUJU2JJbll4Y6rQ=s900-mo-c-c0xffffffff-rj-k-no";
        try {
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/img.php?nomeimg=" + p.getNome() + "");

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
            risposta = obj.get("url").toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        return risposta;
    }
}

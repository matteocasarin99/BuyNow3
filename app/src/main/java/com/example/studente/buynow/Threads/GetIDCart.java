package com.example.studente.buynow.Threads;

import com.example.studente.buynow.Utils.JsonParse;
import com.example.studente.buynow.Utils.Utenti_Password;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import static com.example.studente.buynow.Utils.Utenti_Password.idcart;

public class GetIDCart implements Callable<Integer> {
    private int idut;
    private JsonParse jreader = new JsonParse();
    private org.json.simple.JSONArray array = new org.json.simple.JSONArray();
    private JSONObject obj;

    public GetIDCart(int idut) {
        this.idut = idut;
    }

    @Override
    public Integer call() throws Exception {
        return getIDCarrello(idut);
    }

    public int getIDCarrello(int idUt) {
        boolean b = true;
        try {
            URL url1 = new URL(
                    "http://prova12344.altervista.org/ProgettoEsame/login.php?&query=select%20id_carrello%20from%20carrelli%20where%20fk_idutente='" + idUt + "';");
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
                    idcart = Integer.parseInt(obj.get("id_carrello").toString());
                    System.out.println(idcart + " AAAAAAAAAAAAAAAAAAAA");
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return idcart;
    }
}

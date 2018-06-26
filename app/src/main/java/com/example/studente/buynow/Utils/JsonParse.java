package com.example.studente.buynow.Utils;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.Serializable;

public class JsonParse implements Serializable{

    public JsonParse() {

    }

    public JSONArray responseJson(String response) {
        JSONArray array = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response);
            array = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public JSONObject responseJSonInsert(String response){
        JSONObject obj=new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(response);
           obj = (JSONObject) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
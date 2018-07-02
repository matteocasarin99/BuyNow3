package com.example.studente.buynow.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studente.buynow.Adapters.AdapterJOrdini;
import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.GetIDCart;
import com.example.studente.buynow.Threads.GetOrdini;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Ordini extends AppCompatActivity {
    private ExecutorService executor;
    private Future<ArrayList<Ordine>> results;
    private ArrayList<Ordine> array;
    private AdapterJOrdini adapter;
    private int idUt;
    private Utenti_Password ut;
    private Future<Integer> results2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordini);
        idUt = (Integer) getIntent().getExtras().getSerializable("Id");
        ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        ListView list = findViewById(R.id.listOrd);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Integer> callable2 = new GetIDCart(idUt);
        Future<Integer> results2 = executor.submit(callable2);
        int idcart = 100;
        try {
            idcart = results2.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        executor = Executors.newFixedThreadPool(1);
        Callable<ArrayList<Ordine>> callable = new GetOrdini(idUt, ut, idcart);
        results = executor.submit(callable);
        try {
            System.out.println("computed result: " + results.get());
            array = results.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        adapter = new AdapterJOrdini(getApplicationContext(), array);
        list.setAdapter(adapter);
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Ordine o=(Ordine)parent.getItemAtPosition(position);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(Ordini.this,ShowCarrello.class);
                        i.putExtra("Utenti",ut);
                        i.putExtra("Ordine",o);
                        i.putExtra("IdUt",idUt);
                        i.putExtra("Password", (String)getIntent().getExtras().getSerializable("Password"));
                        startActivity(i);
                        finish();
                    }
                }, 500);
            }
        });*/

    }
}

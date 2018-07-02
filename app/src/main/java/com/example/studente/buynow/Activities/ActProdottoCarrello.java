package com.example.studente.buynow.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.GetIDCart;
import com.example.studente.buynow.Threads.GetURL;
import com.example.studente.buynow.Threads.Rimuovi;
import com.example.studente.buynow.Utils.Utenti_Password;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ActProdottoCarrello extends AppCompatActivity {
    private TextView nomep, descp, prezp, scontp, ingrp, quant;
    private Prodotti p;
    private ImageView img;
    private Utenti_Password ut;
    private int idUt;
    private ExecutorService executor;
    private Future<String> result;
    private String app;
    private Button rimuovi;

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(ActProdottoCarrello.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(ActProdottoCarrello.this, Accedi_Act.class);
                                i.putExtra("Utenti", ut);
                                i.putExtra("Id", idUt);
                                startActivity(i);
                                finish();
                            }
                        }, 300);
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // The user is not sure, so you can exit or just stay
                dialog.dismiss();
            }
        }).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idUt = (Integer) getIntent().getExtras().getSerializable("IdUt");
        p = (Prodotti) getIntent().getExtras().getSerializable("Prodotto");
        ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        executor = Executors.newFixedThreadPool(1);
        Callable<String> callable = new GetURL(p);
        result = executor.submit(callable);


        try {
            System.out.println("" + result.get());
            app = result.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        setContentView(R.layout.activity_act_prodottocar);
        img = findViewById(R.id.imgprod);
        Picasso.with(ActProdottoCarrello.this).load(app).into(img, new Callback() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

        //Dichiarazione
        nomep = findViewById(R.id.nomeProd);
        descp = findViewById(R.id.descrProd);
        prezp = findViewById(R.id.prezzoProd);
        scontp = findViewById(R.id.scontprod);
        ingrp = findViewById(R.id.ingrprod);
        quant = findViewById(R.id.QuantProd);
        rimuovi = findViewById(R.id.rimcart);
        //Inserimento
        nomep.setText(p.getNome());
        descp.setText(p.getDescrizione());
        ingrp.setText(p.getIngredienti());
        prezp.setText(p.getPrezzo() + "");
        scontp.setText(p.getSconto() + "");
        quant.setText(p.getQuantitàDisp() + "");
        rimuovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ActProdottoCarrello.this)
                        .setTitle("Attenzione")
                        .setMessage("Rimuovere il prodotto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ExecutorService executor2 = Executors.newFixedThreadPool(1);
                                Callable<Integer> callable2 = new GetIDCart(idUt);
                                Future<Integer> results2 = executor2.submit(callable2);
                                int idcart = 100;
                                try {
                                    idcart = results2.get();
                                } catch (Exception e) {
                                    System.out.println("Interrupted while waiting for result: "
                                            + e.getMessage());
                                }
                                executor = Executors.newFixedThreadPool(1);
                                Callable<String> callable = new Rimuovi(ut, idUt, p.getId_prod(), idcart);
                                result = executor.submit(callable);
                                try {
                                    System.out.println("" + result.get());
                                    app = result.get();
                                } catch (Exception e) {
                                    System.out.println("Interrupted while waiting for result: "
                                            + e.getMessage());
                                }
                                Context context = getApplicationContext();
                                CharSequence text = "Rimosso dal carrello";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(ActProdottoCarrello.this, Accedi_Act.class);
                                        i.putExtra("Utenti", ut);
                                        i.putExtra("Id", idUt);
                                        startActivity(i);
                                        finish();
                                    }
                                }, 1000);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user is not sure, so you can exit or just stay
                        dialog.dismiss();
                    }
                }).show();

            }
        });
    }
}

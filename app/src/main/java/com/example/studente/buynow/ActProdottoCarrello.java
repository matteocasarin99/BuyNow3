package com.example.studente.buynow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


public class ActProdottoCarrello extends AppCompatActivity {
    private TextView nomep, descp, prezp, scontp, ingrp, quant;
    private Prodotti p;
    private Utenti_Password ut;
    private int idUt;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prodottocar);
        idUt = (Integer) getIntent().getExtras().getSerializable("IdUt");
        p = (Prodotti) getIntent().getExtras().getSerializable("Prodotto");
        ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        //Dichiarazione
        nomep = findViewById(R.id.nomeProd);
        descp = findViewById(R.id.descrProd);
        prezp = findViewById(R.id.prezzoProd);
        scontp = findViewById(R.id.scontprod);
        ingrp = findViewById(R.id.ingrprod);
        quant = findViewById(R.id.QuantProd);
        //Inserimento
        nomep.setText(p.getNome());
        descp.setText(p.getDescrizione());
        ingrp.setText(p.getIngredienti());
        prezp.setText(p.getPrezzo() + "");
        scontp.setText(p.getSconto() + "");
        quant.setText(p.getQuantitàDisp() + "");

    }
}

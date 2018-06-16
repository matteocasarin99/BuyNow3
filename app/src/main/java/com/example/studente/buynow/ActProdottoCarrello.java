package com.example.studente.buynow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ActProdottoCarrello extends AppCompatActivity {
    private TextView nomep, descp, prezp, scontp, ingrp, quant;
    private Prodotti p;
    private Utenti_Password ut;
    private int idUt;

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

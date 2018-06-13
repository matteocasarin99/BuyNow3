package com.example.studente.buynow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ActProdotto extends AppCompatActivity {
    private Spinner spinner;
    private TextView nomep,descp,prezp,scontp,ingrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prodotto);
        Prodotti p=(Prodotti)getIntent().getExtras().getSerializable("Prodotto");
        //Dichiarazione
        nomep=findViewById(R.id.nomeProd);
        descp=findViewById(R.id.descrProd);
        prezp=findViewById(R.id.prezzoProd);
        scontp=findViewById(R.id.scontprod);
        ingrp=findViewById(R.id.ingrprod);
        spinner = findViewById(R.id.spinner);
        //Inserimento
        nomep.setText(p.getNome());
        descp.setText(p.getDescrizione());
        ingrp.setText(p.getIngredienti());
        prezp.setText(p.getPrezzo()+"");
        scontp.setText(p.getSconto()+"");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActProdotto.this,
                android.R.layout.simple_spinner_item,quant(p.getQuantitàDisp()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public ArrayList<String> quant(int quant){
        ArrayList<String> arrayInt=new ArrayList<String>();
        for(int i=1;i<=quant;i++){
            arrayInt.add(i+"");
        }
        return arrayInt;
    }
}

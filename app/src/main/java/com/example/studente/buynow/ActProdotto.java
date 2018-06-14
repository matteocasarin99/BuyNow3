package com.example.studente.buynow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActProdotto extends AppCompatActivity {
    private Spinner spinner;
    private TextView nomep,descp,prezp,scontp,ingrp;
    private Button btnAgg;
    private Prodotti p;
    private Utenti_Password ut;
    private int idUt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prodotto);
        idUt=(Integer)getIntent().getExtras().getSerializable("IdUt");
        p=(Prodotti)getIntent().getExtras().getSerializable("Prodotto");
        ut=(Utenti_Password)getIntent().getExtras().getSerializable("Utenti");
        //Dichiarazione
        nomep=findViewById(R.id.nomeProd);
        descp=findViewById(R.id.descrProd);
        prezp=findViewById(R.id.prezzoProd);
        scontp=findViewById(R.id.scontprod);
        ingrp=findViewById(R.id.ingrprod);
        spinner = findViewById(R.id.spinner);
        btnAgg=findViewById(R.id.btnAddCart);
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
        btnAgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ut.addToCart(p,idUt).compareTo("Error")==0){
                    Context context = getApplicationContext();
                    CharSequence text = "Error while adding to cart";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Inserito nel carrello";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
    public ArrayList<String> quant(int quant){
        ArrayList<String> arrayInt=new ArrayList<String>();
        for(int i=1;i<=quant;i++){
            arrayInt.add(i+"");
        }
        return arrayInt;
    }
}

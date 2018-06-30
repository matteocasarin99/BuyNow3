package com.example.studente.buynow.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.AddOrdine;
import com.example.studente.buynow.Threads.AddToCart;
import com.example.studente.buynow.Threads.ElimThr;
import com.example.studente.buynow.Threads.GetURL;
import com.example.studente.buynow.Utils.Utenti_Password;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ActProdotto extends AppCompatActivity {
    private Spinner spinner;
    private TextView nomep,descp,prezp,scontp,ingrp;
    private Button btnAgg;
    private String password;
    private Prodotti p;
    private Utenti_Password ut;
    private int idUt, quantVol;
    private ExecutorService executor;
    private Future<String> results;
    private String app = "Error";
    private ImageView img;
    private Future<String> result;

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(ActProdotto.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(ActProdotto.this, Accedi_Act.class);
                                i.putExtra("Utenti", ut);
                                i.putExtra("Id", idUt);
                                i.putExtra("Password", password);
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
        idUt=(Integer)getIntent().getExtras().getSerializable("IdUt");
        p=(Prodotti)getIntent().getExtras().getSerializable("Prodotto");
        ut=(Utenti_Password)getIntent().getExtras().getSerializable("Utenti");
        password = (String) getIntent().getExtras().getSerializable("Password");
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
        setContentView(R.layout.activity_act_prodotto);
        img = findViewById(R.id.imgca);
        Picasso.with(ActProdotto.this).load(app).into(img, new Callback() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });




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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantVol = Integer.parseInt((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executor = Executors.newFixedThreadPool(1);
                Callable<String> callable = new AddToCart(ut, p, idUt, quantVol);
                results = executor.submit(callable);
                try {
                    app = results.get();
                } catch (Exception e) {
                    System.out.println("Interrupted while waiting for result: "
                            + e.getMessage());
                }
                if (app.compareTo("Error") == 0) {
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

    private void loadImageFromUrl(String url) {
        Picasso.with(ActProdotto.this).load(url).into(img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}

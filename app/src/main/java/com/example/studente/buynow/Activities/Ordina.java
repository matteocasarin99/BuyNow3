package com.example.studente.buynow.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.ThreadRandom;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ordina extends AppCompatActivity {
    private int idCarr, checkedRadioButtonId, radn;
    private Utenti_Password ut;
    private String pass;
    private EditText textBuono_Carta, textIndFatt, textIndSped, textCVC;
    private TextView textCodOrd;
    private Button btnOrd;
    private RadioGroup radioGroup;
    private RadioButton radioCarta, radioSconto;
    private boolean carta_sconto = false;
    private Future<Integer> results;
    private ExecutorService executor;

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(Ordina.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(Ordina.this, Accedi_Act.class);
                                i.putExtra("Utenti", ut);
                                i.putExtra("Id", idCarr);
                                i.putExtra("Password", pass);
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
        executor = Executors.newFixedThreadPool(1);
        Callable<Integer> callable = new ThreadRandom();
        results = executor.submit(callable);
        try {
            System.out.println("computed result: " + results.get());
            radn = results.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordina);
        ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");

        idCarr = (Integer) getIntent().getExtras().getSerializable("IdUt");
        pass = Accedi_Act.password;
        radioGroup = findViewById(R.id.radioGroup);
        radioCarta = findViewById(R.id.radioCarta);
        radioSconto = findViewById(R.id.radioBuono);
        textBuono_Carta = findViewById(R.id.textCodice);
        textCodOrd = findViewById(R.id.nOrdine);
        textIndFatt = findViewById(R.id.indirizfatt);
        textIndSped = findViewById(R.id.indirizsped);
        btnOrd = findViewById(R.id.btnOrdina);
        textCVC = findViewById(R.id.textCVC);
        textCVC.setVisibility(View.INVISIBLE);
        textCodOrd.setText(radn + "");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    // No item selected
                } else {
                    if (checkedRadioButtonId == radioCarta.getId()) {
                        carta_sconto = true;
                        textCVC.setVisibility(View.VISIBLE);
                    } else {
                        if (checkedRadioButtonId == radioSconto.getId()) {
                            carta_sconto = false;
                            textCVC.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        });
        btnOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textBuono_Carta.getText().toString().compareTo("") == 0 || textIndSped.getText().toString().compareTo("") == 0 || textIndFatt.getText().toString().compareTo("") == 0 || checkedRadioButtonId == -1) {
                    Context context = getApplicationContext();
                    CharSequence text = "Compila i campi vuoti!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    Ordine o = new Ordine(textIndFatt.getText().toString(), textIndSped.getText().toString(), radn, idCarr, Integer.parseInt(textBuono_Carta.getText().toString()), carta_sconto);
                }
            }
        });


    }
}

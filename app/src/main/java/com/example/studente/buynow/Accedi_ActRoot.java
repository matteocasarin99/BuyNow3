package com.example.studente.buynow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Accedi_ActRoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaccia_root);
        Utenti_Password ut=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
    }
}

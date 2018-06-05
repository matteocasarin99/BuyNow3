package com.example.studente.buynow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CambioPassword extends AppCompatActivity {
    public Utenti_Password ut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_password);
        ut=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
    }
}

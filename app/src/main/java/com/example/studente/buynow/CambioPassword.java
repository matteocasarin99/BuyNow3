package com.example.studente.buynow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CambioPassword extends AppCompatActivity {
    public Utenti_Password ut;
    private int idUt;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_password);
        ut=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        idUt=(Integer)getIntent().getExtras().getSerializable("id");
        password=(String)getIntent().getExtras().getSerializable("pass");
    }
}

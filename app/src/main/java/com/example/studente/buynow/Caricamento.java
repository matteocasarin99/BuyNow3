package com.example.studente.buynow;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Caricamento extends AppCompatActivity {
    static Utenti_Password ut;
    int timeout=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caricamento);

        ut=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        String valoreAct=(String) getIntent().getExtras().getString("Act");
        if(valoreAct.compareTo("standard")==0){
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent i = new Intent(Caricamento.this, Accedi_Act.class);
                    i.putExtra("Utenti",ut);
                    startActivity(i);
                    finish();
                }
            }, timeout);

        }else{
            if(valoreAct.compareTo("root")==0){
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent i2 = new Intent(Caricamento.this, Accedi_ActRoot.class);
                        i2.putExtra("Utenti",ut);
                        startActivity(i2);
                        finish();
                    }
                }, timeout);

            }
        }

    }
}

package com.example.studente.buynow;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Caricamento extends AppCompatActivity {
    static Utenti_Password ut;
    int timeout=2000;
    int idUt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caricamento);
        idUt=(Integer)getIntent().getExtras().getSerializable("Id");
        System.out.println(idUt+"  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
                    i.putExtra("Id",idUt);
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
                        i2.putExtra("Id",idUt);
                        startActivity(i2);
                        finish();
                    }
                }, timeout);

            }
        }

    }
}

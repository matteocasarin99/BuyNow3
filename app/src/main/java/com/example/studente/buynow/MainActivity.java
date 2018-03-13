package com.example.studente.buynow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button accedi=(Button) findViewById(R.id.accedi);
        final EditText ut= findViewById(R.id.utente);
        final EditText pass=findViewById(R.id.password);
        final Utenti_Password a=new Utenti_Password();
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.search_utente(ut.getText().toString(),pass.getText().toString()).compareTo("normale")==0){
                    Intent i=new Intent(MainActivity.this,Accedi_Act.class);
                    startActivity(i);
                }else{
                    if(a.search_utente(ut.getText().toString(),pass.getText().toString()).compareTo("root")==0){
                        Intent i2=new Intent(MainActivity.this,Accedi_ActRoot.class);
                        startActivity(i2);
                    }else{
                        Context context = getApplicationContext();
                        CharSequence text = "Non hai un account!!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }

            }
        });
        Button registrati=(Button) findViewById(R.id.btnReg);
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,Reg_Act.class);
                startActivity(i2);
            }
        });
    }
}

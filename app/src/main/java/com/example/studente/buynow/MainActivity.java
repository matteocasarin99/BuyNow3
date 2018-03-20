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
    Utenti_Password a=new Utenti_Password();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getExtras()!=null){
            a=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        }

        Button accedi=(Button) findViewById(R.id.accedi);
        final EditText ut= findViewById(R.id.utente);
        final EditText pass=findViewById(R.id.password);
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ut.getText().toString().compareTo("")==0 || pass.getText().toString().compareTo("")==0){
                    Context context = getApplicationContext();
                    CharSequence text = "Compila i campi vuoti!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else {
                    if (a.search_utente(ut.getText().toString(), pass.getText().toString()).compareTo("normale") == 0) {
                        Intent i = new Intent(MainActivity.this, Accedi_Act.class);
                        i.putExtra("Utenti",a);
                        startActivity(i);
                    } else {
                        if (a.search_utente(ut.getText().toString(), pass.getText().toString()).compareTo("root") == 0) {
                            Intent i2 = new Intent(MainActivity.this, Accedi_ActRoot.class);
                            i2.putExtra("Utenti",a);
                            startActivity(i2);
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Non hai un account!!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                }

            }
        });
        Button registrati=(Button) findViewById(R.id.btnReg);
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,Reg_Act.class);
                i2.putExtra("Utenti",a);
                startActivity(i2);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("Utenti",a);
    }
}

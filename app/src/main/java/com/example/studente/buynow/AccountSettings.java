package com.example.studente.buynow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AccountSettings extends AppCompatActivity {
    public Utenti_Password ut;
    int idUt;
    String password;
    private AdapterRow2 adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ListView list = findViewById(R.id.listaa);
        ut = Accedi_Act.ut;
        idUt = (Integer) getIntent().getExtras().getSerializable("Id");
        adapter = new AdapterRow2(getApplicationContext(), ut.arraylist_settings());
        password = (String) getIntent().getExtras().getSerializable("Password");
        context=getApplicationContext();
        list.setAdapter(adapter);
        list.setFocusable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Impostazioni cancella = new Impostazioni("Elimina account", "Elimina l'account con cui si è connessi");
            Impostazioni cambia = new Impostazioni("Cambia password", "Cambia la tua password di accesso");
            Impostazioni esci = new Impostazioni("Logout", "Esci dal tuo account");

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                if (ut.settings1().equals(parent.getItemAtPosition(position))) {
                    AlertDialog.Builder miaAlert = new AlertDialog.Builder(AccountSettings.this);
                    miaAlert.setMessage("Eliminare l'account?");
                    miaAlert.setTitle("Attenzione!");

                    miaAlert.setCancelable(false);
                    miaAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    miaAlert.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.out.println(ut.eliminaUt(idUt));
                           if(ut.eliminaUt(idUt).compareTo("Error")==0){
                               Context context = getApplicationContext();
                               CharSequence text = "Error During Delete";
                               int duration = Toast.LENGTH_SHORT;
                               Toast toast = Toast.makeText(context, text, duration);
                               toast.show();
                               new Handler().postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Intent i=new Intent(AccountSettings.this,MainActivity.class);
                                       i.putExtra("Utenti",ut);
                                       startActivity(i);
                                       finish();
                                   }
                               }, 1000);
                           }
                        }
                    });



                    AlertDialog alert = miaAlert.create();
                    alert.show();
                } else {
                    if (ut.settings2().equals(parent.getItemAtPosition(position))) {
                        Intent i3 = new Intent(AccountSettings.this, CambioPassword.class);
                        i3.putExtra("Utenti", ut);
                        i3.putExtra("id", idUt);
                        i3.putExtra("pass", password);
                        startActivity(i3);

                    } else {
                        if (ut.settings3().equals(parent.getItemAtPosition(position))) {
                            System.out.println("QUIIIIIIIIIIII");
                            AlertDialog.Builder miaAlert2 = new AlertDialog.Builder(AccountSettings.this);
                            miaAlert2.setMessage("Uscire dall'account?");
                            miaAlert2.setTitle("Information");

                            miaAlert2.setCancelable(false);
                            miaAlert2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            miaAlert2.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Logout In Corso...";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent i=new Intent(AccountSettings.this,MainActivity.class);
                                                i.putExtra("Utenti",ut);
                                                startActivity(i);
                                                finish();
                                            }
                                        }, 1000);
                                    }

                            });
                            AlertDialog alert = miaAlert2.create();
                            alert.show();
                        }
                    }
                }
            }
        });
    }
}

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
import android.widget.ListView;
import android.widget.Toast;

import com.example.studente.buynow.Adapters.AdapterRow2;
import com.example.studente.buynow.Models.Impostazioni;
import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.ElimThr;
import com.example.studente.buynow.Threads.GetProdotti;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccountSettings extends AppCompatActivity {
    public Utenti_Password ut;
    int idUt;
    String password;
    private AdapterRow2 adapter;
    Context context;
    private String tipo = "", app;
    private ExecutorService executor;
    private Future<String> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ListView list = findViewById(R.id.listaa);
        tipo = (String) getIntent().getExtras().getSerializable("Tipo");
        if (tipo.compareTo("standard") == 0) {
            ut = Accedi_Act.ut;
        } else {
            ut = Accedi_ActRoot.ut;
        }
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
                            executor = Executors.newFixedThreadPool(1);
                            Callable<String> callable = new ElimThr(ut, idUt);
                            results = executor.submit(callable);
                            try {
                                app = results.get();
                            } catch (Exception e) {
                                System.out.println("Interrupted while waiting for result: "
                                        + e.getMessage());
                            }
                            if (app.compareTo("Error") == 0) {
                                Context context = getApplicationContext();
                                CharSequence text = "Error During Delete";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            } else {
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
                        i3.putExtra("tipo", tipo);
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
                                        }, 500);
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

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(AccountSettings.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (tipo.compareTo("standard") == 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(AccountSettings.this, Accedi_Act.class);
                                    i.putExtra("Utenti", ut);
                                    i.putExtra("Id", idUt);
                                    i.putExtra("Password", password);
                                    startActivity(i);
                                    finish();
                                }
                            }, 500);
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(AccountSettings.this, Accedi_ActRoot.class);
                                    i.putExtra("Utenti", ut);
                                    i.putExtra("Id", idUt);
                                    i.putExtra("Password", password);
                                    startActivity(i);
                                    finish();
                                }
                            }, 500);
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // The user is not sure, so you can exit or just stay
                dialog.dismiss();
            }
        }).show();

    }
}

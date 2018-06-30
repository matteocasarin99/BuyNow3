package com.example.studente.buynow.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.GetLogin;
import com.example.studente.buynow.Utils.InternetConnection;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    Utenti_Password a = new Utenti_Password();

    private ExecutorService executor;
    private Future<String> results;
    private String app;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            if (getIntent().getExtras() != null) {
                a = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
            }
            Button accedi = (Button) findViewById(R.id.accedi);
            final EditText ut = findViewById(R.id.utente);
            final EditText pass = findViewById(R.id.password);
            accedi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (InternetConnection.haveInternetConnection(getApplicationContext())) {
                        if (ut.getText().toString().compareTo("") == 0 || pass.getText().toString().compareTo("") == 0) {
                            Context context = getApplicationContext();
                            CharSequence text = "Compila i campi vuoti!!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            executor = Executors.newFixedThreadPool(1);
                            Callable<String> callable = new GetLogin(a, ut.getText().toString(), pass.getText().toString());
                            results = executor.submit(callable);
                            try {
                                app = results.get();
                            } catch (Exception e) {
                                System.out.println("Interrupted while waiting for result: "
                                        + e.getMessage());
                            }
                            if (app.compareTo("nessuno") != 0) {
                                Intent i = new Intent(MainActivity.this, Caricamento.class);
                                i.putExtra("Utenti", a);
                                i.putExtra("Act", app);
                                i.putExtra("Id", Utenti_Password.id);
                                i.putExtra("Password", Utenti_Password.password);
                                startActivity(i);
                                finish();
                            } else {
                                Context context = getApplicationContext();
                                CharSequence text = "Non hai un account!!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    } else {
                        if (!InternetConnection.haveInternetConnection(getApplicationContext())) {
                            Context context = getApplicationContext();
                            CharSequence text = "Non Sei Connesso ad Internet!!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                }
            });
        }
        Button registrati = (Button) findViewById(R.id.btnReg);
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, Reg_Act.class);
                i2.putExtra("Utenti", a);
                startActivity(i2);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Utenti", a);
    }

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Attenzione")
                .setMessage("Chiusura applicazione, sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        finish();
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

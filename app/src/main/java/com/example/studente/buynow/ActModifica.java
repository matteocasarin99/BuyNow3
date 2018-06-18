package com.example.studente.buynow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class ActModifica extends AppCompatActivity {
    private EditText nomeprod;
    private Utenti_Password ut;
    private Integer idUt;
    private String password;

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(ActModifica.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(ActModifica.this, Accedi_ActRoot.class);
                                i.putExtra("Utenti", ut);
                                i.putExtra("Id", idUt);
                                i.putExtra("Password", password);
                                startActivity(i);
                                finish();
                            }
                        }, 1000);
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // The user is not sure, so you can exit or just stay
                dialog.dismiss();
            }
        }).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica);
        ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        idUt = (Integer) getIntent().getExtras().getSerializable("Id");
        password = (String) getIntent().getExtras().getSerializable("Password");
    }
}

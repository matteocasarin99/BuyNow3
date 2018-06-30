package com.example.studente.buynow.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studente.buynow.Models.Utente;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Utils.Utenti_Password;

public class Reg_Act extends AppCompatActivity {
    Utenti_Password ut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.registrazione);
            ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");

            final EditText pass = findViewById(R.id.password);
            final EditText passcontr = findViewById(R.id.passwordContr);
            final EditText nome = findViewById(R.id.nomeReg);
            final EditText cognome = findViewById(R.id.cognomeReg);
            final EditText email = findViewById(R.id.emailReg);
            pass.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (pass.getText().toString().equals("") && pass.getText().toString().equals("")) {
                        pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                        passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                    }
                    if (passcontr.getText().toString().compareTo(pass.getText().toString()) == 0) {
                        pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                        passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                    } else {
                        pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                        passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                    }
                    passcontr.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (pass.getText().toString().equals("") && pass.getText().toString().equals("")) {
                                pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                                passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                            }
                            if (pass.getText().toString().compareTo(passcontr.getText().toString()) == 0) {
                                pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                                passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                            } else {
                                pass.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                                passcontr.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                            }
                        }
                    });
                }
            });
            Button regEff = findViewById(R.id.registrEff);
            regEff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nome.getText().toString().compareTo("") == 0 || cognome.getText().toString().compareTo("") == 0 || email.getText().toString().compareTo("") == 0) {
                        Context context = getApplicationContext();
                        CharSequence text = "Compila i campi vuoti!!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Utente e = new Utente(nome.getText().toString(), cognome.getText().toString(), pass.getText().toString(), email.getText().toString());
                        if (ut.addnewUtente(e)) {
                            Context context = getApplicationContext();
                            CharSequence text = "Registrato!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Intent i3 = new Intent(Reg_Act.this, MainActivity.class);
                            i3.putExtra("Utenti", ut);
                            startActivity(i3);
                            finish();
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "ERROR DURING REGISTRATION";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                    }

                }
            });

        }


    }

    @Override
    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(Reg_Act.this)
                .setTitle("Back to previous page")
                .setMessage("Sei sicuro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(Reg_Act.this, MainActivity.class);
                                i.putExtra("Utenti", ut);
                                startActivity(i);
                                finish();
                            }
                        }, 300);
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

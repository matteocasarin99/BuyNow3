package com.example.studente.buynow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AccountSettings extends AppCompatActivity {
    public Utenti_Password ut;
    int idUt;
    String password;
    private AdapterRow2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ListView list = findViewById(R.id.listaa);
        ut = Accedi_Act.ut;
        idUt = (Integer) getIntent().getExtras().getSerializable("Id");
        adapter = new AdapterRow2(getApplicationContext(), ut.arraylist_settings());
        password = (String) getIntent().getExtras().getSerializable("Password");
        list.setAdapter(adapter);
        list.setFocusable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Impostazioni cancella = new Impostazioni("Elimina account", "Elimina l'account con cui si è connessi");
            Impostazioni cambia = new Impostazioni("Cambia password", "Cambia la tua password di accesso");
            Impostazioni esci = new Impostazioni("Logout", "Esci dal tuo account");

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                if (ut.settings1().equals(parent.getItemAtPosition(position))) {
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                } else {
                    if (ut.settings2().equals(parent.getItemAtPosition(position))) {
                        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        Intent i3 = new Intent(AccountSettings.this, CambioPassword.class);
                        i3.putExtra("Utenti", ut);
                        i3.putExtra("id", idUt);
                        i3.putExtra("pass", password);
                        startActivity(i3);

                    } else {
                        if (ut.settings3().equals(parent.getItemAtPosition(position))) {
                            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                        }
                    }
                }
            }
        });
    }
}

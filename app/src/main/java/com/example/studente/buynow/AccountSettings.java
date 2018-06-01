package com.example.studente.buynow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class AccountSettings extends AppCompatActivity {
    private AdapterJRow2 adapter;
    public Utenti_Password ut;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ListView list=findViewById(R.id.listaa);
        ut = Accedi_Act.ut;
        adapter = new AdapterJRow2(getApplicationContext(),ut.arraylist_settings());
        list.setAdapter(adapter);
    }
}

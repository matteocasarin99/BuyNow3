package com.example.studente.buynow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class AccountSettings extends AppCompatActivity {
    private AdapterRow2 adapter;
    public Utenti_Password ut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        ListView list=findViewById(R.id.listaa);
        ut = Accedi_Act.ut;
        adapter = new AdapterRow2(getApplicationContext(),ut.arraylist_settings());
        //ArrayAdapter<Impostazioni> adapter;
        //adapter = new ArrayAdapter<Impostazioni>(getApplicationContext(),R.layout.rowsettings,ut.arraylist_settings());
        list.setAdapter(adapter);
        list.setFocusable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }
        });
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem =(String) parent.getItemAtPosition(position).toString();
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}

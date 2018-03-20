package com.example.studente.buynow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class TabFragment2 extends Fragment {
    private ArrayAdapter<Prodotti> adapter;
    public Utenti_Password ut;
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2, container, false);
        ImageButton btnCerca=v.findViewById(R.id.imgBtnCerca);
        EditText textCerca=v.findViewById(R.id.textCerca);
        ut=Accedi_Act.ut;
        btnCerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
}
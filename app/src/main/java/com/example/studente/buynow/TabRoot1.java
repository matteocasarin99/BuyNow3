package com.example.studente.buynow;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Matteo on 20/03/2018.
 */

public class TabRoot1 extends Fragment{
    public Utenti_Password ut;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab1root, container, false);
        ut=Accedi_ActRoot.ut;
        final EditText nomeProd=view.findViewById(R.id.txtNmPr);
        final EditText descProd=view.findViewById(R.id.txtDesc);
        final EditText txtPrez=view.findViewById(R.id.txtPrez);
        final EditText txtScon=view.findViewById(R.id.txtScon);
        final EditText txtProv=view.findViewById(R.id.txtProv);
        final EditText txtQuant=view.findViewById(R.id.txtQuant);
        Button btnAgg=view.findViewById(R.id.btnAgg);
        btnAgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ut.addProdotto(new Prodotti(nomeProd.getText().toString(),descProd.getText().toString(),txtProv.getText().toString(),Double.parseDouble(txtPrez.getText().toString()),Double.parseDouble(txtScon.getText().toString()),Integer.parseInt(txtQuant.getText().toString())));

            }
        });
        return view;
    }
}

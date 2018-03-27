package com.example.studente.buynow;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(nomeProd.getText().toString().compareTo("")==0 || descProd.getText().toString().compareTo("")==0 || txtPrez.getText().toString().compareTo("")==0 || txtProv.getText().toString().compareTo("")==0 || txtScon.getText().toString().compareTo("")==0 || txtQuant.getText().toString().compareTo("")==0){
                    Context context = view.getContext();
                    CharSequence text = "Compila i campi vuoti!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    ut.addProdotto(new Prodotti(nomeProd.getText().toString(),descProd.getText().toString(),txtProv.getText().toString(),Double.parseDouble(txtPrez.getText().toString()),Double.parseDouble(txtScon.getText().toString()),Integer.parseInt(txtQuant.getText().toString())));
                }
            }
        });
        return view;
    }
}

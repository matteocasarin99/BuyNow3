package com.example.studente.buynow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class TabFragment2 extends Fragment {
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list;
    public View v;
    private int idUt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.tab2, container, false);
        final EditText textCerca=v.findViewById(R.id.textCerca);
        ut=Accedi_Act.ut;
        idUt = Accedi_Act.idUt;
        list=v.findViewById(R.id.listCerca);
        textCerca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textCerca.getText().toString().compareTo("")==0) {
                    adapter = new AdapterJ(v.getContext(), ut.getProdotti());
                }else {
                    adapter = new AdapterJ(v.getContext(), ut.searchProdotti(textCerca.getText().toString()));
                }
                list.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable){}
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final Prodotti p = (Prodotti) parent.getItemAtPosition(position);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getActivity(), ActProdotto.class);
                        i.putExtra("Utenti", ut);
                        i.putExtra("Prodotto", p);
                        i.putExtra("IdUt", idUt);
                        i.putExtra("Password", Accedi_Act.password);
                        startActivity(i);
                        getActivity().finish();
                    }
                }, 500);
            }
        });
        return v;
    }
}
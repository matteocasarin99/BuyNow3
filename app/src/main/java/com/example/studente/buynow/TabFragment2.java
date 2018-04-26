package com.example.studente.buynow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class TabFragment2 extends Fragment {
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list;
    public View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.tab2, container, false);
        final EditText textCerca=v.findViewById(R.id.textCerca);
        ut=Accedi_Act.ut;
        list=v.findViewById(R.id.listCerca);
        textCerca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textCerca.getText().toString().compareTo("")==0) {
                    adapter = new AdapterJ(v.getContext(), ut.getArray_prodotti());
                }else {
                    adapter = new AdapterJ(v.getContext(), ut.searchProdotti(textCerca.getText().toString()));
                }
                list.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable){}
        });
        return v;
    }
}
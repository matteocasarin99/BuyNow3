package com.example.studente.buynow.Activities;

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
import android.widget.EditText;
import android.widget.ListView;

import com.example.studente.buynow.Adapters.AdapterJ;
import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Threads.GetProdotti;
import com.example.studente.buynow.Threads.SearchProd;
import com.example.studente.buynow.Utils.Utenti_Password;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TabFragment2 extends Fragment {
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list;
    public View v;
    private int idUt;
    private ExecutorService executor;
    private Future<ArrayList<Prodotti>> results;
    private ArrayList<Prodotti> array;

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

                    adapter = new AdapterJ(v.getContext(), getArrayProd());
                }else {
                    adapter = new AdapterJ(v.getContext(), searchProd(textCerca.getText().toString()));
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

    public ArrayList<Prodotti> getArrayProd() {
        executor = Executors.newFixedThreadPool(1);
        Callable<ArrayList<Prodotti>> callable = new GetProdotti(ut);
        results = executor.submit(callable);
        try {
            array = results.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        return array;
    }

    public ArrayList<Prodotti> searchProd(String search) {
        executor = Executors.newFixedThreadPool(1);
        Callable<ArrayList<Prodotti>> callable = new SearchProd(ut, search);
        results = executor.submit(callable);
        try {
            array = results.get();
        } catch (Exception e) {
            System.out.println("Interrupted while waiting for result: "
                    + e.getMessage());
        }
        return array;
    }
}
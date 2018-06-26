package com.example.studente.buynow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Matteo on 20/03/2018.
 */

public class TabFragment3 extends Fragment {
    View view;
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list;
    private int idUt;
    private Button btnAcquista;
    private Future<ArrayList<Prodotti>> results;
    private Future<Double> results2;
    private ExecutorService executor;
    private ArrayList<Prodotti> arrayList;
    private double tot;
    private ExecutorService executor2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab3, container, false);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            idUt = Accedi_Act.idUt;
            list = view.findViewById(R.id.listCarr);
            ut = Accedi_Act.ut;
            TextView totale = view.findViewById(R.id.txtTotale);

            executor = Executors.newFixedThreadPool(1);
            Callable<ArrayList<Prodotti>> callable = new GetCarrello(idUt);
            results = executor.submit(callable);
            try {
                System.out.println("computed result: " + results.get());
                arrayList = results.get();
            } catch (Exception e) {
                System.out.println("Interrupted while waiting for result: "
                        + e.getMessage());
            }

            executor2 = Executors.newFixedThreadPool(1);
            Callable<Double> callable2 = new Sum(arrayList);
            results2 = executor2.submit(callable2);
            try {
                System.out.println("computed result: " + results2.get());
                tot = results2.get();
            } catch (Exception e) {
                System.out.println("Interrupted while waiting for result: "
                        + e.getMessage());
            }
            totale.setText(tot + "");
            adapter = new AdapterJ(view.getContext(), arrayList);
            list.setAdapter(adapter);
            btnAcquista = view.findViewById(R.id.btnAcquista);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                    final Prodotti p = (Prodotti) parent.getItemAtPosition(position);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getActivity(), ActProdottoCarrello.class);
                            i.putExtra("Utenti", ut);
                            i.putExtra("Prodotto", p);
                            i.putExtra("IdUt", idUt);
                            startActivity(i);
                            getActivity().finish();
                        }
                    }, 500);
                }
            });
            btnAcquista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Ordina.class);
                    i.putExtra("Utenti", ut);
                    i.putExtra("IdUt", idUt);
                    startActivity(i);
                    getActivity().finish();
                }
            });
        }
        return view;
    }
}
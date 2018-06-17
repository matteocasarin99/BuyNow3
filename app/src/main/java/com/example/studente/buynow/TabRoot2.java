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
import android.widget.ListView;

/**
 * Created by Matteo on 20/03/2018.
 */

public class TabRoot2 extends Fragment{
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list2;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab2root, container, false);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            list2 = view.findViewById(R.id.listr);
            ut = Accedi_ActRoot.ut;
            adapter = new AdapterJ(view.getContext(), ut.getProdotti());
            list2.setAdapter(adapter);
            list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Prodotti p = (Prodotti) parent.getItemAtPosition(position);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getActivity(), ActModifica.class);
                            i.putExtra("Utenti", ut);
                            i.putExtra("Prodotto", p);
                            startActivity(i);
                            getActivity().finish();
                        }
                    }, 500);
                }
            });
        }
        return view;
    }
}

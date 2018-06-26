package com.example.studente.buynow.Activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.studente.buynow.Adapters.AdapterJUtente;
import com.example.studente.buynow.R;
import com.example.studente.buynow.Utils.Utenti_Password;

/**
 * Created by Matteo on 20/03/2018.
 */

public class TabRoot3 extends Fragment{
    private AdapterJUtente adapter;
    public Utenti_Password ut;
    private ListView list3;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab3root, container, false);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            list3 = view.findViewById(R.id.listut);
            ut = Accedi_ActRoot.ut;
            adapter = new AdapterJUtente(view.getContext(), ut.getUtenti());
            list3.setAdapter(adapter);
        }
        return view;
    }
}

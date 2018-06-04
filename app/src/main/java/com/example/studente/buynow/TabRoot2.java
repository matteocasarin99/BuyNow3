package com.example.studente.buynow;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        }
        return view;
    }
}

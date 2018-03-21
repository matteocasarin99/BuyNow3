package com.example.studente.buynow;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        EditText nomeProd=view.findViewById(R.id.txtNmPr);

        return view;
    }
}

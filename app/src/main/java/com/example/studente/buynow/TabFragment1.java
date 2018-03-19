package com.example.studente.buynow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by Matteo on 19/03/2018.
 */

public class TabFragment1 extends Fragment{
    private ArrayAdapter<Prodotti> adapter;
    private Utenti_Password ut;
    private ListView list;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab1, container, false);
        list=view.findViewById(R.id.list);
        /*adapter = new ArrayAdapter<Prodotti>(view.getContext(), R.layout.tab1, ut.getArray_prodotti());
        list.setAdapter(adapter);*/
        return view;
    }

    public void showList(Utenti_Password ut) {
        this.ut=ut;

    }
}

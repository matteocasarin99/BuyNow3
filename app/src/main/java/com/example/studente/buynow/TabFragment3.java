package com.example.studente.buynow;

import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Matteo on 20/03/2018.
 */

public class TabFragment3 extends Fragment {
    View view;
    private AdapterJ adapter;
    public Utenti_Password ut;
    private ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab3, container, false);

        return view;
    }
}
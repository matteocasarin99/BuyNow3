package com.example.studente.buynow;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by studente on 31/05/2018.
 */

public class AdapterJRow2 extends BaseAdapter{
    Context context;
    ArrayList<String> data;
    ArrayList<Impostazioni> data2;
    private static LayoutInflater inflater = null;
    public AdapterJRow2 (Context context, ArrayList<String> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /*public AdapterJRow2 (Context context, ArrayList<Impostazioni> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View vi=view;
        if (vi == null)
            vi = inflater.inflate(android.R.layout.simple_list_item_1, null);
        /*TextView txtAzione=vi.findViewById(R.id.Action);
        TextView desc=vi.findViewById(R.id.Desc);
        txtAzione.setText(data.get(i).getAzione());
        desc.setText(data.get(i).getDescrizione());*/
        return vi;
    }
}
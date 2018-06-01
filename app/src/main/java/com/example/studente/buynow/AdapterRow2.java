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

public class AdapterRow2 extends BaseAdapter{
    Context context;
    ArrayList<String> data;
    private static LayoutInflater inflater = null;
    public AdapterRow2 (Context context, ArrayList<String> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View vi=view;
        int i=0;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowsettings, null);
        TextView txtAzione=vi.findViewById(R.id.Action);
        TextView desc=vi.findViewById(R.id.Desc);
        txtAzione.setText(data.get(i));
        return vi;
    }
}

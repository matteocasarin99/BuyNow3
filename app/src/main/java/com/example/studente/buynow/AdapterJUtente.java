package com.example.studente.buynow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterJUtente extends BaseAdapter {
    Context context;
    ArrayList<Utente> data;
    private static LayoutInflater inflater = null;
    public AdapterJUtente(Context context, ArrayList<Utente> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.utente, null);
        TextView textNome=vi.findViewById(R.id.textViewNome);
        TextView textCognome=vi.findViewById(R.id.textViewCognome);
        TextView textEmail=vi.findViewById(R.id.textViewEmail);
        textNome.setText(data.get(position).getNome());
        textCognome.setText(data.get(position).getCognome());
        textEmail.setText(data.get(position).getEmail());
        return vi;
    }
}

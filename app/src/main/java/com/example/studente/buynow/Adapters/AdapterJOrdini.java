package com.example.studente.buynow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studente.buynow.Models.Ordine;
import com.example.studente.buynow.Models.Utente;
import com.example.studente.buynow.R;

import java.util.ArrayList;

public class AdapterJOrdini extends BaseAdapter {
    Context context;
    ArrayList<Ordine> data;
    private static LayoutInflater inflater = null;

    public AdapterJOrdini(Context context, ArrayList<Ordine> data) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowordine, null);
        TextView cod = vi.findViewById(R.id.CodOrdine);
        TextView pos = vi.findViewById(R.id.posizione);
        TextView pag = vi.findViewById(R.id.pagamento);
        TextView datac = vi.findViewById(R.id.dataconsegna);
        cod.setText(data.get(position).getCodOrd() + "");
        pos.setText(data.get(position).getPosizione() + "");
        pag.setText(data.get(position).getPagamento() + "");
        datac.setText(data.get(position).getDataArrivo());
        return vi;
    }
}

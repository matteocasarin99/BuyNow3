package com.example.studente.buynow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterJElimina extends BaseAdapter {
    Context context;
    ArrayList<Prodotti> data;
    private static LayoutInflater inflater = null;
    private Utenti_Password ut;
    public AdapterJElimina(Context context, ArrayList<Prodotti> data, Utenti_Password ut) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ut=ut;
    }
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
    public View getView(final int i, final View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowelimina, null);

        TextView text = vi.findViewById(R.id.text);
        TextView text2= vi.findViewById(R.id.textHeader);
        TextView textPrezlist=vi.findViewById(R.id.textPrezzoList);
        TextView textPrv=vi.findViewById(R.id.textProvenienza);
        TextView textQt=vi.findViewById(R.id.textViewQt);
        Button elimina=vi.findViewById(R.id.buttonelimina);

        textPrv.setText("Provenienza: "+data.get(i).getProvenienza());
        text.setText(data.get(i).getDescrizione());
        text2.setText(data.get(i).getNome());
        textPrezlist.setText(data.get(i).getPrezzo()+"");
        textQt.setText(data.get(i).getQuantitàDisp()+"");

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ut.elimina(data.get(i)).compareTo("Error")==0){
                    Context context = view.getContext();
                    CharSequence text = "ERROR DURING REGISTRATION";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        return vi;
    }
}

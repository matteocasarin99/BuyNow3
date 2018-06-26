package com.example.studente.buynow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studente.buynow.Models.Prodotti;
import com.example.studente.buynow.R;

import java.util.ArrayList;

public class AdapterJ extends BaseAdapter {
    Context context;
    ArrayList<Prodotti> data;
    private static LayoutInflater inflater = null;

    public AdapterJ(Context context, ArrayList<Prodotti> data) {
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
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);

        TextView text = vi.findViewById(R.id.text);
        TextView text2= vi.findViewById(R.id.textHeader);
        TextView textPrezlist=vi.findViewById(R.id.textPrezzoList);
        TextView textPrv=vi.findViewById(R.id.textProvenienza);
        TextView textQt=vi.findViewById(R.id.textViewQt);

        ImageView img=vi.findViewById(R.id.imgMaterial);
        ImageView imgBio=vi.findViewById(R.id.imgBiologico);

        textPrv.setText("Provenienza: "+data.get(i).getProvenienza());
        text.setText(data.get(i).getDescrizione());
        text2.setText(data.get(i).getNome());
        textPrezlist.setText(data.get(i).getPrezzo()+"");
        textQt.setText(data.get(i).getQuantitàDisp()+"");

        imgBio.setImageResource(R.drawable.icons8_cibo);
        if(data.get(i).getNome().contains("Caffe")) {
            img.setImageResource(R.drawable.icons8_coffe);
        }else {
            if (data.get(i).getNome().contains("Cioccolata")) {
                img.setImageResource(R.drawable.icons8_cioccolata);
            } else {
                if (data.get(i).getNome().contains("Banane")) {
                    img.setImageResource(R.drawable.icons8_banana_24);
                } else {
                    if (data.get(i).getNome().contains("Te")) {
                        img.setImageResource(R.drawable.icons8_tea24);
                    }else{
                        if (data.get(i).getNome().contains("Bevanda")|| data.get(i).getNome().contains("Bibite")) {
                            img.setImageResource(R.drawable.icons8_acuqa);
                        }
                    }
                }
            }
        }


        return vi;
    }
}

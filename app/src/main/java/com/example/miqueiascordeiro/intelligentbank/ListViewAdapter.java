package com.example.miqueiascordeiro.intelligentbank;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Miqueias Cordeiro on 17/05/2017.
 */
public class ListViewAdapter extends ArrayAdapter<deposito> {
    private LayoutInflater mInflater;
    private ArrayList<deposito> depositos;
    private int mViewResourceID;


    public ListViewAdapter(Context context, int textViewResourceID, ArrayList<deposito> depositos) {
        super(context, textViewResourceID, depositos);
        this.depositos = depositos;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mViewResourceID = textViewResourceID;
    }

    public void ListaDeposito(){
        for (int i=0;i<depositos.size();i++){
            System.out.println(depositos.get(i).getData() + " " + depositos.get(i).getHora()+" "+ depositos.get(i).getDescricao()+" "+
            depositos.get(i).getValor());

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceID, null);

        deposito deps = depositos.get(position);

        if (deps != null) {
            TextView firstName = (TextView) convertView.findViewById(R.id.textData);
            TextView lastName = (TextView) convertView.findViewById(R.id.textHora);
            TextView favFood = (TextView) convertView.findViewById(R.id.textDescricao);
            TextView QuatrName = (TextView) convertView.findViewById(R.id.textValor);

 //           this.ListaDeposito();

            if (firstName != null) {
                firstName.setText((deps.getData()));

            }
            if (lastName != null) {
                lastName.setText((deps.getHora()));
            }
            if (favFood != null) {
                favFood.setText((deps.getDescricao()));
            }
            if (QuatrName != null) {
                QuatrName.setText((deps.getValor()));
            }
        }
        return convertView;
    }
}
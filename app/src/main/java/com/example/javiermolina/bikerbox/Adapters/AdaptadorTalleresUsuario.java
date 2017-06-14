package com.example.javiermolina.bikerbox.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

/**
 * Created by Javi on 14/06/2017.
 */

public class AdaptadorTalleresUsuario extends ArrayAdapter<Taller> {
    Taller[] lista;

    public AdaptadorTalleresUsuario(Context context, Taller[] lista) {
        super(context, R.layout.list_item_motos_perfil_usuario, lista);
        this.lista = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item_talleres, null);

        TextView nombre = (TextView) item.findViewById(R.id.txtNombre);
        nombre.setText(lista[position].getNombre());

        return item;
    }
}
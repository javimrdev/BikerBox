package com.example.javiermolina.bikerbox.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.javiermolina.bikerbox.Models.Anuncio;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.R;

/**
 * Created by Javi on 11/06/2017.
 */

public class AdaptadorAnunciosPerfil extends ArrayAdapter<Anuncio> {
        Anuncio[] lista;

    public AdaptadorAnunciosPerfil(Context context, Anuncio[] lista){
        super(context, R.layout.list_item_anuncios, lista);
        this.lista=lista;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item_anuncios, null);

        //ImageView imagen = (ImageView)item.findViewById(R.id.profile_image);
        //HelperValidacion.bytesToImage(listaMotos[position].);

        TextView marca = (TextView)item.findViewById(R.id.txtMarcaAnuncio);
        marca.setText(lista[position].getMarca());

        TextView modelo = (TextView)item.findViewById(R.id.txtModeloAnuncio);
        modelo.setText(lista[position].getModelo());

        TextView precio = (TextView)item.findViewById(R.id.txtPrecioAnuncio);
        precio.setText(String.valueOf(lista[position].getPrecio())+"€");

        TextView km = (TextView)item.findViewById(R.id.txtKmAnuncio);
        km.setText(String.valueOf(lista[position].getKm())+"Kms");

        return item;
    }
}

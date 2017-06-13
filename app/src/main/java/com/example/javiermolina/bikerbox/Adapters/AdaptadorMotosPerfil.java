package com.example.javiermolina.bikerbox.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javiermolina.bikerbox.Helper.HelperValidacion;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.R;

/**
 * Created by Javi on 06/06/2017.
 */

public class AdaptadorMotosPerfil extends ArrayAdapter<Moto>{
    Moto[] lista;
    public AdaptadorMotosPerfil(Context context, Moto[] lista){
        super(context, R.layout.list_item_motos_perfil_usuario, lista);
        this.lista=lista;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item_motos_perfil_usuario, null);

        //ImageView imagen = (ImageView)item.findViewById(R.id.profile_image);
        //HelperValidacion.bytesToImage(listaMotos[position].);

        TextView marca = (TextView)item.findViewById(R.id.txtMarca);
        marca.setText(lista[position].getMarca());

        TextView modelo = (TextView)item.findViewById(R.id.txtModelo);
        modelo.setText(lista[position].getModelo());

       /* TextView km = (TextView)item.findViewById(R.id.txtKm);
        modelo.setText(lista[position].getKm());

        <TextView
            android:id="@+id/txtKm"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:textSize="30dp"
            android:gravity="center_vertical"/>*/

        return item;
    }
}

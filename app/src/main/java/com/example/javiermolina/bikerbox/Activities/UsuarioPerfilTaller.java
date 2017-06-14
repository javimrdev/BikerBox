package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

public class UsuarioPerfilTaller extends AppCompatActivity {

    private TextView txtNombre;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_perfil_taller);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcionTallerUsuario);
        txtNombre = (TextView)findViewById(R.id.txtNombreTallerUsuario);

        Intent intent = getIntent();
        Taller taller = (Taller) intent.getSerializableExtra("taller");
        txtNombre.setText(taller.getNombre());
        txtDescripcion.setText(taller.getDescripcion());
    }
}

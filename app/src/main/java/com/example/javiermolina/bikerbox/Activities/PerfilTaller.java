package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

import static android.R.attr.bitmap;

public class PerfilTaller extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtDescripcion;
    private EditText edtLocalidad;
    private Button btnImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_taller);
        Intent intent = getIntent();
        Taller taller = intent.getParcelableExtra("taller");
        edtNombre.setHint(taller.getNombre());
        edtDescripcion.setHint(taller.getDescripcion());
        edtLocalidad.setHint(taller.getLocalidad());

    }
}

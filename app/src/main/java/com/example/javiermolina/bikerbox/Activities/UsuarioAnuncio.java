package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.javiermolina.bikerbox.Models.Anuncio;
import com.example.javiermolina.bikerbox.R;

public class UsuarioAnuncio extends AppCompatActivity {
    private TextView txtMarca;
    private TextView txtModelo;
    private TextView txtKm;
    private TextView txtColor;
    private TextView txtCilindrada;
    private TextView txtLocalidad;
    private TextView txtDescripcion;
    private TextView txtAno;
    private TextView txtPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_anuncio);
        txtMarca = (TextView)findViewById(R.id.txtMarcaAnuncioFinal);
        txtModelo = (TextView)findViewById(R.id.txtModeloAnuncioFinal);
        txtAno = (TextView)findViewById(R.id.txtAnoAnuncioFinal);
        txtCilindrada = (TextView)findViewById(R.id.txtCilindradaAnuncioFinal);
        txtColor = (TextView)findViewById(R.id.txtColorAnuncioFinal);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcionAnuncioFinal);
        txtKm = (TextView)findViewById(R.id.txtKmAnuncioFinal);
        txtLocalidad = (TextView)findViewById(R.id.txtLocalidadAnuncioFinal);
        txtPrecio = (TextView)findViewById(R.id.txtPrecioAnuncioFinal);
        Intent intent = getIntent();
        Anuncio a = (Anuncio)intent.getSerializableExtra("anuncio");

        txtMarca.setText(a.getMarca());
        txtModelo.setText(a.getModelo());
        txtAno.setText("Año: "+a.getAno());
        txtCilindrada.setText("Cilindrada: "+a.getCilindrada()+"cc");
        txtColor.setText("Color: "+a.getColor());
        txtDescripcion.setText("Descripcion: "+a.getDescripcion());
        txtKm.setText(a.getKm()+" Kms");
        txtLocalidad.setText("Localidad: "+a.getLocalidad());
        txtPrecio.setText("Precio: "+a.getPrecio()+"€");
    }
}

package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

import static android.R.attr.bitmap;

public class PerfilTaller extends AppCompatActivity{

    private CardView img;
    private EditText edtNombre;
    private EditText edtDescripcion;
    private Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_taller);
        btnGuardar = (Button)findViewById(R.id.btnGuardarTaller);
        edtNombre = (EditText)findViewById(R.id.edtNombreTallerModificar);
        edtDescripcion = (EditText)findViewById(R.id.edtDescripcionTallerModificacion);

        Intent intent = getIntent();
        final Taller taller = (Taller) intent.getSerializableExtra("taller");
        Log.e("asdad",taller.toString());
        edtNombre.setText(taller.getNombre());
        edtDescripcion.setText(taller.getDescripcion());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarTaller(taller.getId(),edtNombre.getText().toString(),
                        edtDescripcion.getText().toString().replaceAll(" ","%20"));
                finish();
            }
        });
    }

    private void modificarTaller(int id, String nombre, String descripcion) {
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp() + "taller/modificarTaller/"+
            id+"/"+nombre+"/"+descripcion);
        get.setHeader("content-type", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("asda",respStr);
                    if (respStr.equals("true")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Taller modificado", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        finish();
    }
}

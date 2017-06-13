package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.R;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class UsuarioModificarMoto extends AppCompatActivity {

    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtMatricula;
    private EditText edtColor;
    private EditText edtKm;
    private EditText edtCilindrada;
    private EditText edtAno;
    private Spinner spEstilo;
    private ArrayAdapter<String> adapter;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_modificar_moto);
        String estilos[] = {"Naked","R","Cafe Racer","Trail","Enduro","Trial","Custom"};
        Intent intent = getIntent();
        final Moto m = (Moto)intent.getSerializableExtra("moto");
        edtMarca = (EditText)findViewById(R.id.edtModificarMarcaMoto);
        edtModelo = (EditText)findViewById(R.id.edtModificarModeloMoto);
        edtMatricula = (EditText)findViewById(R.id.edtModificarMatriculaMoto);
        spEstilo = (Spinner)findViewById(R.id.spModificarEstiloMoto);
        edtColor = (EditText)findViewById(R.id.edtModificarColorMoto);
        edtKm = (EditText)findViewById(R.id.edtModificarKmMoto);
        edtCilindrada = (EditText)findViewById(R.id.edtModificarCilindradaMoto);
        edtAno = (EditText)findViewById(R.id.edtModificarAnoMoto);
        btnGuardar = (Button)findViewById(R.id.btnModificarContinuarMoto);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, estilos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstilo.setAdapter(adapter);

        edtMarca.setText(m.getMarca());
        edtModelo.setText(m.getModelo());
        edtMatricula.setText(m.getMatricula());
        edtColor.setText(m.getColor());
        edtKm.setText(String.valueOf(m.getKm()));
        edtCilindrada.setText(String.valueOf(m.getCilindrada()));
        edtAno.setText(String.valueOf(m.getAno()));
        spEstilo.setSelection(((ArrayAdapter<String>)spEstilo.getAdapter()).getPosition(m.getEstilo()));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HttpClient httpClient = new DefaultHttpClient();
                Comunes comunes = new Comunes();
                final HttpGet get = new HttpGet(comunes.getServerIp()+"motos/modificarmoto/"+m.getId()+"/"+
                        edtMarca.getText().toString()+"/"+edtModelo.getText().toString()+"/"+edtMatricula.getText().toString()+"/"+
                                edtColor.getText().toString()+"/"+edtKm.getText().toString()+"/"+edtCilindrada.getText().toString()+"/"+
                                edtAno.getText().toString()+"/"+spEstilo.getSelectedItem().toString());
                get.setHeader("content-type", "application/json");
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            HttpResponse resp = httpClient.execute(get);
                            String respStr = EntityUtils.toString(resp.getEntity());
                            Log.e("jaja",respStr);
                            if(respStr.equals("true")) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Moto modificada", Toast.LENGTH_LONG);
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
        });
    }
}

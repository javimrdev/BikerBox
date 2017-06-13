package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class AnadirMoto extends AppCompatActivity {

    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtColor;
    private EditText edtMatricula;
    private EditText edtKilometros;
    private EditText edtCilindrada;
    private EditText edtAno;
    private Button btnContinuar;
    private Usuario user;
    private Moto m;
    private Spinner spEstilo;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_moto);
        Intent intent = getIntent();
        user = (Usuario)intent.getSerializableExtra("usuario");
        String estilos[] = {"Naked","R","Cafe Racer","Trail","Enduro","Trial","Custom"};
        edtMarca = (EditText)findViewById(R.id.edtAnadirMarcaMoto);
        edtModelo = (EditText)findViewById(R.id.edtAnadirModeloMoto);
        edtMatricula = (EditText)findViewById(R.id.edtAnadirMatriculaMoto);
        edtKilometros = (EditText)findViewById(R.id.edtAnadirKmMoto);
        edtColor = (EditText)findViewById(R.id.edtAnadirColorMoto);
        edtCilindrada = (EditText)findViewById(R.id.edtAnadirCilindradaMoto);
        edtAno = (EditText)findViewById(R.id.edtAnadirAnoMoto);
        spEstilo = (Spinner)findViewById(R.id.spAnadirEstiloMoto);
        btnContinuar = (Button)findViewById(R.id.btnAnadirContinuarMoto);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, estilos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstilo.setAdapter(adapter);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = new Moto(edtMarca.getText().toString(),edtModelo.getText().toString(),edtMatricula.getText().toString(),
                        Integer.parseInt(edtCilindrada.getText().toString()),Integer.parseInt(edtAno.getText().toString()),Integer.parseInt(edtKilometros.getText().toString()),
                        edtColor.getText().toString());
                anadirMoto(m);
                finish();
            }
        });
    }

    private void anadirMoto(Moto m){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp()+"motos/anadirmotousuario/"+user.getId()+"/"+
                edtMarca.getText().toString()+"/"+edtModelo.getText().toString()+"/"+edtMatricula.getText().toString()+"/"+
                edtColor.getText().toString()+"/"+edtKilometros.getText().toString()+"/"+edtCilindrada.getText().toString()+"/"+
                edtAno.getText().toString()+"/"+spEstilo.getSelectedItem().toString());
        get.setHeader("content-type", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    if(!respStr.equals("null")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Moto añadida", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    }else{
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
    }
}

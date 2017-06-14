package com.example.javiermolina.bikerbox.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Adapters.AdaptadorAnunciosPerfil;
import com.example.javiermolina.bikerbox.Adapters.AdaptadorMotosPerfil;
import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Anuncio;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class UsuarioAnuncios extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView lstAnuncios;
    private FloatingActionButton fab;
    private Anuncio[] listaAnuncios;
    private Spinner spnProvincias;
    private Spinner spnLocalidades;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_anuncios);
        fab = (FloatingActionButton)findViewById(R.id.btnBuscarAnuncio);
        lstAnuncios = (ListView) findViewById(R.id.lstAnuncios);
        spnProvincias = (Spinner)findViewById(R.id.spnProvincias);
        spnLocalidades = (Spinner)findViewById(R.id.spnLocalidades);
        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("usuario");
        loadSpinnerProvincias();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerAnuncios(spnLocalidades.getSelectedItem().toString());
            }
        });

        lstAnuncios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Anuncio a = listaAnuncios[position];
                runOnUiThread(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(UsuarioAnuncios.this,UsuarioAnuncio.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("anuncio",a);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void obtenerAnuncios(String localidad){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();

        final HttpGet get = new HttpGet(comunes.getServerIp()+"anuncios/obtenerAnunciosProvincia/"+localidad);

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("hola",respStr);
                    if(!respStr.equals("[]")) {
                        JSONArray respJSONarray = new JSONArray(respStr);
                        listaAnuncios = new Anuncio[respJSONarray.length()];
                        for(int i =0;i<respJSONarray.length();i++){
                            JSONObject jsonobject = respJSONarray.getJSONObject(i);
                            listaAnuncios[i] = new Anuncio();
                            listaAnuncios[i].setId(jsonobject.getInt("id"));
                            listaAnuncios[i].setDescripcion(jsonobject.getString("descripcion"));
                            listaAnuncios[i].setMarca(jsonobject.getString("marca"));
                            listaAnuncios[i].setModelo(jsonobject.getString("modelo"));
                            listaAnuncios[i].setKm(jsonobject.getInt("km"));
                            listaAnuncios[i].setCilindrada(jsonobject.getInt("cilindrada"));
                            listaAnuncios[i].setLocalidad(jsonobject.getString("localidad"));
                            listaAnuncios[i].setColor(jsonobject.getString("color"));
                            listaAnuncios[i].setPrecio(jsonobject.getInt("precio"));
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //cargar listview de anuncios
                                AdaptadorAnunciosPerfil adaptador = new AdaptadorAnunciosPerfil(getApplicationContext(), listaAnuncios);
                                lstAnuncios.setAdapter(adaptador);
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "No se encuentran anuncios", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void loadSpinnerProvincias() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.provincias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnProvincias.setAdapter(adapter);

        this.spnProvincias.setOnItemSelectedListener(this);
        this.spnLocalidades.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spnProvincias:

                // Retrieves an array
                TypedArray arrayLocalidades = getResources().obtainTypedArray(
                        R.array.array_provincia_a_localidades);
                CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                arrayLocalidades.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, localidades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                this.spnLocalidades.setAdapter(adapter);

                break;

            case R.id.spnLocalidades:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

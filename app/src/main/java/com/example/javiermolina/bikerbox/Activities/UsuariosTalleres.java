package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Adapters.AdaptadorAnunciosPerfil;
import com.example.javiermolina.bikerbox.Adapters.AdaptadorTalleresUsuario;
import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Anuncio;
import com.example.javiermolina.bikerbox.Models.Taller;
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

public class UsuariosTalleres extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ListView lstAnuncios;
    private Taller listaTalleres[];
    private Spinner spProvinciasTalleresUsuario;
    private Spinner spLocalidadesTalleresUsuario;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_talleres);
        lstAnuncios = (ListView)findViewById(R.id.lstTalleresUsuario);
        spProvinciasTalleresUsuario = (Spinner)findViewById(R.id.spProvinciasUsuarioTalleres);
        spLocalidadesTalleresUsuario = (Spinner)findViewById(R.id.spLocalidadesUsuarioTaller);
        fab = (FloatingActionButton)findViewById(R.id.btnBuscarTalleresUsuario);
        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("usuario");
        loadSpinnerProvincias();
        obtenerTalleres(user.getLocalidad());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerTalleres(spLocalidadesTalleresUsuario.getSelectedItem().toString());
            }
        });
    }

    private void obtenerTalleres(String localidad){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();

        final HttpGet get = new HttpGet(comunes.getServerIp()+"taller/obtenertalleres/"+localidad);

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("hola",respStr);
                    if(!respStr.equals("[]")) {
                        JSONArray respJSONarray = new JSONArray(respStr);
                        listaTalleres = new Taller[respJSONarray.length()];
                        for(int i =0;i<respJSONarray.length();i++){
                            JSONObject jsonobject = respJSONarray.getJSONObject(i);
                            listaTalleres[i] = new Taller(jsonobject.getInt("id"));
                            listaTalleres[i].setNombre(jsonobject.getString("nombre"));
                            listaTalleres[i].setDescripcion(jsonobject.getString("descripcion"));
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //cargar listview de anuncios
                                AdaptadorTalleresUsuario adaptador = new AdaptadorTalleresUsuario(getApplicationContext(), listaTalleres);
                                lstAnuncios.setAdapter(adaptador);
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "No se encuentran talleres", Toast.LENGTH_LONG);
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
        this.spProvinciasTalleresUsuario.setAdapter(adapter);
        this.spProvinciasTalleresUsuario.setOnItemSelectedListener(this);
        this.spLocalidadesTalleresUsuario.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spProvinciasUsuarioTalleres:
                TypedArray arrayLocalidades = getResources().obtainTypedArray(
                        R.array.array_provincia_a_localidades);
                CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                arrayLocalidades.recycle();
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, localidades);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                this.spLocalidadesTalleresUsuario.setAdapter(adapter);
                break;
            case R.id.spLocalidadesUsuarioTaller:
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}

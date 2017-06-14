package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Adapters.AdaptadorMotosPerfil;
import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class UsuarioMotos extends AppCompatActivity {
    private ListView lstMotos;
    private FloatingActionButton btnanadir;
    private Usuario user;
    private Moto[] listaMotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_motos);
        btnanadir = (FloatingActionButton)findViewById(R.id.btnAnadirMoto);
        Intent intent = getIntent();
        user = (Usuario)intent.getSerializableExtra("usuario");
        obtenerMotos(user.getId());
        //registerForContextMenu(lstMotos);

        btnanadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(UsuarioMotos.this, AnadirMoto.class);
                        intent.putExtra("usuario",user);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_motos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:
                int x = info.position;
                Moto m = listaMotos[x];
                eliminarMoto(m);
                break;
            case R.id.CtxLblOpc2:
                int y = info.position;
                Moto mo = listaMotos[y];
                modificarMoto(mo);
                break;
            case R.id.CtxLblOpc3:
                int z = info.position;
                Moto mot = listaMotos[z];
                anunciarMoto(mot);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_anadirmoto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refreshMoto:
                obtenerMotos(user.getId());
                break;
        }
        return true;
    }

    private void anunciarMoto(final Moto m){
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(UsuarioMotos.this,AnunciarMoto.class);
                intent.putExtra("moto",m);
                startActivity(intent);
            }
        });
    }

    private void obtenerMotos(int id){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();

        final HttpGet get = new HttpGet(comunes.getServerIp()+"motos/obtenermotosusuario/"+id);

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("motos",respStr);
                    if(!respStr.equals("null")) {
                        JSONArray respJSONarray = new JSONArray(respStr);
                        listaMotos = new Moto[respJSONarray.length()];
                        for(int i =0;i<respJSONarray.length();i++){
                            JSONObject jsonobject = respJSONarray.getJSONObject(i);
                            listaMotos[i] = new Moto();
                            listaMotos[i].setId(jsonobject.getInt("id"));
                            listaMotos[i].setModelo(jsonobject.getString("modelo"));
                            listaMotos[i].setMarca(jsonobject.getString("marca"));
                            listaMotos[i].setMatricula(jsonobject.getString("matricula"));
                            listaMotos[i].setCilindrada(jsonobject.getInt("cilindrada"));
                            listaMotos[i].setEstilo(jsonobject.getString("estilo"));
                            listaMotos[i].setAno(jsonobject.getInt("año"));
                            listaMotos[i].setColor(jsonobject.getString("color"));
                            listaMotos[i].setKm(jsonobject.getInt("km"));
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //cargar listview de motos
                                AdaptadorMotosPerfil adaptador = new AdaptadorMotosPerfil(getApplicationContext(), listaMotos);
                                lstMotos = (ListView) findViewById(R.id.lstMotos);
                                lstMotos.setAdapter(adaptador);
                                if(lstMotos!=null)
                                    registerForContextMenu(lstMotos);
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

    private void eliminarMoto(Moto m){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp()+"motos/eliminarmoto/"+m.getId());
        get.setHeader("content-type", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    if(respStr.equals("true")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Moto eliminada", Toast.LENGTH_LONG);
                                toast.show();
                                obtenerMotos(user.getId());
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void modificarMoto(final Moto m){
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(UsuarioMotos.this,UsuarioModificarMoto.class);
                intent.putExtra("moto",m);
                startActivity(intent);
            }
        });
    }
}

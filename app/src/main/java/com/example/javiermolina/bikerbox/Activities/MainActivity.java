package com.example.javiermolina.bikerbox.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Helper.HelperValidacion;
import com.example.javiermolina.bikerbox.Models.Taller;
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

public class MainActivity extends AppCompatActivity {
    private Button btnInicioUsuario;
    private EditText edtCorreoUsuario;
    private EditText edtContrasenaUsuario;
    private TextView txtRecuperarUsuario;
    private Button btnRegistrarUsuario;
    private Button btnInicioTaller;
    private EditText edtCorreoTaller;
    private EditText edtContrasenaTaller;
    private TextView txtRecuperarTaller;
    private Button btnRegistrarTaller;
    private Comunes comunes;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCorreoUsuario = (EditText)findViewById(R.id.edtCorreoUsuario);
        edtContrasenaUsuario = (EditText)findViewById(R.id.edtContrasenaUsuario);
        btnInicioUsuario = (Button) findViewById(R.id.btnInicioUsuario);
        txtRecuperarUsuario = (TextView)findViewById(R.id.txtRecuperarUsuario);
        btnRegistrarUsuario = (Button)findViewById(R.id.btnRegistrarUsuario);
        edtCorreoTaller = (EditText)findViewById(R.id.edtCorreoTaller);
        edtContrasenaTaller = (EditText)findViewById(R.id.edtContrasenaTaller);
        btnInicioTaller = (Button)findViewById(R.id.btnInicioTaller);
        txtRecuperarTaller = (TextView)findViewById(R.id.txtRecuperarTaller);
        btnRegistrarTaller = (Button) findViewById(R.id.btnRegistrarTaller);




        TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("Usuario");
        tabSpec.setIndicator("Usuario");
        tabSpec.setContent(R.id.tab1);
        tabhost.addTab(tabSpec);
        tabSpec = tabhost.newTabSpec("Taller");
        tabSpec.setIndicator("Taller");
        tabSpec.setContent(R.id.tab2);
        tabhost.addTab(tabSpec);

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 666);
        }

        btnInicioUsuario.setOnClickListener(onClickListener);
        btnInicioTaller.setOnClickListener(onClickListener);
        btnRegistrarUsuario.setOnClickListener(onClickListener);
        btnRegistrarTaller.setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btnInicioUsuario:
                    //ocultar teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //metodo para validar el email
                    if(HelperValidacion.validarEmail(edtCorreoUsuario.toString())){
                        loginUsuario(edtCorreoUsuario.getText().toString(),edtContrasenaUsuario.getText().toString());
                    }
                    loginUsuario(edtCorreoUsuario.getText().toString(),edtContrasenaUsuario.getText().toString());
                    break;
                case R.id.btnRegistrarUsuario:
                            intent = new Intent(MainActivity.this,DatosRegistroUsuario.class);
                            startActivity(intent);
                    break;
                case R.id.btnInicioTaller:
                    //ocultar teclado
                    InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm2.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //metodo para validar el email
                    if(HelperValidacion.validarEmail(edtCorreoTaller.toString())){
                        loginTaller(edtContrasenaTaller.getText().toString(),edtContrasenaTaller.getText().toString());
                    }
                    loginTaller(edtCorreoTaller.getText().toString(),edtContrasenaTaller.getText().toString());
                    break;
                case R.id.btnRegistrarTaller:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            intent = new Intent(MainActivity.this,DatosRegistroTaller.class);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    private void loginUsuario(String correo, String contrasena){
        Usuario user;
        final HttpClient httpClient = new DefaultHttpClient();
        comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp()+"login/iniciarsesion/"+correo+"/"+contrasena);
        get.setHeader("content-type", "application/json");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        HttpResponse resp = httpClient.execute(get);
                        String respStr = EntityUtils.toString(resp.getEntity());
                        if(!respStr.equals("null")) {
                            JSONObject respJSON = new JSONObject(respStr);
                            final Usuario usuario = new Usuario(respJSON.getInt("id"),respJSON.getString("correo"),
                                    respJSON.getString("contrasena"),respJSON.getString("nombre"),respJSON.getString("apellidos"),
                                    respJSON.getString("localidad"));
                            Log.e("usuario",usuario.toString());
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        intent = new Intent(MainActivity.this, InicioUsuario.class);
                                        intent.putExtra("usuario",usuario);
                                        startActivity(intent);
                                    }
                                });

                        }else{
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_LONG);
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

    private void loginTaller(String correo, String contrasena){
        final HttpClient httpClient = new DefaultHttpClient();
        comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp()+"login/iniciarTaller/"+correo+"/"+contrasena);

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    if(!respStr.equals("null")) {
                        Log.e(("hola"),respStr);
                        JSONObject respJSON = new JSONObject(respStr);
                        int idCli = respJSON.getInt("id");
                        final Taller taller = new Taller(idCli);
                        if(taller!=null){

                            runOnUiThread(new Runnable() {
                                public void run() {
                                    intent = new Intent(MainActivity.this, MenuInicioTaller.class);
                                    intent.putExtra("taller",taller);
                                    startActivity(intent);
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_LONG);
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


}


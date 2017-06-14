package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Adapters.AdaptadorMotosPerfil;
import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
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

public class AnunciarMoto extends AppCompatActivity {

    private Button btnAnunciar;
    private EditText edtPrecio;
    private Moto m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunciar_moto);
        Intent intent = getIntent();
        m = (Moto) intent.getSerializableExtra("moto");
        btnAnunciar = (Button) findViewById(R.id.btnAnunciarMoto);
        edtPrecio = (EditText)findViewById(R.id.edtPrecioAnunciarMoto);

        btnAnunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anunciarMoto(m);
            }
        });
    }

    private void anunciarMoto(Moto m){
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();

        final HttpGet get = new HttpGet(comunes.getServerIp()+"motos/anunciarmoto/"+m.getId()+"/"+edtPrecio.getText().toString());

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("adasd",respStr);
                    if(respStr.equals("true")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Moto publicada", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_LONG);
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

package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Helper.*;

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

import static com.loopj.android.http.AsyncHttpClient.log;

public class DatosRegistroUsuario extends AppCompatActivity {
    private EditText nombre;
    private EditText apellidos;
    private EditText email;
    private EditText localidad;
    private EditText contrasena;
    private Button btnContinuar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registro_usuario);

        nombre = (EditText) findViewById(R.id.edtNombre);
        apellidos = (EditText) findViewById(R.id.edtApellidos);
        email = (EditText) findViewById(R.id.edtEmail);
        contrasena = (EditText) findViewById(R.id.edtContrasena);
        localidad = (EditText)findViewById(R.id.edtLocalidad) ;
        btnContinuar = (Button) findViewById(R.id.btnContinuar);

        //evento onClick del boton continuar
        btnContinuar.setOnClickListener(controlBtnContinuar);
    }

    //controlador del boton continuar
    private View.OnClickListener controlBtnContinuar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String aux;
            boolean correcto = true;
            /*
            aux = HelperValidacion.validarNombre(nombre.getText().toString());
            if (!aux.equals("si")) {
                nombre.setError("Error, nombre incorrecto");
                correcto = false;
            }
            aux = HelperValidacion.validarApellidos(apellidos.getText().toString());
            if (!aux.equals("si")) {
                apellidos.setError("Error, apellido incorrecto");
                correcto = false;
            }
            if (!HelperValidacion.validarEmail(email.getText().toString()))) {
                correcto = false;
            }
            aux = HelperValidacion.validarContrasena(contrasena.getText().toString(),email.getText().toString());
            if (!aux.equals("si")) {
                contrasena.setError("Error, nombre incorrecto");
                correcto = false;
            }*/
            if (correcto) {
                Usuario user = new Usuario(email.getText().toString(), contrasena.getText().toString(), nombre.getText().toString(),
                        apellidos.getText().toString(), localidad.getText().toString());
                registrarUsuario(user);
            }
        }
    };

    private void registrarUsuario(Usuario user) {
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp() + "login/registrarusuario/" + user.getCorreo() + "/" + user.getContrasena() + "/" +
                user.getNombre() + "/" + user.getApellidos() + "/" + user.getLocalidad());

        get.setHeader("content-type", "application/json");


        new Thread(new Runnable() {
            public void run() {
                try {
                    Log.e(("sadds"),get.toString());
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    log.e(("sadasd"),respStr);
                    if (!respStr.equals("null")) {
                        JSONObject respJSON = new JSONObject(respStr);
                        int idCli = respJSON.getInt("id");
                        final Usuario user = new Usuario(idCli);
                        runOnUiThread(new Runnable() {
                            public void run() {

                                intent = new Intent(DatosRegistroUsuario.this, InicioUsuario.class);
                                intent.putExtra("usuario", user);

                                Toast toast = Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_LONG);
                                toast.show();
                                startActivity(intent);
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_LONG);
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

package com.example.javiermolina.bikerbox.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.javiermolina.bikerbox.Helper.HelperValidacion;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;

import static android.R.attr.id;

public class InicioUsuario extends AppCompatActivity {
    Button btnUsuarioAnuncios;
    Button btnUsuarioMotos;
    Button btnUsuarioTalleres;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);
        Intent intent = getIntent();
        usuario = (Usuario)intent.getSerializableExtra("usuario");

        btnUsuarioAnuncios = (Button)findViewById(R.id.btnUsuarioAnuncios);
        btnUsuarioMotos = (Button)findViewById(R.id.btnUsuarioMotos);
        btnUsuarioTalleres = (Button)findViewById(R.id.btnUsuarioTalleres);

        btnUsuarioAnuncios.setOnClickListener(onClickListener);
        btnUsuarioMotos.setOnClickListener(onClickListener);
        btnUsuarioTalleres.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btnUsuarioMotos:
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(InicioUsuario.this, UsuarioMotos.class);
                            intent.putExtra("usuario",usuario);
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.btnUsuarioAnuncios:
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(InicioUsuario.this, UsuarioAnuncios.class);
                            intent.putExtra("usuario",usuario);
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.btnUsuarioTalleres:
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(InicioUsuario.this, UsuariosTalleres.class);
                            intent.putExtra("usuario",usuario);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };


}

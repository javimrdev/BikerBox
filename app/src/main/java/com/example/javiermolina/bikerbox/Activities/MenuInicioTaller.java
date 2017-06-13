package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.javiermolina.bikerbox.Adapters.AdaptadorMotosPerfil;
import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

public class MenuInicioTaller extends AppCompatActivity {

    private Button btnChat;
    private Button btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio_taller);
        btnChat = (Button) findViewById(R.id.btnChatTaller);
        btnPerfil = (Button) findViewById(R.id.btnDatosTaller);
        final Intent intent = getIntent();
        final Taller taller = (Taller) intent.getSerializableExtra("taller");
        Log.e("eeee", taller.toString());

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Intent intentPerfil = new Intent(MenuInicioTaller.this, PerfilTaller.class);
                        intentPerfil.putExtra("taller", taller);
                        startActivity(intentPerfil);
                    }
                });
            }
        });
    }
}

package com.example.javiermolina.bikerbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.R;

public class MenuInicioTaller extends AppCompatActivity {

    private Button btnChat;
    private Button btnPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio_taller);
        btnChat = (Button)findViewById(R.id.btnChatTaller);
        btnPerfil = (Button)findViewById(R.id.btnDatosTaller);
        final Intent intent = getIntent();
        final Taller taller = intent.getParcelableExtra("taller");


        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChat = new Intent(MenuInicioTaller.this,ChatTaller.class);
                intent.putExtra("taller",taller);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerfil = new Intent(MenuInicioTaller.this,PerfilTaller.class);
                intent.putExtra("Taller",taller);
            }
        });
    }
}

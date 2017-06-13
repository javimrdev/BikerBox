package com.example.javiermolina.bikerbox.Interfaces;

import com.example.javiermolina.bikerbox.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Javi on 31/05/2017.
 */

public interface UsuarioInterface {
    String url = "http://localhost:49890/";

    @GET("usuario/all")
    Call<List<Usuario>> getUsuario();
}

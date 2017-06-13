package com.example.javiermolina.bikerbox.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Javi on 05/06/2017.
 */

public class Taller implements Serializable{
    private int id;
    private String correo;
    private String contrasena;
    private String nombre;
    private String descripcion;
    private String localidad;
    private String foto;

    public Taller(int id) {
        this.id = id;
    }

    public Taller(String correo, String contrasena, String nombre, String descripcion, String localidad) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.localidad = localidad;
    }

    public Taller(int id, String correo, String contrasena, String nombre, String descripcion) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Taller(int id, String correo, String contrasena, String nombre, String descripcion,String localidad,String foto) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}

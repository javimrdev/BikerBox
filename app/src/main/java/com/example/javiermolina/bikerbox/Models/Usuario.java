package com.example.javiermolina.bikerbox.Models;

import java.io.Serializable;

/**
 * Created by Javi on 31/05/2017.
 */

public class Usuario implements Serializable{
    public int id;
    public String correo;
    public String contrasena;
    public String nombre;
    public String apellidos;
    public String localidad;

    public Usuario(int id, String correo, String contrasena, String nombre, String apellidos, String localidad) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
    }
    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(String correo, String contrasena, String nombre, String apellidos, String localidad) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}

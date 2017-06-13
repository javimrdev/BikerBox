package com.example.javiermolina.bikerbox.Models;

import java.io.Serializable;

/**
 * Created by Javi on 11/06/2017.
 */

public class Anuncio implements Serializable{
    private int id;
    private String comunidadAutonoma;
    private String provincia;
    private String localidad;
    private String descripcion;
    private int idMoto;
    private int idUsuario;
    private String nombre;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int ano;
    private int km;
    private String color;
    private float precio;

    public Anuncio(){};

    public Anuncio(int id, String comunidadAutonoma, String provincia, String localidad, String descripcion, int idMoto, int idUsuario) {
        this.id = id;
        this.comunidadAutonoma = comunidadAutonoma;
        this.provincia = provincia;
        this.localidad = localidad;
        this.descripcion = descripcion;
        this.idMoto = idMoto;
        this.idUsuario = idUsuario;
    }

    public Anuncio(String localidad, String descripcion, int idMoto, int idUsuario) {
        this.localidad = localidad;
        this.descripcion = descripcion;
        this.idMoto = idMoto;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

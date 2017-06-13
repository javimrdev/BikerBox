package com.example.javiermolina.bikerbox.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Javi on 05/06/2017.
 */

public class Moto implements Serializable{
    private int id;
    private String marca;
    private String modelo;
    private String matricula;
    private String estilo;
    private int cilindrada;
    private int ano;
    private int km;
    private String color;
    private Bitmap imagen;

    public Moto(){}

    public Moto(String s, String toString, String string, int i, int parseInt, int anInt, String s1) { }

    public Moto(int id, String marca, String modelo, String matricula, String estilo, int cilindrada, int ano, int km, String color) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.estilo = estilo;
        this.cilindrada = cilindrada;
        this.ano = ano;
        this.km = km;
        this.color = color;
    }


    public Moto(int id, String marca, String modelo, String matricula, String estilo, int cilindrada, int ano, int km, String color, Bitmap imagen) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.estilo = estilo;
        this.cilindrada = cilindrada;
        this.ano = ano;
        this.km = km;
        this.color = color;
        this.imagen = imagen;
    }

    public Moto(String marca, String modelo, String matricula, String estilo, int cilindrada, int ano, int km, String color, Bitmap imagen) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.estilo = estilo;
        this.cilindrada = cilindrada;
        this.ano = ano;
        this.km = km;
        this.color = color;
        this.imagen = imagen;
    }

    public Moto(String marca, String modelo, String matricula, int cilindrada, int ano, int km, String color, Bitmap bitmap) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cilindrada = cilindrada;
        this.ano = ano;
        this.km = km;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEstilo() {
        return estilo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public int getAno() {
        return ano;
    }

    public int getKm() {
        return km;
    }

    public String getColor() {
        return color;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", estilo='" + estilo + '\'' +
                ", cilindrada=" + cilindrada +
                ", ano=" + ano +
                ", km=" + km +
                ", color='" + color + '\'' +
                '}';
    }
}

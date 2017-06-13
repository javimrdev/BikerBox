package com.example.javiermolina.bikerbox.Models;

import java.io.Serializable;

/**
 * Created by Javi on 05/06/2017.
 */

public class Direccion  implements Serializable {
    private int id;
    private String calle;
    private byte numero;
    private char letra;

    public Direccion(int id, String calle, byte numero, char letra) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.letra = letra;
    }

    public Direccion(String calle, byte numero, char letra) {
        this.calle = calle;
        this.numero = numero;
        this.letra = letra;
    }

    public int getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }
}

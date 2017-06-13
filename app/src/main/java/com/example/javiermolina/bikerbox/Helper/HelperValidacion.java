package com.example.javiermolina.bikerbox.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Patterns;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Javi on 31/05/2017.
 */

public class HelperValidacion {

    //metodo para validar un email
    public static boolean validarEmail(String email){
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            return pattern.matcher(email).matches();
    }
    //metodo para validar el nombre
    public static String validarNombre(String nombre){
        String correcto = "si";
        if(nombre.isEmpty())
            correcto = "Error, el nombre esta vacio";
        if(nombre.length()>25)
            correcto = "Error, el nombre es demasiado largo";
        return correcto;
    }
    //metodo para validar apellidos
    public static String validarApellidos(String apellidos){
        String correcto = "si";
        if(apellidos.isEmpty())
            correcto = "Error, el apellido esta vacio";
        if(apellidos.length()>30)
            correcto = "Error, el apellido es demasiado largo";
        return correcto;
    }
    //metodo para validar la contraseña
    public static String validarContrasena(String contra, String email){
        String correcto = "si";
        if(contra.isEmpty())
            correcto = "Error, la contraseña esta vacía";
        if(contra.length()<6)
            correcto = "Error, la contraseña tiene menos de 6 caracteres";
        if(email.contains(contra))
            correcto = "Error, el email no puede contener la contraseña";
        return correcto;
    }
}

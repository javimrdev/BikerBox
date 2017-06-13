package com.example.javiermolina.bikerbox.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Helper.HelperValidacion;
import com.example.javiermolina.bikerbox.Models.Taller;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class DatosRegistroTaller extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Bitmap imagenTaller;
    private Intent intent;
    private EditText edtNombre;
    private EditText edtCorreo;
    private EditText edtContrasena;
    private EditText edtDescripcion;
    private Button btnRegistrarTaller;
    private ImageButton elegirImagen;
    private Spinner spLocalidades;
    private Spinner spProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registro_taller);

        elegirImagen = (ImageButton) findViewById(R.id.btnSubirImagen);
        edtNombre = (EditText) findViewById(R.id.edtNombreRegistroTaller);
        edtCorreo = (EditText) findViewById(R.id.edtCorreoRegistroTaller);
        edtContrasena = (EditText) findViewById(R.id.edtContrasenaRegistroTaller);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcionRegistroTaller);
        btnRegistrarTaller = (Button) findViewById(R.id.btnContinuar);
        spLocalidades = (Spinner)findViewById(R.id.spLocalidadesRegistroTaller);
        spProvincias = (Spinner)findViewById(R.id.spProvinciasRegistroTaller);
        loadSpinnerProvincias();
        btnRegistrarTaller.setOnClickListener(onClickListener);
        elegirImagen.setOnClickListener(btnSubirImagenControl);

    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btnContinuar:
                    if(elegirImagen!=null) {
                        Drawable d = elegirImagen.getBackground();
                        Taller taller = new Taller(edtCorreo.getText().toString(), edtContrasena.getText().toString(), edtNombre.getText().toString(),
                                edtDescripcion.getText().toString(),spLocalidades.getSelectedItem().toString());
                        registrarTaller(taller);
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Selecciona una foto", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                    break;
            }
        }
    };

    private void registrarTaller(Taller taller) {
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp() + "login/registrartaller/"+taller.getNombre()+"/"+taller.getCorreo()+"/"+taller.getContrasena()+"/"+
                taller.getDescripcion()+"/"+taller.getLocalidad());

        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e(("e"),respStr);
                    if (!respStr.equals("null")) {
                        JSONObject respJSON = new JSONObject(respStr);
                        int idTaller = respJSON.getInt("id");
                        Taller taller = new Taller(idTaller);
                        taller.setDescripcion(respJSON.getString("descripcion"));
                        taller.setLocalidad(respJSON.getString("localidad"));
                        taller.setNombre(respJSON.getString("nombre"));
                        taller.setCorreo(respJSON.getString("correo"));
                        intent = new Intent(DatosRegistroTaller.this, MenuInicioTaller.class);
                        intent.putExtra("taller", taller);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                        startActivity(intent);
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public OnClickListener btnSubirImagenControl = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    imagenTaller = BitmapFactory.decodeFile(filePath);
                }
        }
    }

    private void loadSpinnerProvincias() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.provincias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spProvincias.setAdapter(adapter);

        this.spProvincias.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        this.spLocalidades.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spProvinciasRegistroTaller:

                TypedArray arrayLocalidades = getResources().obtainTypedArray(
                        R.array.array_provincia_a_localidades);
                CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                arrayLocalidades.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, localidades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                this.spLocalidades.setAdapter(adapter);

                break;

            case R.id.spLocalidadesRegistroTaller:

                break;
        }
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String convertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}

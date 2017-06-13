package com.example.javiermolina.bikerbox.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javiermolina.bikerbox.Helper.Comunes;
import com.example.javiermolina.bikerbox.Models.Moto;
import com.example.javiermolina.bikerbox.Models.Usuario;
import com.example.javiermolina.bikerbox.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class AnadirMoto extends AppCompatActivity {

    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtColor;
    private EditText edtMatricula;
    private EditText edtKilometros;
    private EditText edtCilindrada;
    private EditText edtAno;
    private Button btnContinuar;
    private Usuario user;
    private Moto m;
    private Bitmap auxFoto;
    private Spinner spEstilo;
    private ArrayAdapter<String> adapter;
    private static final int SELECT_FILE = 1;
    private TextView txtSeleccionarImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_moto);
        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("usuario");
        String estilos[] = {"Naked", "R", "Cafe Racer", "Trail", "Enduro", "Trial", "Custom"};
        edtMarca = (EditText) findViewById(R.id.edtAnadirMarcaMoto);
        edtModelo = (EditText) findViewById(R.id.edtAnadirModeloMoto);
        edtMatricula = (EditText) findViewById(R.id.edtAnadirMatriculaMoto);
        edtKilometros = (EditText) findViewById(R.id.edtAnadirKmMoto);
        edtColor = (EditText) findViewById(R.id.edtAnadirColorMoto);
        edtCilindrada = (EditText) findViewById(R.id.edtAnadirCilindradaMoto);
        edtAno = (EditText) findViewById(R.id.edtAnadirAnoMoto);
        spEstilo = (Spinner) findViewById(R.id.spAnadirEstiloMoto);
        btnContinuar = (Button) findViewById(R.id.btnAnadirContinuarMoto);
        txtSeleccionarImagen = (TextView) findViewById(R.id.btnSeleccionarImagen);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, estilos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstilo.setAdapter(adapter);

        txtSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Seleccione una imagen"),
                        SELECT_FILE);
            }
        });
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = new Moto(edtMarca.getText().toString(), edtModelo.getText().toString(), edtMatricula.getText().toString(),
                        Integer.parseInt(edtCilindrada.getText().toString()), Integer.parseInt(edtAno.getText().toString()), Integer.parseInt(edtKilometros.getText().toString()),
                        edtColor.getText().toString());

                anadirMoto(m);
                finish();
            }
        });
    }

    private void anadirMoto(Moto m) {
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpGet get = new HttpGet(comunes.getServerIp() + "motos/anadirmotousuario/" + user.getId() + "/" +
                edtMarca.getText().toString() + "/" + edtModelo.getText().toString() + "/" + edtMatricula.getText().toString() + "/" +
                edtColor.getText().toString() + "/" + edtKilometros.getText().toString() + "/" + edtCilindrada.getText().toString() + "/" +
                edtAno.getText().toString() + "/" + spEstilo.getSelectedItem().toString());
        get.setHeader("content-type", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse resp = httpClient.execute(get);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    if (!respStr.equals("null")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Moto añadida", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public View.OnClickListener btnSubirImagenControl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
        }
    };

    private void anadirMotoFoto(final Moto m) {
        final HttpClient httpClient = new DefaultHttpClient();
        Comunes comunes = new Comunes();
        final HttpPost post = new HttpPost(comunes.getServerIp() + "motos/anadirmotousuariofoto");
        post.setHeader("content-type", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    JSONObject js = new JSONObject();
                    js.put("idUsuario", m.getId());
                    js.put("marca", m.getMarca());
                    js.put("modelo", m.getModelo());
                    js.put("matricula", m.getMatricula());
                    js.put("color", m.getColor());
                    js.put("km", m.getKm());
                    js.put("cilindrada", m.getCilindrada());
                    js.put("ano", m.getAno());
                    js.put("estilo", m.getEstilo());
                    js.put("foto", BitMapToByteArray(auxFoto));
                    StringEntity entity = new StringEntity(js.toString());
                    Log.e("asda", entity.toString());
                    post.setEntity(entity);
                    HttpResponse resp = httpClient.execute(post);
                    String respStr = EntityUtils.toString(resp.getEntity());
                    Log.e("jaja", respStr);
                    if (respStr.equalsIgnoreCase("true")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Moto añadida", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error", Toast.LENGTH_LONG);
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

    public String BitMapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT).replaceAll(" ", "%20");
        return temp;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath = selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                            auxFoto = Bitmap.createScaledBitmap(bmp, 120, 120, false);
                            ;

                        }
                    }
                }
                break;
        }
    }
}

package com.example.bd_android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class AltasActivity extends AppCompatActivity {

    EditText cajaNumControl;
    EditText cajaNombre;
    EditText cajaPA;
    EditText cajaSA;
    EditText cajaEdad;
    EditText cajaSemestre;
    EditText cajaCarrera;
    static boolean mensajeResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_add_num_control);
        cajaNombre = findViewById(R.id.caja_add_nombre);
        cajaPA = findViewById(R.id.caja_add_pa);
        cajaSA = findViewById(R.id.caja_add_sa);
        cajaEdad = findViewById(R.id.caja_add_edad);
        cajaSemestre = findViewById(R.id.caja_add_semestre);
        cajaCarrera = findViewById(R.id.caja_add_carrera);
    }

    public void agregarRegistro(View v) {
        String nc = cajaNumControl.getText().toString();
        String n = cajaNombre.getText().toString();
        String pa = cajaPA.getText().toString();
        String sa = cajaSA.getText().toString();
        String e = cajaEdad.getText().toString();
        String s = cajaSemestre.getText().toString();
        String c = cajaCarrera.getText().toString();

        //Verificar que la comunicación con WIFI funcione
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if(ni != null && ni.isConnected()) {
            agregarAlumno(nc, n, pa, sa, e, s, c);
            Toast.makeText(this, mensajeResultados?"EXITO":"ME CAMBIO DE CARRERA", Toast.LENGTH_LONG).show();
        } else {
            Log.e("MSJ-->", "Error en el WIFI");
        }

        Toast.makeText(this, "Magia", Toast.LENGTH_LONG).show();
    }

    public void agregarAlumno(String... datos) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_altas_alumnos.php";
                String metodo = "POST";

                Map<String, String> mapDatos = new HashMap<String, String>();
                mapDatos.put("nc", datos[0]);
                mapDatos.put("n", datos[1]);
                mapDatos.put("pa", datos[2]);
                mapDatos.put("sa", datos[3]);
                mapDatos.put("e", datos[4]);
                mapDatos.put("s", datos[5]);
                mapDatos.put("c", datos[6]);

                AnalizadorJSON aj = new AnalizadorJSON();
                JSONObject resultado = aj.peticionHTTP(url, metodo, mapDatos);

                boolean exito = false;
                try {
                    exito = resultado.getBoolean("exito");
                    AltasActivity.mensajeResultados = exito;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}


/*
class AgregarAlumno extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... datos) {

        String url = "http://192.168.0.4:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_altas_alumnos.php";
        String metodo = "POST";

        Map<String, String> mapDatos = new HashMap<String, String>();
        mapDatos.put("nc", datos[0]);
        mapDatos.put("n", datos[1]);
        mapDatos.put("pa", datos[2]);
        mapDatos.put("sa", datos[3]);
        mapDatos.put("e", datos[4]);
        mapDatos.put("s", datos[5]);
        mapDatos.put("c", datos[6]);

        AnalizadorJSON aj = new AnalizadorJSON();
        JSONObject resultado = aj.peticionHTTP(url, metodo, mapDatos);

        boolean exito = false;
        try {
            exito = resultado.getBoolean("exito");
            AltasActivity.mensajeResultados = exito;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return String.valueOf(exito);
    }
}*/
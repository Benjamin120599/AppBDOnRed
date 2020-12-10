package com.example.bd_android_http;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.AnalizadorJSON;

/*
public class ConsultasActivity extends AppCompatActivity {

    ListView listaALumnos;
    ArrayList<String> datos = new ArrayList<>();
    ArrayList<Alumno> listaAlumnos;

    public ConsultasActivity(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
        new MostrarAlumnos().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        new MostrarAlumnos().execute();

        listaALumnos = findViewById(R.id.list_view_alumnos);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datos);
        listaALumnos.setAdapter(adapter);

    }

    class MostrarAlumnos extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            String url = "http://192.168.0.4:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
            String metodo = "POST";

            AnalizadorJSON aj = new AnalizadorJSON();
            JSONObject jsonObject = aj.consultaHTTP(url, metodo);

            //JSONArray jsonArray = null;
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                String cadena = "";

                for(int i=0; i<jsonArray.length(); i++) {
                    cadena = jsonArray.getJSONObject(i).getString("nc")+" | "+
                             jsonArray.getJSONObject(i).getString("n")+" | "+
                             jsonArray.getJSONObject(i).getString("pa")+" | "+
                             jsonArray.getJSONObject(i).getString("sa")+" | "+
                             jsonArray.getJSONObject(i).getString("e")+" | "+
                             jsonArray.getJSONObject(i).getString("s")+" | "+
                             jsonArray.getJSONObject(i).getString("c");

                    datos.add(cadena);

                    Alumno alumno = new Alumno( jsonArray.getJSONObject(i).getString("nc"),
                            jsonArray.getJSONObject(i).getString("n"),
                            jsonArray.getJSONObject(i).getString("pa"),
                            jsonArray.getJSONObject(i).getString("sa"),
                            jsonArray.getJSONObject(i).getString("e"),
                            jsonArray.getJSONObject(i).getString("s"),
                            jsonArray.getJSONObject(i).getString("c"));
                    listaAlumnos.add(alumno);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}*/

public class ConsultasActivity extends Activity {

    RecyclerView recyclerViewConsultas;
    ArrayList<Alumno> listaAlumnos;
    AdapterConsultas adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        listaAlumnos = new ArrayList<>();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewConsultas = findViewById(R.id.recyclerview_consultas);
        recyclerViewConsultas.setLayoutManager(mLayoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                JSONObject jsonObject = analizadorJSON.consultaHTTP(url, metodo);

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                    for (int i=0; i<jsonArray.length();i++){
                        Alumno alumno = new Alumno( jsonArray.getJSONObject(i).getString("nc"),
                                jsonArray.getJSONObject(i).getString("n"),
                                jsonArray.getJSONObject(i).getString("pa"),
                                jsonArray.getJSONObject(i).getString("sa"),
                                jsonArray.getJSONObject(i).getString("e"),
                                jsonArray.getJSONObject(i).getString("s"),
                                jsonArray.getJSONObject(i).getString("c"));

                        listaAlumnos.add(alumno);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new AdapterConsultas(listaAlumnos);
                        recyclerViewConsultas.setAdapter(adapter);
                    }
                });

            }
        }).start();

    }
}
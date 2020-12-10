package com.example.bd_android_http;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.AnalizadorJSON;

public class BajasActivity extends Activity {

    RecyclerView recyclerViewBajas;
    ArrayList<Alumno> listaAlumnos;
    AdapterBajas adapter;
    public static boolean mensajeResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        listaAlumnos = new ArrayList<>();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewBajas = findViewById(R.id.recycler_view_bajas);
        recyclerViewBajas.setLayoutManager(mLayoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                JSONObject jsonObject = analizadorJSON.consultaHTTP(url, metodo);

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                    String cadena = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Alumno alumno = new Alumno(jsonArray.getJSONObject(i).getString("nc"),
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
                        adapter = new AdapterBajas(listaAlumnos);
                        recyclerViewBajas.setAdapter(adapter);
                    }
                });
            }
        }).start();

    }


    static class EliminarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... datos) {

            String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_bajas_alumnos.php";
            String metodo = "POST";

            AnalizadorJSON aj = new AnalizadorJSON();
            JSONObject resultado = aj.eliminacionHTTP(url, metodo, datos[0].substring(14));
            Log.i( "DATOS: ", datos[0].substring(14));
            boolean exito = false;
            try {
                exito = resultado.getBoolean("exito");
                BajasActivity.mensajeResultado = exito;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(exito);
        }
    }

}


    /*public void eliminarRegistro(AdapterBajas.ViewHolder holder) {

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nc = holder.numControl.toString();
                new EliminarAlumno().execute(nc);
                //Toast.makeText(getApplicationContext(), mensajeResultado?"Registro Eliminado":"No se pudo eliminar", Toast.LENGTH_LONG).show();
                adapter.listaAlumnos.remove(holder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });

    }


    class EliminarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... datos) {

            String url = "http://192.168.0.4:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_bajas_alumnos.php";
            String metodo = "POST";

            AnalizadorJSON aj = new AnalizadorJSON();
            JSONObject resultado = aj.eliminacionHTTP(url, metodo, datos[0]);

            boolean exito = false;
            try {
                exito = resultado.getBoolean("exito");
                BajasActivity.mensajeResultado = exito;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(exito);
        }
    }

        if(BajasActivity.mensajeResultado == true) {
            Toast.makeText(BajasActivity.this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BajasActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
        }

    class buscarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            String url = "http://192.168.0.4:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consulta_baja.php";
            String metodo = "POST";

            AnalizadorJSON aj = new AnalizadorJSON();
            JSONObject jsonObject = aj.consultaFiltroHTTP(url, metodo, "numControl");

            //JSONArray jsonArray = null;
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");
                Log.i("Tamaño JSONArray-->", ""+jsonArray.length());

                for(int i=0; i<jsonArray.length(); i++) {

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


    class EliminarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... datos) {

            String url = "http://192.168.0.4:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_bajas_alumnos.php";
            String metodo = "POST";

            AnalizadorJSON aj = new AnalizadorJSON();
            JSONObject resultado = aj.eliminacionHTTP(url, metodo, busqueda.getText().toString());

            boolean exito = false;
            try {
                exito = resultado.getBoolean("exito");
                AltasActivity.mensajeResultados = exito;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(exito);
        }
    }
    */
//}
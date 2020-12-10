package com.example.bd_android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class CambiosActivity extends AppCompatActivity {

    EditText cajaNumControl;
    EditText cajaNombre;
    EditText cajaPA;
    EditText cajaSA;
    EditText cajaEdad;
    EditText cajaSemestre;
    EditText cajaCarrera;
    EditText cajaBusqueda;
    Spinner spinner;
    Button btnBuscar, btnModificar;

    static public boolean mensajeResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        cajaNumControl = findViewById(R.id.caja_change_numcontrol);
        cajaNombre = findViewById(R.id.caja_change_nombre);
        cajaPA = findViewById(R.id.caja_change_pa);
        cajaSA = findViewById(R.id.caja_change_sa);
        cajaEdad = findViewById(R.id.caja_change_edad);
        cajaSemestre = findViewById(R.id.caja_change_semestre);
        cajaCarrera = findViewById(R.id.caja_change_carrera);
        cajaBusqueda = findViewById(R.id.caja_busqueda);
        spinner = findViewById(R.id.spinner_filtro);

        btnBuscar = findViewById(R.id.btn_busqueda);
        btnModificar = findViewById(R.id.btn_mod_alumnos);

        String[] filtros = {"Selecciona una opción", "Num_Control", "Nombre", "Primer_AP"};
        spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, filtros));

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String busqueda = cajaBusqueda.getText().toString();
                String filtro = spinner.getSelectedItem().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consulta.php";
                        String metodo = "POST";

                        AnalizadorJSON aj = new AnalizadorJSON();

                        Log.i("BUSCAR", btnBuscar+"");
                        Log.i("BUSCAR", spinner+"");
                        JSONObject resultado = aj.consultaFiltroHTTP(url, metodo, busqueda, filtro);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONArray jsonArray = resultado.getJSONArray("alumnos");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        cajaNumControl.setText( jsonArray.getJSONObject(i).getString("nc") );
                                        cajaNombre.setText( jsonArray.getJSONObject(i).getString("n") );
                                        cajaPA.setText( jsonArray.getJSONObject(i).getString("pa") );
                                        cajaSA.setText( jsonArray.getJSONObject(i).getString("sa") );
                                        cajaEdad.setText( jsonArray.getJSONObject(i).getString("e") );
                                        cajaSemestre.setText( jsonArray.getJSONObject(i).getString("s") );
                                        cajaCarrera.setText( jsonArray.getJSONObject(i).getString("c") );
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }).start();

            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    modificarRegistro(nc, n, pa, sa, e, s, c);
                    Toast.makeText(getBaseContext(), BajasActivity.mensajeResultado?"EXITO":"ME CAMBIO DE CARRERA", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("MSJ-->", "Error en el WIFI");
                }

                //Toast.makeText(this, "Magia", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void modificarRegistro(String... datos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_cambios_alumnos.php";
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

            }
        }).start();
    }


}
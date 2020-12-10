package com.example.bd_android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Controlador.AnalizadorJSON;

public class MainActivity extends AppCompatActivity {

    EditText cajaUsuario, cajaPass;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cajaUsuario = findViewById(R.id.caja_usuario);
        cajaPass = findViewById(R.id.caja_password);
        
    }

    public void abrirActivities(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.btn_acceder:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String url = "http://192.168.0.2:80/PaginasWebs/Pruebas_php/Aplicacion_ABCC/API_REST_Android/api_usuarios.php";
                        String metodo = "POST";

                        AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                        JSONObject jsonObject = analizadorJSON.consultaFiltroHTTP(url, metodo, cajaUsuario.getText().toString(), cajaPass.getText().toString());

                        try {
                            if(jsonObject.getBoolean("exito") == true) {
                                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(i);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Error al autenticarse", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.btn_registro:
                Toast.makeText(this, "Botón registro presionado", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }



}
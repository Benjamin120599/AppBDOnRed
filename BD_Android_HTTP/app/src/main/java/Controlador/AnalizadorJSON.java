package Controlador;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class AnalizadorJSON {
    InputStream is = null;
    OutputStream os = null;
    JSONObject jsonObject = null;
    String json = null;
    HttpURLConnection conexion = null;
    URL mUrl = null;

    //Método para altas, bajas, cambios
    public JSONObject peticionHTTP(String url, String metodo, Map datos) {

        //Envío de Informacion
        try {
            String cadenaJson = "{\"nc\":\""+URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8") +
                    "\", \"n\":\""+URLEncoder.encode(String.valueOf(datos.get("n")), "UTF-8") +
                    "\", \"pa\":\""+URLEncoder.encode(String.valueOf(datos.get("pa")), "UTF-8") +
                    "\", \"sa\":\""+URLEncoder.encode(String.valueOf(datos.get("sa")), "UTF-8") +
                    "\", \"e\":\""+URLEncoder.encode(String.valueOf(datos.get("e")), "UTF-8") +
                    "\", \"s\":\""+URLEncoder.encode(String.valueOf(datos.get("s")), "UTF-8") +
                    "\", \"c\":\""+URLEncoder.encode(String.valueOf(datos.get("c")), "UTF-8") + "\"}";


            mUrl = new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();

            //Activar envío de datos a través de HTTP
            conexion.setDoOutput(true);
            //Indicar método de envío
            conexion.setRequestMethod(metodo);

            //Tamaño preestablecido de la cadena
            conexion.setFixedLengthStreamingMode(cadenaJson.length());

            //Establecer el formato de envío de informacion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJson.getBytes());
            os.flush();
            os.close();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Información Recibida
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();
            String fila = null;
            while ( (fila = br.readLine() ) != null) {
                cadena.append(fila+"\n");
            }

            is.close();
            json = cadena.toString();
            jsonObject = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    //Método para consultas
    public JSONObject consultaHTTP(String url, String metodo) {

        //Petición
        try {
            mUrl = new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();

            //Activar envío de datos a través de HTTP
            conexion.setDoOutput(true);
            //Indicar método de envío
            conexion.setRequestMethod(metodo);
            //conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());
            //os.write(Integer.parseInt(""));
            os.flush();
            os.close();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Respuesta
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();
            String fila = null;
            while ( (fila = br.readLine() ) != null) {
                cadena.append(fila+"\n");
            }

            is.close();
            json = cadena.toString();
            jsonObject = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    public JSONObject eliminacionHTTP(String url, String metodo, String numControl) {

        //Peticion
        try {

            String cadenaJson = "{\"nc\":\""+URLEncoder.encode(numControl, "UTF-8")+"\"}";

            mUrl = new URL(url);

            conexion = (HttpURLConnection) mUrl.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJson.length());
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJson.getBytes());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Información Recibida
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();
            String fila = null;
            while ( (fila = br.readLine() ) != null) {
                cadena.append(fila+"\n");
            }

            is.close();
            json = cadena.toString();
            jsonObject = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONObject consultaFiltroHTTP(String url, String metodo, String numControl, String filtro) {

        //Peticion
        try {

            String cadenaJson = "{\"nc\":\""+URLEncoder.encode(numControl, "UTF-8")+
                                    "\", \"f\":\""+URLEncoder.encode(filtro, "UTF-8")+"\"}";

            mUrl = new URL(url);

            conexion = (HttpURLConnection) mUrl.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJson.length());
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJson.getBytes());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Información Recibida
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();
            String fila = null;
            while ( (fila = br.readLine() ) != null) {
                cadena.append(fila+"\n");
            }

            is.close();
            json = cadena.toString();
            jsonObject = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }



}

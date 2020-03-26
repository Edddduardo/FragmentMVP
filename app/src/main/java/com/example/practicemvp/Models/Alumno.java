package com.example.practicemvp.Models;

import com.example.practicemvp.Enums.Enums;
import com.example.practicemvp.Helpers.Helpers;
import com.example.practicemvp.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.practicemvp.Presents.TablaAlumnoPresentador;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Alumno {
    public String id;
    public String nombre;
    public String apellidos;
    public String edad;
    public String sexo;
    public String direccion;

    private static ArrayList <Alumno> alumnos;
    private static Alumno alumno;

    private static String urlAlumnos = Helpers.URL+ Enums.getAlumnos;


    public static void cargarTabla(final TablaAlumnoPresentador view){
        String token = LoginUser.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get(urlAlumnos , new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){

                    try {
                        alumnos = new ArrayList<Alumno>();
                        ArrayList <Alumno> filas = new ArrayList <Alumno> ();
                        JSONArray response =new JSONArray(new String(responseBody));
                        for (int i =0; i < response.length(); i++){
                            Alumno alumno = new Alumno();
                            alumno.nombre = response.getJSONObject(i).getString("name");
                            alumno.apellidos = response.getJSONObject(i).getString("lastname");
                            alumno.edad = response.getJSONObject(i).getString("age");
                            alumno.sexo = response.getJSONObject(i).getString("gender");
                            alumno.direccion = response.getJSONObject(i).getString("address");
                            alumno.id = response.getJSONObject(i).getString("id");
                            alumnos.add(alumno);
                        }
                        view.view.asignarTabla(alumnos);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    String rs = new String(responseBody);
                    //Toast.makeText(getActivity(),"404 ! "+ rs, Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("404 ! "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("500 ! "+ rs);
                } else if (statusCode == 403) {
                    //Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("403 ! "+ rs);
                } else {
                    //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert(rs);
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });


    }


    public static void getAlumno(int id, final TablaAlumnoPresentador view){
        String token = LoginUser.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( Helpers.URL +Enums.getAlumnos+ id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        JSONObject response =new JSONObject(new String(responseBody));
                        final Alumno alumno = new Alumno();
                        alumno.nombre = response.getString("name");
                        alumno.apellidos = response.getString("lastname");
                        alumno.edad = response.getString("age");
                        alumno.sexo = response.getString("gender");
                        alumno.direccion = response.getString("address");
                        alumno.id = response.getString("id");
                        view.view.dialogAlumno(alumno);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    String rs = new String(responseBody);
                    //Toast.makeText(getActivity(),"404 ! "+ rs, Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("404 ! "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("500 ! "+ rs);
                } else if (statusCode == 403) {
                    //Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("403 ! "+ rs);
                } else {
                    //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert(rs);
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }




}

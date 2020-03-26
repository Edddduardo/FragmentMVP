package com.example.practicemvp.Models;

import android.app.AlertDialog;

import com.example.practicemvp.Enums.Enums;
import com.example.practicemvp.Helpers.Helpers;
import com.example.practicemvp.Presents.TablaCarreraPresentador;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Carrera {
    public String id;
    public String name;
    public String periodo;
    public String codigo;
    private static ArrayList<Carrera> carreras;
    private static String urlCarreras = Helpers.URL+ Enums.getCarreras;


    @Override
    public String toString(){
        return "nombre: "+ name + " ID: " + id;
    }

    public static void getCarreras(final TablaCarreraPresentador view){

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ LoginUser.token);
        client.get(urlCarreras , new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){

                    try {
                        carreras = new ArrayList<Carrera>();
                        JSONArray response =new JSONArray(new String(responseBody));
                        for (int i =0; i < response.length(); i++){
                            Carrera carrera = new Carrera();
                            carrera.id = response.getJSONObject(i).getString("id");
                            carrera.name = response.getJSONObject(i).getString("name");
                            carrera.periodo = response.getJSONObject(i).getString("periodo");
                            carrera.codigo = response.getJSONObject(i).getString("codigo");


                            carreras.add(carrera);

                        }
                        view.view.asignarTabla(carreras);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    /*Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();*/
                    view.view.tablaAlert("404 !");
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("500 !");
                } else if (statusCode == 403) {
                    // Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("403 !");
                } else {
                    // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    view.view.tablaAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }

    public static void getCarrera(int id, final TablaCarreraPresentador view){

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ LoginUser.token);
        client.get(urlCarreras+ id, new AsyncHttpResponseHandler() {
            AlertDialog.Builder builder;
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
                        Carrera carrera = new Carrera();
                        carrera.id = response.getString("id");
                        carrera.name = response.getString("name");
                        carrera.periodo = response.getString("periodo");
                        carrera.codigo = response.getString("codigo");

                        view.view.dialogCarrera(carrera);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    /*Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();*/
                    view.view.tablaAlert("404 !");
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("500 !");
                } else if (statusCode == 403) {
                    // Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("403 !");
                } else {
                    // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    view.view.tablaAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }

}
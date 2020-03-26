package com.example.practicemvp.Presents;

import android.view.View;

import com.example.practicemvp.Models.Carrera;

import java.util.ArrayList;

public class TablaCarreraPresentador {
    public View view;

    public TablaCarreraPresentador(View view){
        this.view = view;
    }

    public interface View{
        void tablaAlert(String text);
        void asignarTabla(ArrayList<Carrera> carreras);
        void dialogCarrera(Carrera carrera);
    }

    public void getCarreras(){
        Carrera.getCarreras(this);
    }

    public void getCarrera(int id){
        Carrera.getCarrera(id, this);
    }
}

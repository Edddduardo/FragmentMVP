package com.example.practicemvp.Presents;

import android.view.View;

import com.example.practicemvp.Models.Alumno;

import java.util.ArrayList;

public class TablaAlumnoPresentador {
    public View view;
    public TablaAlumnoPresentador(View view){
        this.view = view;
    }

    public interface View{
        void tablaAlert(String text);
        void asignarTabla(ArrayList<Alumno> alumnos);
        void dialogAlumno(Alumno alumno);
    }

    public void cargarTabla(){
        Alumno.cargarTabla(this);
    }

    public void getAlumno(int id){
        Alumno.getAlumno(id, this);
    }


}

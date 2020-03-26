package com.example.practicemvp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.practicemvp.Helpers.Helpers;
import com.example.practicemvp.Models.LoginUser;
import com.example.practicemvp.R;

public class MostradorDeTablas extends AppCompatActivity {
    TablaAlumnos TablaAlumnos;
    TablaCarreras TablaCarreras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador_de_tablas);
        Toast.makeText(getApplicationContext(), "Username: "+ LoginUser.username, Toast.LENGTH_LONG).show();
        if(Helpers.admin == true){
            TablaAlumnos f_tabla = new TablaAlumnos();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_tabla, f_tabla);
            transaction.replace(R.id.fragment_tabla, f_tabla);
            transaction.commit();
        }else{
            TablaCarreras f_tabla = new TablaCarreras();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_tabla, f_tabla);
            transaction.commit();
        }
    }
}

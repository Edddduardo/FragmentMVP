package com.example.practicemvp.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicemvp.Models.Alumno;
import com.example.practicemvp.Models.LoginUser;
import com.example.practicemvp.Presents.TablaAlumnoPresentador;
import com.example.practicemvp.R;

import java.util.ArrayList;


public class TablaAlumnos extends Fragment implements TablaAlumnoPresentador.View{
    private TableLayout tl;
    private TextView tvNombres;
    private TextView tvApellidos;
    private TextView tvEdad;
    private Button btnAlumno;
    private ViewGroup root;
    private TablaAlumnoPresentador present;

    public TablaAlumnos() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.fragment_tabla_alumnos, null);
        tl = (TableLayout) root.findViewById(R.id.tbAlumno);
        //token = getArguments().getString("token");
        System.out.println(LoginUser.token);
        present = new TablaAlumnoPresentador(this);
        present.cargarTabla();
        //getAlumnos();
        return root;
    }


    @Override
    public void tablaAlert(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void asignarTabla(ArrayList<Alumno> alumnos) {
        for(int p =0; p < alumnos.size(); p++){
            TableRow tr1 = new TableRow(root.getContext());
            System.out.println(alumnos.get(p).nombre);
            tr1.setClickable(true);
            TableRow.LayoutParams layoutId = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams layoutNombre = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            tvNombres = new TextView(getActivity());
            tvNombres.setText(alumnos.get(p).nombre);
            tvNombres.setGravity(Gravity.CENTER);
            tvNombres.setTextColor(Color.BLACK);
            tvNombres.setPadding(100, 10, 100, 10);
            tvNombres.setLayoutParams(layoutNombre);
            tr1.addView(tvNombres);

                        /*tvId = new TextView(getActivity());
                        tvId.setText(alumnos.get(p).id);
                        tvId.setGravity(Gravity.CENTER);
                        tvId.setTextColor(Color.BLACK);
                        tvId.setPadding(150, 10, 150, 10);
                        tvId.setLayoutParams(layoutNombre);
                        tr1.addView(tvId);*/

            tvApellidos = new TextView(getActivity());
            tvApellidos.setText(alumnos.get(p).apellidos);
            tvApellidos.setGravity(Gravity.CENTER);
            tvApellidos.setTextColor(Color.BLACK);
            tvApellidos.setPadding(100, 10, 100, 10);
            tvApellidos.setLayoutParams(layoutNombre);
            tr1.addView(tvApellidos);

            tvEdad = new TextView(getActivity());
            tvEdad.setText(alumnos.get(p).edad);
            tvEdad.setGravity(Gravity.CENTER);
            tvEdad.setTextColor(Color.BLACK);
            tvEdad.setPadding(100, 10, 100, 10);
            tvEdad.setLayoutParams(layoutNombre);
            tr1.addView(tvEdad);

            btnAlumno = new Button(getActivity());
            btnAlumno.setText("Ver mas");
            btnAlumno.setGravity(Gravity.CENTER);
            btnAlumno.setBackgroundColor(Color.RED);
            btnAlumno.setTag(alumnos.get(p).id);
            btnAlumno.setTextColor(Color.BLACK);
            tr1.addView(btnAlumno);


            btnAlumno.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Id de alumno seleccionado ="+ id, Toast.LENGTH_LONG).show();
                    present.getAlumno(id);
                }
            });
            tl.addView(tr1);
        }
    }

    @Override
    public void dialogAlumno(Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alumno")
                .setMessage("Nombre: "+ alumno.nombre +
                        " \n Apellido: " + alumno.apellidos +
                        " \n Edad: "+ alumno.edad+
                        " \n Sexo: "+ alumno.sexo);
        builder.create().show();
    }
}

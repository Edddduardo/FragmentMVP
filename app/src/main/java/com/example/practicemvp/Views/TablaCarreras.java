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

import com.example.practicemvp.Models.Carrera;
import com.example.practicemvp.Presents.TablaCarreraPresentador;
import com.example.practicemvp.R;

import java.util.ArrayList;


public class TablaCarreras extends Fragment implements TablaCarreraPresentador.View  {
    private TableLayout tl;
    private TextView tvNombres;
    private TextView tvId;
    private TextView tvPeriodo;
    private Button btnCarrera;
    private ViewGroup root;
    private TablaCarreraPresentador present;

    public TablaCarreras() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_tabla_carrera, null);
        tl = (TableLayout) root.findViewById(R.id.tbCarrera);
        present = new TablaCarreraPresentador(this);
        //token = getArguments().getString("token");
        //System.out.println(token);
        present.getCarreras();
        return root;
    }

    @Override
    public void tablaAlert(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void asignarTabla(ArrayList<Carrera> carreras) {
        for(int p =0; p < carreras.size(); p++) {
            TableRow tr1 = new TableRow(getActivity());
            System.out.println(carreras.get(p).name);
            tr1.setClickable(true);
            TableRow.LayoutParams layoutId = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams layoutNombre = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            tvNombres = new TextView(getActivity());
            tvNombres.setText(carreras.get(p).name);
            tvNombres.setGravity(Gravity.CENTER);
            tvNombres.setTextColor(Color.BLACK);
            tvNombres.setPadding(100, 10, 100, 10);
            tvNombres.setLayoutParams(layoutNombre);
            tr1.addView(tvNombres);

            tvId = new TextView(getActivity());
            tvId.setText(carreras.get(p).id);
            tvId.setGravity(Gravity.CENTER);
            tvId.setTextColor(Color.BLACK);
            tvId.setPadding(100, 10, 100, 10);
            tvId.setLayoutParams(layoutNombre);
            tr1.addView(tvId);


            tvPeriodo = new TextView(getActivity());
            tvPeriodo.setText(carreras.get(p).periodo);
            tvPeriodo.setGravity(Gravity.CENTER);
            tvPeriodo.setTextColor(Color.BLACK);
            tvPeriodo.setPadding(100, 10, 100, 10);
            tvPeriodo.setLayoutParams(layoutNombre);
            tr1.addView(tvPeriodo);

            btnCarrera = new Button(getActivity());
            btnCarrera.setText("Ver mas");
            btnCarrera.setGravity(Gravity.CENTER);
            btnCarrera.setBackgroundColor(Color.BLUE);
            btnCarrera.setTag(carreras.get(p).id);
            btnCarrera.setTextColor(Color.BLACK);
            tr1.addView(btnCarrera);

            btnCarrera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Carrera seleccionada " + id, Toast.LENGTH_LONG).show();
                    present.getCarrera(id);
                }
            });
            tl.addView(tr1);
        }
    }

    @Override
    public void dialogCarrera(Carrera carrera) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Carrera")
                .setMessage("Nombre: "+ carrera.name);
        builder.create().show();
    }
}

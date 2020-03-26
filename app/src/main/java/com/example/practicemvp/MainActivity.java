package com.example.practicemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicemvp.Models.LoginUser;
import com.example.practicemvp.Presents.LoginPresentador;
import com.example.practicemvp.Views.MostradorDeTablas;

public class MainActivity extends AppCompatActivity implements LoginPresentador.View {
    public View view;
    public LoginUser login;
    EditText etUsuario;
    EditText etContraseña;
    Button btnEntrar;
    private LoginPresentador present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        present = new LoginPresentador(this);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present.login(etUsuario.getText().toString(),etContraseña.getText().toString());

            }
        });

    }


    @Override
    public void loginAlert(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void accesSuccest(String token, String Username){
        String token1 = token;
        String username1 = Username;
        LoginUser.token = token1;
        LoginUser.username = username1;

        Intent intent = new Intent(MainActivity.this , MostradorDeTablas.class);
        /*intent.putExtra(EXTRA_MESSAGE, username1);
        intent.putExtra(EXTRA_MESSAGE2, token1);*/
        startActivity(intent);
    }
}

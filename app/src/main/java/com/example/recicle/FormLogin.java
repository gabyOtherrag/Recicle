package com.example.recicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FormLogin extends AppCompatActivity {


    private TextView text_tela_cadastro, txt_local;
    private AppCompatButton entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        IniciarComponentes();


        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        txt_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, HomeCliente.class);
                startActivity(intent);

            }
        });
    }

    private void IniciarComponentes(){
        text_tela_cadastro= findViewById(R.id.text_tela_cadastro);
        txt_local = findViewById(R.id.txt_localizacao);
        entrar = findViewById(R.id.bt_entrar);
    }
}
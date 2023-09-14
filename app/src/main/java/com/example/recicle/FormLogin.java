package com.example.recicle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro, txt_local;
    private AppCompatButton entrar;

    private EditText edtemail, edtsenha;
    private DatabaseReference db;
    Usuario usuario;
    Usuario us ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        FirebaseApp.initializeApp(FormLogin.this);

        db = FirebaseDatabase.getInstance().getReference().child("usuario");


        IniciarComponentes();

        edtemail = findViewById(R.id.edit_emailL);
        edtsenha = findViewById(R.id.edit_senhaL);

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
                String email, senha;
                email = edtemail.getText().toString();
                senha = edtsenha.getText().toString();

                Query query = db.orderByChild("email").equalTo(email);


                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot snapshot1: snapshot.getChildren()){
                            usuario = snapshot1.getValue(Usuario.class);
                            us = usuario;
                        }

                        Query queryS = db.orderByChild("senha").equalTo(us.getSenha());
                        queryS.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot snapshot1: snapshot.getChildren()){
                                    usuario = snapshot1.getValue(Usuario.class);
                                    us = usuario;


                                }


                                Intent intent = new Intent(FormLogin.this, HomeCliente.class);
                                intent.putExtra("usuario", us);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }

        });

    }

    public void IniciarComponentes(){
        text_tela_cadastro= findViewById(R.id.text_tela_cadastro);
        txt_local = findViewById(R.id.txt_localizacao);
        entrar = findViewById(R.id.bt_entrar);
    }



}
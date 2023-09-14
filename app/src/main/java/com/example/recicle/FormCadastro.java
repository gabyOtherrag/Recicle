package com.example.recicle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class FormCadastro extends AppCompatActivity {

    ImageView photo;
    Bitmap capturar;
    Button btn;
    DatabaseReference db;

    EditText edtnome, edtemail, edtsenha;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        FirebaseApp.initializeApp(FormCadastro.this);

        db = FirebaseDatabase.getInstance().getReference().child("usuario");

        edtnome = findViewById(R.id.edit_nome);
        edtemail = findViewById(R.id.edit_email);
        edtsenha = findViewById(R.id.editsenha);
        btn = findViewById(R.id._bttUser);
        photo = findViewById(R.id.imageView);

        btn.setBackgroundTintList(null);

        final ConfirmacaoCadastro confirmar = new
                ConfirmacaoCadastro(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(FormCadastro.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(FormCadastro.this, new String[] {Manifest.permission.CAMERA}, 0);

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, email, senha, id;
                nome = edtnome.getText().toString();
                email = edtemail.getText().toString();
                senha = edtsenha.getText().toString();

                if(nome.trim().isEmpty()){
                    edtnome.setError("Campo obrigatório");
                }else if(email.trim().isEmpty()){
                    edtemail.setError("Campo obrigatório");
                }else if(senha.trim().isEmpty()){
                    edtsenha.setError("Campo obrigatório");
                }else {
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    DatabaseReference ref = db.push();
                    usuario.setId(ref.getKey());
                    db.child(usuario.getId()).setValue(usuario);

                    NotificationCompat.Builder builder =
                            confirmar.builder
                                    ("Bem-Vindo"
                                            ,"Estamos felizes por ter você aqui!");
                    confirmar.getManager().notify(new Random().nextInt(), builder.build());
                    Intent intent =  new Intent(FormCadastro.this, FormLogin.class);
                    startActivity(intent);

                }

            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Habilitar();
            }
        });
    }
    public void Habilitar(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == FormCadastro.RESULT_OK ){

            Bundle extras = data.getExtras();
            capturar = (Bitmap) extras.get("data");
            photo.setImageBitmap(capturar);

        }

        super.onActivityResult(requestCode, resultCode,data);
    }
}
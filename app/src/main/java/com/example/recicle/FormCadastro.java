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
import android.widget.ImageView;

import java.util.Random;

public class FormCadastro extends AppCompatActivity {

    Button btn;
    ImageView photo;
    Bitmap capturar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        btn = findViewById(R.id.bt_nv_user);

        btn.setBackgroundTintList(null);

        if (ActivityCompat.checkSelfPermission(FormCadastro.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(FormCadastro.this, new String[] {Manifest.permission.CAMERA},0);
        }
        ConfirmacaoCadastro confirmar = new
                ConfirmacaoCadastro(getApplicationContext());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        confirmar.builder
                                ("Bem-Vindo"
                                        ,"Estamos felizes por ter você aqui!");
                confirmar.getManager().notify(new Random().nextInt(), builder.build());
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
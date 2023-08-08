package com.example.recicle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class FormCadastro extends AppCompatActivity {

    Button btn;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        btn = findViewById(R.id.bt_nv_user);

        btn.setBackgroundTintList(null);

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
    }
}
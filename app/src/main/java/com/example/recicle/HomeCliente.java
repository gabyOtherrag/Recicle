package com.example.recicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeCliente extends AppCompatActivity {
    TextView account, exit, textView3, api;

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        textView3 = findViewById(R.id.textView3);
        account = findViewById(R.id.account_id);
        exit = findViewById(R.id.exit_account);
        api = findViewById(R.id.textViewH);

        Bundle user = getIntent().getExtras();
        if ((user!= null) && (user.containsKey("usuario"))) {
            usuario  = (Usuario) user.getSerializable("usuario");

        }else {

            Intent intent = new Intent(HomeCliente.this, FormLogin.class);
            Toast.makeText(HomeCliente.this,
                    "Sessão encerrada!", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCliente.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCliente.this, Account.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }
        });

        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HomeCliente.this, ViewAPI.class);
//                intent.putExtra("usuario", usuario);
//                startActivity(intent);
//                finish();
            }
        });



    }


}
package com.example.recicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Account extends AppCompatActivity {

    EditText edtnome, edtemail, edtsenha;
    Button btn, btd;
    TextView ret;
    Usuario usuario;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        db = FirebaseDatabase.getInstance().getReference();

        edtnome = findViewById(R.id.edt_nomeU);
        edtemail = findViewById(R.id.edt_emailU);
        edtsenha = findViewById(R.id.edt_senhaU);
        ret = findViewById(R.id._return);

        btn = findViewById(R.id._bttUser);
        btd = findViewById(R.id._bttDusr);

        Bundle user = getIntent().getExtras();
        if ((user!= null) && (user.containsKey("usuario"))) {
            usuario  = (Usuario) user.getSerializable("usuario");
        }else {

            Intent intent = new Intent(Account.this, FormLogin.class);
            Toast.makeText(Account.this,
                    "Sua sessão foi encerrada faça o login novamente!", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }

        edtnome.setHint(usuario.getNome().toString());
        edtemail.setHint(usuario.getEmail().toString());
        edtsenha.setHint(usuario.getSenha().toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updated();
            }
        });

        btd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dell();

            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, HomeCliente.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Updated(){

        if(!edtnome.getText().toString().trim().isEmpty()){
            usuario.setNome(edtnome.getText().toString());
        }else if( !edtemail.getText().toString().trim().isEmpty()){
            usuario.setEmail(edtemail.getText().toString());

        }else if( !edtsenha.getText().toString().trim().isEmpty()){
            usuario.setSenha(edtsenha.getText().toString());

        }

        db.child("usuario").child(usuario.getId()).setValue(usuario);
        Intent intent = new Intent(Account.this, HomeCliente.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
        finish();

        Toast.makeText(this, "Dados atualizados", Toast.LENGTH_SHORT).show();

    }

    public void dell(){
        db.child("usuario").child(usuario.getId()).removeValue();
        Toast.makeText(Account.this, "Conta excluída!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Account.this, FormLogin.class);
        startActivity(intent);
        finish();
        System.exit(0);


    }
}
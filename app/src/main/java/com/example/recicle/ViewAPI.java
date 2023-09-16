package com.example.recicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAPI extends AppCompatActivity {

    TextView bt, vt, json;

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_api);
        bt = findViewById(R.id.buscar_api);
        vt = findViewById(R.id.textView2);
        json = findViewById(R.id.textViewJson);

        Bundle user = getIntent().getExtras();
        if ((user!= null) && (user.containsKey("usuario"))) {
            usuario  = (Usuario) user.getSerializable("usuario");

        }else {

            Intent intent = new Intent(ViewAPI.this, FormLogin.class);
            Toast.makeText(ViewAPI.this,
                    "Sessão encerrada!", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarAPI chamarAPI = new ChamarAPI();
                chamarAPI.execute("https://viacep.com.br/ws/01001000/json/");
            }
        });

        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAPI.this, HomeCliente.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }
        });

    }

    private class ChamarAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno = ConectAPI.getAPI(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            json.setText(s);
        }

    }
}
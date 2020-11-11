package com.example.appqueimadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class cadastroUsi extends AppCompatActivity {

    EditText edtLogin, edtsenha, confirmasenha;
    Button btnConfirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usi);

        edtLogin = findViewById(R.id.edtUsuarioNovo);
        edtsenha = findViewById(R.id.edtSenha);
        confirmasenha = findViewById(R.id.edtConfirmaSenha);
        btnConfirma = findViewById(R.id.btnCadastrausi);

        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String senha = edtsenha.getText().toString();
                String confirma = confirmasenha.getText().toString();

                if(confirma.equals(senha))
                {
                    Manute manute = new Manute();
                    manute.cadastrarUsuario(cadastroUsi.this, edtLogin.getText().toString(), confirma);
                }
                else
                    {
                        confirmasenha.setError("senha não confere");
                        confirmasenha.requestFocus();
                }
            }
        });

    }

}
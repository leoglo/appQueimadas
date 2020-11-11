package com.example.appqueimadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela2 extends AppCompatActivity {

    Button btnlistaqueimadas, btnDenuncia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        btnlistaqueimadas = findViewById(R.id.listaqueimada);
        btnDenuncia = findViewById(R.id.btndenuncia);

        btnlistaqueimadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tela2.this, mapslocais.class));
            }
        });

        btnDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tela2.this, maps.class));
            }
        });
    }
}
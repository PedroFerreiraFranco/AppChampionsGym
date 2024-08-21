package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        AppCompatButton btTreinos = findViewById(R.id.bt_treinos);
        AppCompatButton btObjetivos = findViewById(R.id.bt_objetivos);
        AppCompatButton btPerfil = findViewById(R.id.bt_perfil);

        btTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormTreino.class);
                startActivity(intent);
            }
        });

        btObjetivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormObjetivo.class);
                startActivity(intent);
            }
        });

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Perfil.class);
                startActivity(intent);
            }
        });
    }
}
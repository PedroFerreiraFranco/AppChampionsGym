package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        AppCompatButton btTreinos = findViewById(R.id.bt_treinos);
        AppCompatButton btVideos = findViewById(R.id.bt_videos);
        AppCompatButton btPerfil = findViewById(R.id.bt_perfil);

        btTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormTreino.class);
                startActivity(intent);
            }
        });

        btVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Videos.class);
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
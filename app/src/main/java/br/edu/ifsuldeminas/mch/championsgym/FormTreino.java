package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;

public class FormTreino extends AppCompatActivity {

    private ListView listViewTreinos;
    private ArrayAdapter<Treino> treinoAdapter;
    private List<Treino> treinoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_treino);
        getSupportActionBar().hide();


        listViewTreinos = findViewById(R.id.listViewTreinos);
        treinoList = new ArrayList<>();

        treinoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, treinoList);
        listViewTreinos.setAdapter(treinoAdapter);

        findViewById(R.id.bt_cadastrarTreino).setOnClickListener(v -> {
            Intent intent = new Intent(FormTreino.this, FormCadastroTreino.class);
            startActivityForResult(intent, 1);
        });

        carregarTreinos(); // Carregar treinos ao iniciar a atividade
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Treino novoTreino = (Treino) data.getSerializableExtra("novoTreino");
            if (novoTreino != null) {
                treinoList.add(novoTreino);
                treinoAdapter.notifyDataSetChanged();
            }
        }
    }

    private void carregarTreinos() {
        TreinoDAO treinoDAO = new TreinoDAO(this);
        List<Treino> treinos = treinoDAO.loadTreinos();
        treinoList.clear();
        treinoList.addAll(treinos);
        treinoAdapter.notifyDataSetChanged();
    }
}
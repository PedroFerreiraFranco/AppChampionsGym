package br.edu.ifsuldeminas.mch.championsgym;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.sql.Time;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;

public class FormCadastroTreino extends AppCompatActivity {

    private EditText editNomeTreino, editDuracao, editData;
    private Button btnRegistrar;
    private TreinoDAO treinoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro_treino);

        treinoDAO = new TreinoDAO(this);

        // Inicializando os componentes
        editNomeTreino = findViewById(R.id.edit_nome_treino);
        editDuracao = findViewById(R.id.edit_duracao);
        editData = findViewById(R.id.edit_data);
        btnRegistrar = findViewById(R.id.bt_registrarTreino);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capturando os valores inseridos pelo usuário
                String nomeExercicio = editNomeTreino.getText().toString();
                Time duracao = Time.valueOf(editDuracao.getText().toString()); // Espera formato hh:mm:ss
                Date data = Date.valueOf(editData.getText().toString()); // Espera formato yyyy-MM-dd

                // Criando o objeto treino
                Treino treino = new Treino(0, nomeExercicio, duracao, data);

                // Inserindo o treino no banco de dados
                treinoDAO.addTreino(treino);

                // Feedback ao usuário (você pode usar um Toast aqui, por exemplo)
                finish(); // Fecha a Activity após o cadastro
            }
        });
    }
}

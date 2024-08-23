package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        getSupportActionBar().hide();
        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this,FormCadastro.class);
                startActivity(intent);
            }
        });

        emailInput = findViewById(R.id.edit_email);
        passwordInput = findViewById(R.id.edit_senha);

        Button loginButton = findViewById(R.id.bt_entrar);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (email.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(FormLogin.this, MainActivity.class);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            } else {
                Toast.makeText(FormLogin.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
    }
}
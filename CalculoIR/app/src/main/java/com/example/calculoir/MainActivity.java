package com.example.calculoir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText BarraNome, BarraEmail, BarraIdade, BarraDisciplina, BarraNota1, BarraNota2;
    private Button ButaoEnviar, ButaoResetar;
    private TextView ResultadoM, ResultadoR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos elementos de UI
        BarraNome = findViewById(R.id.BarraNome);
        BarraEmail = findViewById(R.id.BarraEmail);
        BarraIdade = findViewById(R.id.BarraIdade);
        BarraDisciplina = findViewById(R.id.BarraDisciplina);
        BarraNota1 = findViewById(R.id.BarraNota1);
        BarraNota2 = findViewById(R.id.BarraNota2);
        ButaoEnviar = findViewById(R.id.ButaoEnviar);
        ButaoResetar = findViewById(R.id.ButaoResetar);
        ResultadoM = findViewById(R.id.ResultadoMensagens);
        ResultadoR = findViewById(R.id.ResultadoResumo);

        ButaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ver();
            }
        });

        ButaoResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetar();
            }
        });
    }

    private void ver() {
        String nome = BarraNome.getText().toString().trim();
        String email = BarraEmail.getText().toString().trim();
        String idadeStr = BarraIdade.getText().toString().trim();
        String disciplina = BarraDisciplina.getText().toString().trim();
        String nota1Str = BarraNota1.getText().toString().trim();
        String nota2Str = BarraNota2.getText().toString().trim();

        if (nome.isEmpty()) {
            Erro();
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            Erro();
            return;
        }
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                Erro();
                return;
            }
        } catch (NumberFormatException e) {
            Erro();
            return;
        }
        if (disciplina.isEmpty()) {
            Erro();
            return;
        }
        double nota1, nota2;
        try {
            nota1 = Double.parseDouble(nota1Str);
            nota2 = Double.parseDouble(nota2Str);
            if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
                Erro();
                return;
            }
        } catch (NumberFormatException e) {
            Erro();
            return;
        }

        String resultado = calculandoR(nota1, nota2);
        String resumo = "Nome: " + nome + " === " + "Email: " + email + "\n" +
                "Idade: " + idade + " === " + "Disciplina: " + disciplina + "\n" +
                "Notas 1 e 2 Bimestres: " + nota1 + ", " + nota2 + "\n" +
                resultado;
        ResultadoR.setText(resumo);
        ResultadoM.setText("");
    }

    private String calculandoR(double nota1, double nota2) {
        double media = (nota1 + nota2) / 2;
        String resultadofinal;
        if (media >= 6) {
            resultadofinal = "Aprovado";
        } else {
            resultadofinal = "Reprovado";
        }
        return "Média: " + media + " === " + "Situação: " + resultadofinal;
    }


    private void Erro() {
        ResultadoM.setText("ERRO");
        ResultadoR.setText("");
    }

    private void resetar() {
        BarraNome.setText("");
        BarraEmail.setText("");
        BarraIdade.setText("");
        BarraDisciplina.setText("");
        BarraNota1.setText("");
        BarraNota2.setText("");
        ResultadoR.setText("");
        ResultadoM.setText("");
    }
}

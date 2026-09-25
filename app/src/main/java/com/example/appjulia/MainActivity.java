package com.example.appjulia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });

        Integer[] imagens = new Integer[]{
                R.drawable.perfil,
                R.drawable.abaixopeso,
                R.drawable.normal,
                R.drawable.obesidade1,
                R.drawable.obesidade2,
                R.drawable.obesidade3
        };

        Button botaoVoltar, botaoProximo;

        botaoVoltar = findViewById(R.id.btnVoltar);
        botaoProximo = findViewById(R.id.btnProximo);

        ImageView imageView = findViewById(R.id.imageView);

        // Botão Próximo
        botaoProximo.setOnClickListener(view -> {
            posicao++;

            if (posicao >= imagens.length) {
                posicao = 0;
            }

            imageView.setImageResource(imagens[posicao]);
        });

        // Botão Voltar
        botaoVoltar.setOnClickListener(view -> {
            posicao--;

            if (posicao < 0) {
                posicao = imagens.length - 1;
            }

            imageView.setImageResource(imagens[posicao]);
        });
    }
}
package com.example.appjulia;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String>nomes = new ArrayList<>();
    //String nomes[] = new String[]{"Juju", "Helo", "Jojo", "Duda", "Kiki", "Oó"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText editaNome = findViewById(R.id.editaNome);
        Button botaoSalvar = findViewById(R.id.botaoSalvar);
        ListView lv = findViewById(R.id.listView);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, R.layout.item_lista, R.id.textView, nomes);

        botaoSalvar.setOnClickListener(v -> {
            String nome = editaNome.getText().toString().trim();

            if (!nome.isEmpty()) {
                nomes.add(nome);
                adaptador.notifyDataSetChanged();
                editaNome.setText("");

                Toast.makeText(this, "Nome salvo!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Digite um nome!", Toast.LENGTH_SHORT).show();
            }
        });

        //ArrayAdapter<String> adaptador = new ArrayAdapter<>(getApplicationContext(), R.layout.item_lista, R.id.textView, nomes);

        lv.setAdapter(adaptador);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, nomes.get(position), Toast.LENGTH_LONG).show();
        });
    }

}
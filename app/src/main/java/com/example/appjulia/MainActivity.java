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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editaTitulo;
    private EditText editaTexto;

    private NotaDbHelper dbHelper;
    private final List<Nota> listaNotas = new ArrayList<>();
    private ArrayAdapter<Nota> adaptador;

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

        editaTitulo = findViewById(R.id.editaTitulo);
        editaTexto = findViewById(R.id.editaTexto);
        Button botaoSalvar = findViewById(R.id.botaoSalvar);
        ListView listView = findViewById(R.id.listView);

        dbHelper = new NotaDbHelper(this);

        adaptador = new ArrayAdapter<>(this, R.layout.item_lista, R.id.textView, listaNotas);
        listView.setAdapter(adaptador);

        carregarNotas();

        botaoSalvar.setOnClickListener(v -> {
            String titulo = editaTitulo.getText().toString().trim();
            String texto = editaTexto.getText().toString().trim();

            if (!titulo.isEmpty() || !texto.isEmpty()) {
                long id = dbHelper.inserirNota(titulo, texto);
                if (id != -1) {
                    Toast.makeText(this, "Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
                    editaTitulo.setText("");
                    editaTexto.setText("");
                    carregarNotas();
                } else {
                    Toast.makeText(this, "Erro ao salvar nota!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha o título ou o texto!", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota nota = listaNotas.get(position);
            Toast.makeText(this, "Nota #" + nota.getId() + ": " + nota.getTitulo(), Toast.LENGTH_SHORT).show();
        });
    }

    private void carregarNotas() {
        listaNotas.clear();
        listaNotas.addAll(dbHelper.obterTodasNotas());
        adaptador.notifyDataSetChanged();
    }
}

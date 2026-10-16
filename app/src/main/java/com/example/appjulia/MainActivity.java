package com.example.appjulia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editText;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Criar ou abrir o banco de dados
        database = openOrCreateDatabase("notinhas", MODE_PRIVATE, null);

        // Criar a tabela
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS notas (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "titulo VARCHAR, " +
                        "conteudo VARCHAR)"
        );

        setContentView(R.layout.activity_main);

        // Encontrar os componentes da tela
        editText = findViewById(R.id.EditText);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listview);

        // Salvar a nota ao clicar no botão
        button.setOnClickListener(v -> {

            String titulo = editText.getText().toString().trim();

            if (titulo.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Digite um título para a nota",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            ContentValues values = new ContentValues();
            values.put("titulo", titulo);

            long id = database.insert("notas", null, values);

            if (id != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        "Nota salva com sucesso!",
                        Toast.LENGTH_SHORT
                ).show();

                editText.setText("");

            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Erro ao salvar a nota",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
    public void ListarNotas(){
        Cursor c=database.rawQuery( "SELECT * FROM notas LIMIT ?", new String[]{"100"});
    }
}



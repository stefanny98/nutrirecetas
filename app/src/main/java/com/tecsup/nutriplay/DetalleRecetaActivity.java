 package com.tecsup.nutriplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

 public class DetalleRecetaActivity extends AppCompatActivity {

    TextView ingredientesText, contenidoText, tituloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        String titulo = getIntent().getExtras().getString("titulo");
        String ingredientes = getIntent().getExtras().getString("ingredientes");
        String contenido = getIntent().getExtras().getString("contenido");

        ingredientesText = findViewById(R.id.ingredientes_text);
        contenidoText = findViewById(R.id.contenido_text);
        tituloText = findViewById(R.id.titulo_text);

        tituloText.setText(titulo);
        ingredientesText.setText(ingredientes);
        contenidoText.setText(contenido);

    }
}

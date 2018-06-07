 package com.tecsup.nutriplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

 public class DetalleRecetaActivity extends AppCompatActivity {

    TextView ingredientesText, contenidoText, tituloText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        String titulo = getIntent().getExtras().getString("titulo");
        String ingredientes = getIntent().getExtras().getString("ingredientes");
        String contenido = getIntent().getExtras().getString("contenido");
        String imagen = getIntent().getExtras().getString("imagen");

        ingredientesText = findViewById(R.id.ingredientes_text);
        contenidoText = findViewById(R.id.contenido_text);
        tituloText = findViewById(R.id.titulo_text);
        imageView = findViewById(R.id.receta_image);

        tituloText.setText(titulo);
        ingredientesText.setText(ingredientes);
        contenidoText.setText(contenido);
        Picasso.with(this).load(imagen).into(imageView);

    }
}

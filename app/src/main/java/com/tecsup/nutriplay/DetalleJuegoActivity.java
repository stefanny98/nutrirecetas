package com.tecsup.nutriplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetalleJuegoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private boolean estado;
    private TextView pregunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

       final String titulo =  getIntent().getExtras().getString("titulo");

        pregunta = findViewById(R.id.preguntaText);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("juego").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (titulo.equals(ds.child("titulo").getValue(String.class))) {
                        estado = ds.child("verdad").getValue(Boolean.class);
                        pregunta.setText(ds.child("pregunta").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });
    }

    public void verdadTapped(View view) {
        if(estado){
            Log.d("Respuesta:", "Respuesta correcta");
        }else{
            Log.d("Respuesta:", "Respuesta incorrecta");
        }
    }

    public void falsoTapped(View view) {
        if(!estado){
            Log.d("Respuesta:", "Respuesta correcta");
        }else{
            Log.d("Respuesta:", "Respuesta incorrecta");
        }
    }
}

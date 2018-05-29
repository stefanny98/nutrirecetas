package com.tecsup.nutriplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private String pregunta_id;
    private ArrayList<String> content = new ArrayList<>();
    private int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        listView = findViewById(R.id.listView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("juego").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot ds : dataSnapshot.getChildren()) {
                     content.add(ds.child("titulo").getValue(String.class));
                     Log.d("Array", content.toString());
                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(JuegoActivity.this, android.R.layout.simple_list_item_1, content);
                     listView.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent intent = new Intent(JuegoActivity.this, DetalleJuegoActivity.class);
                intent.putExtra("titulo", content.get(i));
                startActivity(intent);
                finish();

           }
       });

    }
}

package com.tecsup.nutriplay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MisRecetasFragment extends Fragment {


    View view;
    private RecyclerView misrecetasList;
    private DatabaseReference mDatabase;
    private  List<Receta> recetas = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mis_recetas, container, false);

        misrecetasList = (RecyclerView) view.findViewById(R.id.recetasLista);
        // Get a reference to our posts

// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Firebase ref = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");
        // Attach an listener to read the data at our posts reference
        mDatabase.child("receta").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        // Result will be holded Here
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Receta receta = dsp.getValue(Receta.class);
                            recetas.add(receta); //add result into array list
                            Log.d("receta", receta.getTitulo());
                        }
                        ListAdapter adapter = new ListAdapter();

                        recetas.add(new Receta("Pollo con Verduras", "Descripcionn"));
                        adapter.setRecetas(recetas);

                        misrecetasList.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        misrecetasList.setLayoutManager(layoutManager);
                      //  Receta receta = dataSnapshot.getValue(Receta.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






        return view;
    }
}
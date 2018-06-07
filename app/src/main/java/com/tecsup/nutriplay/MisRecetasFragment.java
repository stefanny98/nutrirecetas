package com.tecsup.nutriplay;

import android.app.ProgressDialog;
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
    private  ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mis_recetas, container, false);

        misrecetasList = (RecyclerView) view.findViewById(R.id.recetasLista);
        final String id_usu = "1";

        progress = new ProgressDialog(getContext());
        progress.setTitle("Cargando");
        progress.setMessage("Por favor espere...");
        progress.setCancelable(false);
        progress.show();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("receta").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                            mDatabase.child("coleccion").child(id_usu).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        boolean estado = dsp.getValue(Boolean.class);

                                        if (estado == true){
                                            String id_receta = dsp.getKey();
                                            if (ds.getKey().equals(id_receta)) {
                                                Receta receta = ds.getValue(Receta.class);
                                                receta.setId(ds.getKey());
                                                recetas.add(receta);
                                                ListAdapter adapter = new ListAdapter();
                                                adapter.setRecetas(recetas, getContext());
                                                misrecetasList.setAdapter(adapter);
                                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                                misrecetasList.setLayoutManager(layoutManager);

                                            }
                                        }
                                    }
                                    progress.dismiss();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                          //  Log.d("id_re", ds.getKey());

                        }

                        /*
                        ListAdapter adapter = new ListAdapter();

                       // recetas.add(new Receta("Pollo con Verduras", "Descripcionn"));
                        adapter.setRecetas(recetas);

                        misrecetasList.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        misrecetasList.setLayoutManager(layoutManager);*/
                      //  Receta receta = dataSnapshot.getValue(Receta.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






        return view;
    }
}
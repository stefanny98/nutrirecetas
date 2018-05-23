package com.tecsup.nutriplay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MisRecetasFragment extends Fragment {


    View view;
    private RecyclerView misrecetasList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mis_recetas, container, false);

        misrecetasList = (RecyclerView) view.findViewById(R.id.recetasLista);
        ListAdapter adapter = new ListAdapter();
        List<Receta> recetas = new ArrayList<>();
        recetas.add(new Receta("Pollo con Verduras", "Descripcionn"));
        adapter.setRecetas(recetas);

        misrecetasList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        misrecetasList.setLayoutManager(layoutManager);


        return view;
    }
}
package com.tecsup.nutriplay;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Receta> recetas;
    private Context context;
    public ListAdapter(){
        this.recetas = new ArrayList<>();
    }
    public void setRecetas(List<Receta> recetas, Context context) {
        this.recetas = recetas;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView titulo;
        public TextView descripcion;
        public ImageView picture;
        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.picture_image);
            titulo = (TextView) itemView.findViewById(R.id.titulo_text);
            descripcion = (TextView) itemView.findViewById(R.id.desc_text);
        }
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
      final Receta receta = this.recetas.get(position);
        viewHolder.titulo.setText(receta.getTitulo());
        viewHolder.descripcion.setText(receta.getDescripcion());

        Picasso.with(viewHolder.itemView.getContext()).load(receta.getImagen()).into(viewHolder.picture);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(view.getContext(), DetalleRecetaActivity.class);
                i1.putExtra("titulo", receta.getTitulo());
                i1.putExtra("imagen", receta.getImagen());
                i1.putExtra("ingredientes", receta.getIngredientes());
                i1.putExtra("contenido", receta.getContenido());
                view.getContext().startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recetas.size();
    }

}
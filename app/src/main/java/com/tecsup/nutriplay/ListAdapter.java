package com.tecsup.nutriplay;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Receta> recetas;

    public ListAdapter(){
        this.recetas = new ArrayList<>();
    }
    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public TextView titulo;
        public TextView descripcion;

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
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder, int position) {
      final Receta receta = this.recetas.get(position);
        viewHolder.titulo.setText(receta.getTitulo());
        viewHolder.descripcion.setText(receta.getDescripcion());
/*
        Context context = viewHolder.itemView.getContext();
        int idRes = context.getResources().getIdentifier(receta.getPicture(), "drawable", context.getPackageName());
        viewHolder.picture.setImageResource(idRes);
*/
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(view.getContext(), DetalleRecetaActivity.class);
               // i1.putExtra("id", receta.getTitulo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recetas.size();
    }

}
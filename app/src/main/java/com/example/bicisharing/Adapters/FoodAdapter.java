package com.example.bicisharing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.example.bicisharing.Entities.Food;
import com.example.bicisharing.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Food> arrayFood;

    public FoodAdapter(Context context, ArrayList<Food> arrayFood) {
        this.context = context;
        this.arrayFood = arrayFood;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Crear una instancia de ViewHolder inflando el diseño de un elemento de lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ecommerce_template, parent, false);
        return new FoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        // Vincular los datos del elemento de la lista con los elementos de la vista en ViewHolder
        Food food = arrayFood.get(position);
        holder.textNameEcommerce.setText(food.getName());
        holder.textPriceEcommerce.setText(food.getPrice());
        String imageUrl = food.getImage();
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error)  // Recurso drawable de imagen de error
                .into(holder.imgEcommerce);
    }

    @Override
    public int getItemCount() {
        // Devolver el número total de elementos en la lista
        return arrayFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEcommerce;
        TextView textNameEcommerce;
        TextView textPriceEcommerce;

        public ViewHolder(View itemView) {
            super(itemView);
            imgEcommerce = itemView.findViewById(R.id.imgEcommerce);
            textNameEcommerce = itemView.findViewById(R.id.textNameEcommerce);
            textPriceEcommerce = itemView.findViewById(R.id.textPriceEcommerce);
        }
    }
}
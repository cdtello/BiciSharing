package com.example.bicisharing.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bicisharing.Entities.Product;
import com.example.bicisharing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> arrayProducts;

    public ProductAdapter(Context context, ArrayList<Product> arrayProducts) {
        this.context = context;
        this.arrayProducts = arrayProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Crear una instancia de ViewHolder inflando el diseño de un elemento de lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ecommerce_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Vincular los datos del elemento de la lista con los elementos de la vista en ViewHolder
        Product product = arrayProducts.get(position);
        holder.textNameEcommerce.setText(product.getName());
        holder.textPriceEcommerce.setText(product.getPrice());
        String imageUrl = product.getImage();
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error)  // Recurso drawable de imagen de error
                .into(holder.imgEcommerce);
    }

    @Override
    public int getItemCount() {
        // Devolver el número total de elementos en la lista
        return arrayProducts.size();
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

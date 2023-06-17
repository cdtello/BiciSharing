package com.example.bicisharing.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bicisharing.Entities.Event;
import com.example.bicisharing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> arrayEvents;

    public EventAdapter(Context context, ArrayList<Event> arrayEvents) {
        this.context = context;
        this.arrayEvents = arrayEvents;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Crear una instancia de ViewHolder inflando el diseño de un elemento de lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_template, parent, false);
        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        // Vincular los datos del elemento de la lista con los elementos de la vista en ViewHolder
        Event event = arrayEvents.get(position);
        Log.i("testEvent", event.toString());
        Log.i("testEvent", event.getName());
        Log.i("testEvent", event.getDescription());
        Log.i("testEvent", event.getImage());
        holder.textNameEvent.setText(event.getName());
        holder.textDescriptionEvent.setText(event.getDescription());
        holder.textTimeEvent.setText(event.getTime());
        String imageUrl = event.getImage();
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error)  // Recurso drawable de imagen de error
                .into(holder.imgEvent);
    }

    @Override
    public int getItemCount() {
        // Devolver el número total de elementos en la lista
        return arrayEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEvent;
        TextView textNameEvent;
        TextView textDescriptionEvent;
        TextView textTimeEvent;

        public ViewHolder(View itemView) {
            super(itemView);
            imgEvent = itemView.findViewById(R.id.imgEvent);
            textNameEvent = itemView.findViewById(R.id.textNameEvent);
            textDescriptionEvent = itemView.findViewById(R.id.textDescriptionEvent);
            textTimeEvent = itemView.findViewById(R.id.textTimeEvent);
        }
    }
}
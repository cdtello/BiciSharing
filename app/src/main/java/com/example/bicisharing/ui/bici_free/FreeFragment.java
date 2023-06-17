package com.example.bicisharing.ui.bici_free;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicisharing.Adapters.EventAdapter;
import com.example.bicisharing.Adapters.FoodAdapter;
import com.example.bicisharing.Adapters.ProductAdapter;
import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.Event;
import com.example.bicisharing.Entities.Food;
import com.example.bicisharing.Entities.Product;
import com.example.bicisharing.R;
import com.example.bicisharing.Services.ProductService;
import com.example.bicisharing.databinding.FragmentFreeBinding;

import java.util.ArrayList;

public class FreeFragment extends Fragment {

    private FragmentFreeBinding binding;

    private ProductAdapter productAdapter;
    private ArrayList<Product> arrayProducts;
    private EventAdapter eventAdapter;
    private ArrayList<Event> arrayEvents;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> arrayFood;

    private DBHelper dbHelper;
    private ProductService productService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        productService = new ProductService();
        dbHelper = new DBHelper(getActivity());



        View view = inflater.inflate(R.layout.fragment_free, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listViewEcommerceFree);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Cursor cursor = dbHelper.getItems("products");
        arrayProducts = productService.cursorToArrayListProduct(cursor);
        productAdapter = new ProductAdapter(getActivity(), arrayProducts);
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager layoutManagerEvents = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewEvents = view.findViewById(R.id.listViewEventsFree);
        recyclerViewEvents.setLayoutManager(layoutManagerEvents);
        Cursor cursorEvents = dbHelper.getItems("events");
        Log.i("testEvents", cursorEvents.toString());
        arrayEvents = productService.cursorToArrayListEvents(cursorEvents);
        Log.i("testEvents", arrayEvents.toString());
        eventAdapter = new EventAdapter(getActivity(), arrayEvents);
        recyclerViewEvents.setAdapter(eventAdapter);

        LinearLayoutManager layoutManagerFood = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFood = view.findViewById(R.id.listViewFoodFree);
        recyclerViewFood.setLayoutManager(layoutManagerFood);
        Cursor cursorFood  = dbHelper.getItems("food");
        Log.i("testFood", cursorEvents.toString());
        arrayFood = productService.cursorToArrayListFood(cursorFood);
        Log.i("testFood", arrayFood.toString());
        foodAdapter = new FoodAdapter(getActivity(), arrayFood);
        recyclerViewFood.setAdapter(foodAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.bicisharing.ui.bici_enterprise;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.bicisharing.ProductForm;
import com.example.bicisharing.R;
import com.example.bicisharing.Services.ProductService;
import com.example.bicisharing.databinding.FragmentEnterpriseBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class EnterpriseFragment extends Fragment {

    private Button tvBici;
    private Button tvAccesories;
    private FragmentEnterpriseBinding binding;

    private ProductAdapter productAdapter;
    private EventAdapter eventAdapter;
    private FoodAdapter foodAdapter;


    private ArrayList<Product> arrayProducts;
    private ArrayList<Event> arrayEvents;
    private ArrayList<Food> arrayFood;

    private DBHelper dbHelper;
    private ProductService productService;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        productService = new ProductService();
        dbHelper = new DBHelper(getActivity());

        View view = inflater.inflate(R.layout.fragment_enterprise, container, false);

        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBici = view.findViewById(R.id.listViewEcommerceEnterprise);
        recyclerViewBici.setLayoutManager(layoutManagerProduct);
        Cursor cursorProducts = dbHelper.getItems("products");
        arrayProducts = productService.cursorToArrayListProduct(cursorProducts);
        productAdapter = new ProductAdapter(getActivity(), arrayProducts);
        recyclerViewBici.setAdapter(productAdapter);

        LinearLayoutManager layoutManagerEvents = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewEvents = view.findViewById(R.id.listViewEventsEnterprise);
        recyclerViewEvents.setLayoutManager(layoutManagerEvents);
        Cursor cursorEvents = dbHelper.getItems("events");
        Log.i("testEvents", cursorEvents.toString());
        arrayEvents = productService.cursorToArrayListEvents(cursorEvents);
        Log.i("testEvents", arrayEvents.toString());
        eventAdapter = new EventAdapter(getActivity(), arrayEvents);
        recyclerViewEvents.setAdapter(eventAdapter);

        LinearLayoutManager layoutManagerFood = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFood = view.findViewById(R.id.listViewFoodEnterprise);
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
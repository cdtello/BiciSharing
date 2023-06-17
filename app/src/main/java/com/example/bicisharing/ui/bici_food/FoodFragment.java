package com.example.bicisharing.ui.bici_food;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicisharing.Adapters.ProductAdapter;
import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.Product;
import com.example.bicisharing.R;
import com.example.bicisharing.Services.ProductService;
import com.example.bicisharing.databinding.FragmentFoodBinding;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    private ProductAdapter productAdapter;
    private ArrayList<Product> arrayProducts;
    private DBHelper dbHelper;
    private ProductService productService;

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        productService = new ProductService();
        dbHelper = new DBHelper(getActivity());



        View view = inflater.inflate(R.layout.fragment_food, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listViewEcommerceFood);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Cursor cursor = dbHelper.getItems("products");
        arrayProducts = productService.cursorToArrayListProduct(cursor);
        productAdapter = new ProductAdapter(getActivity(), arrayProducts);
        recyclerView.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
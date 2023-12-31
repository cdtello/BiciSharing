package com.example.bicisharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

import com.example.bicisharing.Adapters.ProductAdapter;
import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.Product;
import com.example.bicisharing.Services.ProductService;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicisharing.databinding.ActivityEnterpriseBinding;

import java.util.ArrayList;

public class Enterprise extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityEnterpriseBinding binding;
    private Button tvBici;
    private Button tvAccesories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityEnterpriseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvBici = (Button) findViewById(R.id.tvBici);
        tvAccesories = (Button) findViewById(R.id.tvEvents);

        tvBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
                intent.putExtra("table", "products");
                startActivity(intent);
            }
        });

        tvAccesories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
                intent.putExtra("table", "events");
                startActivity(intent);
            }
        });
        setSupportActionBar(binding.appBarEnterprise.toolbar);


        binding.appBarEnterprise.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_enterprise);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enterprise, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_enterprise);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
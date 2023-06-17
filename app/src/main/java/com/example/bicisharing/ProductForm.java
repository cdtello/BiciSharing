package com.example.bicisharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.Product;
import com.example.bicisharing.Services.ProductService;
import com.squareup.picasso.Picasso;

public class ProductForm extends AppCompatActivity {
    private TextView title, timeEvent;
    private Button btnInsert, btnGet, btnUpdate, btnDelete;
    private EditText productName, productDescription, productPrice, productId, productImage;
    private ImageView imgProduct;

    private DBHelper dbHelper;
    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        btnInsert = (Button) findViewById(R.id.btnProductFormInsert);
        btnGet = (Button) findViewById(R.id.btnProductFormGet);
        btnUpdate = (Button) findViewById(R.id.btnProductFormUpdate);
        btnDelete = (Button) findViewById(R.id.btnProductFormDelete);

        productName = (EditText) findViewById(R.id.editNameProductForm);
        productDescription = (EditText) findViewById(R.id.editDescriptionProductForm);
        productPrice = (EditText) findViewById(R.id.editPriceProductForm);
        productId = (EditText) findViewById(R.id.editIdProductForm);
        productImage = (EditText) findViewById(R.id.editImageProductForm);

        imgProduct = (ImageView) findViewById(R.id.imgProductForm);

        title = (TextView) findViewById(R.id.tvTitleForm);
        timeEvent = (TextView) findViewById(R.id.textPriceProductForm);

        dbHelper = new DBHelper(this);
        productService = new ProductService();

        Intent intentIN = getIntent();
        String table = intentIN.getStringExtra("table");
        String string = "";
        switch (table){
            case "products":
                string = "ECOMMERCE";
                break;
            case "events":
                string = "EVENTOS";
                timeEvent.setText("Fecha");
                break;
            case "food":
                string = "MENÚS";
                break;
            default:
                break;
        }
        title.setText("GESTION " + string);


        productImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Picasso.get()
                            .load(productImage.getText().toString())
                            .error(R.drawable.error)  // Recurso drawable de imagen de error
                            .into(imgProduct);
                }
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addItem(table,
                        productName.getText().toString(),
                        productDescription.getText().toString(),
                        productImage.getText().toString(),
                        productPrice.getText().toString()
                );
                Intent intent = new Intent(getApplicationContext(), Enterprise.class);
                startActivity(intent);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.getItem(table, productId.getText().toString().trim());
                Log.i("testCursor", cursor.toString());
                Product product = productService.cursorToArrayListProduct(cursor).get(0);

                productName.setText(product.getName());
                productDescription.setText(product.getDescription());
                productPrice.setText(product.getPrice());
                productImage.setText(product.getImage());
                Picasso.get()
                        .load(product.getImage())
                        .error(R.drawable.error)  // Recurso drawable de imagen de error
                        .into(imgProduct);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateItem(table,
                        productId.getText().toString(),
                        productName.getText().toString(),
                        productDescription.getText().toString(),
                        productImage.getText().toString(),
                        productPrice.getText().toString()
                );
                Toast.makeText(getApplicationContext(), "Elemento Actualizado Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteItemById(productId.getText().toString().trim(), table);
                Toast.makeText(getApplicationContext(), "Elemento Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
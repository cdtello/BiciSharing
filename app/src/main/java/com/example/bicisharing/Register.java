package com.example.bicisharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.User;

public class Register extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button btnRegister;
    private EditText editEmailRegister, editPassRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);
        btnRegister = (Button) findViewById(R.id.btnLoginRegister);
        editEmailRegister = (EditText) findViewById(R.id.editEmailRegister);
        editPassRegister = (EditText) findViewById(R.id.editPassRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agregar un usuario
                User user = new User(editEmailRegister.getText().toString(), editPassRegister.getText().toString(), User.Role.FOOD);
                dbHelper.addUser(user);

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
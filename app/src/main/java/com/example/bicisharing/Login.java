package com.example.bicisharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bicisharing.DB.DBHelper;
import com.example.bicisharing.Entities.User;

public class Login extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button btnLogin, btnLoginRegister;
    private EditText editEmailLogin, editPassLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLoginRegister = (Button) findViewById(R.id.btnLoginRegister);
        editEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        editPassLogin = (EditText) findViewById(R.id.editPassLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get User by Email
                User user = dbHelper.getUserByEmail(editEmailLogin.getText().toString());
                if (user != null) {
                    String userId = user.getId();
                    String userEmail = user.getEmail();
                    String userPassword = user.getPassword();
                    User.Role userRole = user.getRole();

                    if(editPassLogin.getText().toString().equals(userPassword)){
                        Intent intent;
                        Toast.makeText(getApplicationContext(), userRole.toString(), Toast.LENGTH_SHORT).show();
                        switch (userRole){
                            case FREE:
                                intent = new Intent(Login.this, Free.class);
                                break;
                            case ENTERPRISE:
                                intent = new Intent(Login.this, Enterprise.class);
                                break;
                            case FOOD:
                                intent = new Intent(Login.this, Food.class);
                                break;
                            default:
                                intent = new Intent(Login.this, Free.class);
                                break;
                        }
                        startActivity(intent);
                        finish();
                    } else{
                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario no Existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
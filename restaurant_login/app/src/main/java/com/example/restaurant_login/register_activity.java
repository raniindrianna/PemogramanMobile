package com.example.restaurant_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register_activity extends AppCompatActivity {
    Button btnSignUp;
    EditText addUsername, addEmail, addPassword, addCpassword, addPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addUsername = findViewById(R.id.addUsername);
        addEmail = findViewById(R.id.add_Email);
        addPassword = findViewById(R.id.addPassword);
        addCpassword = findViewById(R.id.addCPassword);
        addPhone = findViewById(R.id.add_phone);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addUsername.getText().toString()) || TextUtils.isEmpty(addEmail.getText().toString())
                        || TextUtils.isEmpty(addPassword.getText().toString()) || TextUtils.isEmpty(addPassword.getText().toString())
                        || TextUtils.isEmpty(addPhone.getText().toString())) {
                    String message = "All inputs require...";
                    Toast.makeText(register_activity.this,message,Toast.LENGTH_LONG).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setUsername(addUsername.getText().toString());
                    registerRequest.setEmail(addEmail.getText().toString());
                    registerRequest.setPassword(addPassword.getText().toString());
                    registerRequest.setPhone(addPhone.getText().toString());
                    registerUser(registerRequest);
                }

            }
        });

    }


    public void registerUser(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()) {
                    String message = "Successfull";
                    Toast.makeText(register_activity.this, message,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(register_activity.this, login_activity.class));

                    finish();
                }else {
                    String message = "an error occured. please try again";
                    Toast.makeText(register_activity.this, message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(register_activity.this, message, Toast.LENGTH_LONG).show();

            }
        });
    }

}
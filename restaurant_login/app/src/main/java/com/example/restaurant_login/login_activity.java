package com.example.restaurant_login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login_activity extends AppCompatActivity {
    Button btnLogin;
    EditText addUsername, addPass;
    TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        addUsername = findViewById(R.id.addUsername);
        addPass = findViewById(R.id.addPassword);
        noAccount = findViewById(R.id.tvCreateAccount);


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this, register_activity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addUsername.getText().toString())
                        || TextUtils.isEmpty(addPass.getText().toString())) {
                    String message = "All input required";
                    Toast.makeText(login_activity.this, message,Toast.LENGTH_LONG).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(addUsername.getText().toString());
                    loginRequest.setPassword(addPass.getText().toString());
                    loginRequest(loginRequest);
                }
            }
        });
    }
    public void loginRequest(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(login_activity.this,MainActivity.class)
                            .putExtra("data",loginResponse));

                    finish();

                }else {

                    String message = "An error occurred please try again";
                    Toast.makeText(login_activity.this,message,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(login_activity.this,message,Toast.LENGTH_LONG).show();
            }
        });

    }
}


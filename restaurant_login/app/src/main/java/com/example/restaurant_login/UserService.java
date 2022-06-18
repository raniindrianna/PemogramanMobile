package com.example.restaurant_login;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("login")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse>registerUser(@Body RegisterRequest registerRequest);
}

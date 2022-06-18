package com.example.mytasklistnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class activity_splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

//        // menghilangkan action bar
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);


       // final Handler handler = new Handler();
       new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent home = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(home);
                finish();
            }
        }, 3000L);
    }
}
package com.company.lesson17client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvLoginWelcome;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle  savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvLoginWelcome = findViewById(R.id.tvLoginWelcome);
        Intent intent =  getIntent();
        String name = intent.getStringExtra("user");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        if (name != null) {
            tvLoginWelcome.setText("Приветствуем, " + name);
        } else {
            tvLoginWelcome.setText("Что пошло не так...");
        }
    }
}
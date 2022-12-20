package com.example.muchikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.muchikapp.databinding.ActivityBienvenida1Binding;
import com.example.muchikapp.databinding.ActivityBienvenida2Binding;

public class Bienvenida2 extends AppCompatActivity {
    ActivityBienvenida2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBienvenida2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rlPantalla.setOnClickListener(v -> {
            startActivity(new Intent(Bienvenida2.this, Bienvenida3.class));
            finish();
        });
    }
}
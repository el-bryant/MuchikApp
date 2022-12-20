package com.example.muchikapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.muchikapp.databinding.ActivityBienvenida1Binding;

public class Bienvenida1 extends AppCompatActivity {
    ActivityBienvenida1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBienvenida1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rlPantalla.setOnClickListener(v -> {
            startActivity(new Intent(Bienvenida1.this, Bienvenida2.class));
            finish();
        });
    }
}
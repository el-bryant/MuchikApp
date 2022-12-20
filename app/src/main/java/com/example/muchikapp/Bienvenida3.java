package com.example.muchikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.muchikapp.databinding.ActivityBienvenida3Binding;

public class Bienvenida3 extends AppCompatActivity {
    ActivityBienvenida3Binding binding;
    PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBienvenida3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefUtil = new PrefUtil(this);
        binding.rlPantalla.setOnClickListener(v -> {
            prefUtil.saveGenericValue("primerAcceso", "no");
            startActivity(new Intent(Bienvenida3.this, Iniciar.class));
            finish();
        });
    }
}
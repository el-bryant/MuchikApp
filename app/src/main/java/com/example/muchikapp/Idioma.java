package com.example.muchikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Idioma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);
    }

    public void irIniciar(View vista){
        Intent intent = new Intent(Idioma.this,Iniciar.class);
        startActivity(intent);
    }
}
package com.example.muchikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Locale;

public class Idioma extends AppCompatActivity {
    Button btnCastellano, btnIngles;
    PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);
        prefUtil = new PrefUtil(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
        }
        btnCastellano = (Button) findViewById(R.id.btnCastellano);
        btnIngles = (Button) findViewById(R.id.btnIngles);
        btnCastellano.setOnClickListener(v -> {
            Locale localizacion = new Locale("es", "ES");
            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            irIniciar();
        });
        btnIngles.setOnClickListener(v -> {
            Locale localizacion = new Locale("en", "EN");
            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            irIniciar();
        });
    }

    public void irIniciar() {
        if (prefUtil.getStringValue("primerAcceso").equals("no")) {
            startActivity(new Intent(Idioma.this, Iniciar.class));
        } else {
            startActivity(new Intent(Idioma.this, Bienvenida1.class));
        }
        finish();
    }
}
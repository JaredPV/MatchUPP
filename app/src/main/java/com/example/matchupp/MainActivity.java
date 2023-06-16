package com.example.matchupp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_comienza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_comienza = findViewById(R.id.btn_recuperar);
        getSharedPreferences("preferenciasLogin", MODE_PRIVATE);
        boolean sesion = getSharedPreferences("preferenciasLogin", MODE_PRIVATE).getBoolean("sesion", false);
        if(sesion){
            startActivity(new Intent(MainActivity.this, MenuActivity.class));
        }
        btn_comienza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
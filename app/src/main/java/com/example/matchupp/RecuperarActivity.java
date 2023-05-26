package com.example.matchupp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RecuperarActivity extends AppCompatActivity {
    private ImageView iv_back2;
    private Button btn_recuperar;
    private EditText et_mail2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        et_mail2 = findViewById(R.id.et_mail2);
        iv_back2 = findViewById(R.id.iv_back2);
        iv_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecuperarActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btn_recuperar = findViewById(R.id.btn_recuperar);
        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Recuperar contraseña")
                        .setMessage("Si el correo "+et_mail2.getText().toString()+" es correcto y corresponde a una cuenta, se enviará un correo para recuperar su contraseña")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in = new Intent(RecuperarActivity.this, LoginActivity.class);
                                startActivity(in);
                            }
                        })
                        .show();

            }
        });

    }
}
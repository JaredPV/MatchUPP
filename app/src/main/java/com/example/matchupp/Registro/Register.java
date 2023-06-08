package com.example.matchupp.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.matchupp.LoginActivity;
import com.example.matchupp.MenuActivity;
import com.example.matchupp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private Button btn_registrar, btn_cancelar;
    private EditText et_nombre, et_apellido, et_correo, et_pass, et_apodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_registrar = findViewById(R.id.btn_registrarme_register);
        btn_cancelar = findViewById(R.id.btn_cancelar);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_correo = findViewById(R.id.et_mail_register);
        et_pass = findViewById(R.id.et_pass);
        et_apodo = findViewById(R.id.et_apodo);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaciones()) {
                    Intent i = new Intent(Register.this, MenuActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    public boolean validaciones() {
        if (et_nombre.getText().toString().isEmpty()) {
            et_nombre.setError("Campo obligatorio");
            return false;
        }
        if (et_apellido.getText().toString().isEmpty()) {
            et_apellido.setError("Campo obligatorio");
            return false;
        }
        if (et_correo.getText().toString().isEmpty()) {
            et_correo.setError("Campo obligatorio");
            return false;
        } else {
            if (!et_correo.getText().toString().endsWith("@micorreo.upp.edu.mx")) {
                et_correo.setError("Ingresa una dirección de correo institucional");
                return false;
            }
            if (et_pass.getText().toString().isEmpty()) {
                et_pass.setError("Campo obligatorio");
                return false;
            }
            if (et_apodo.getText().toString().isEmpty()) {
                et_apodo.setError("Campo obligatorio");
                return false;
            }
            return true;
        }
    }
}
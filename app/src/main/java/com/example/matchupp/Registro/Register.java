package com.example.matchupp.Registro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.matchupp.LoginActivity;
import com.example.matchupp.MenuActivity;
import com.example.matchupp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private ImageView iv_passIcon, iv_passConIcon;
    private Button btn_registrar, btn_cancelar;
    private EditText et_nombre, et_apellido, et_correo, et_pass, et_apodo, et_pass_confirm;


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
        et_pass_confirm = findViewById(R.id.et_pass_confirm);
        et_apodo = findViewById(R.id.et_apodo);
        iv_passIcon = findViewById(R.id.passwordIcon);
        iv_passConIcon = findViewById(R.id.passwordConIcon);

        iv_passIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pass.getInputType() == 129) {
                    et_pass.setInputType(1);
                    iv_passIcon.setImageResource(R.drawable.eye_open);
                } else {
                    et_pass.setInputType(129);
                    iv_passIcon.setImageResource(R.drawable.eye_closed);
                }
            }
        });

        iv_passConIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pass_confirm.getInputType() == 129) {
                    et_pass_confirm.setInputType(1);
                    iv_passConIcon.setImageResource(R.drawable.eye_open);
                } else {
                    et_pass_confirm.setInputType(129);
                    iv_passConIcon.setImageResource(R.drawable.eye_closed);
                }
            }
        });

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
                    registrar_usuario(et_nombre.getText().toString(), et_apellido.getText().toString(),et_apodo.getText().toString(), et_correo.getText().toString(), et_pass.getText().toString());
                }
            }
        });


    }

    private boolean validaciones() {
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
            }else{
                if(!et_pass.getText().toString().equals(et_pass_confirm.getText().toString())){
                    et_pass_confirm.setError("Las contraseñas no coinciden");
                    return false;
                }
            }
            if (et_apodo.getText().toString().isEmpty()) {
                et_apodo.setError("Campo obligatorio");
                return false;
            }
            return true;
        }
    }
    private void registrar_usuario(final String nombre, final String apellido, final String apodo, final String correo, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, response -> {
            if(response.equals("Conexion exitosaSuccess")){
                startActivity(new Intent(Register.this, LoginActivity.class));
            }else {
                new AlertDialog.Builder(Register.this)
                        .setTitle("Error")
                        .setMessage(response)
                        .setPositiveButton("Aceptar", null)
                        .create().show();
            }
        }, error -> {
            Snackbar.make(btn_registrar, "Error de conexión", Snackbar.LENGTH_LONG).show();
            Log.d("VOLLEY", error.getMessage());
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("name", nombre);
                params.put("lastname", apellido);
                params.put("nickname", apodo);
                params.put("email", correo);
                params.put("pass", pass);
                return params;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

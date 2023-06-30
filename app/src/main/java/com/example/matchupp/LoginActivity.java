package com.example.matchupp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.matchupp.Registro.Endpoints;
import com.example.matchupp.Registro.Register;
import com.example.matchupp.Registro.VolleySingleton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.ktx.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ImageView iv_back, iv_passIcon;
    private TextView tv_recuperar;
    private Button btn_iniciar, btn_registrar;
    private EditText et_correo, et_pass;
    private String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_correo = findViewById(R.id.et_mail);
        et_pass = findViewById(R.id.et_pass);
        iv_passIcon = findViewById(R.id.passwordIcon);
        iv_back = findViewById(R.id.iv_back);
        tv_recuperar = findViewById(R.id.tv_olvide);
        btn_registrar = findViewById(R.id.btn_registrarme);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        recuperarUsuario();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

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
        tv_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RecuperarActivity.class);
                startActivity(i);
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Register.class);
                startActivity(i);
            }
        });
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_correo.getText().toString().isEmpty() || et_pass.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Ingresa todos los campos", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!et_correo.getText().toString().endsWith("@micorreo.upp.edu.mx")) {
                    Snackbar.make(v, "Ingresa una dirección de correo válida", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                signIn(et_correo.getText().toString(), et_pass.getText().toString());

            }
        });
    }
    private void signIn(String email, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.optBoolean("success");
                    if (success) {
                        guardarUsuario(jsonObject);
                        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(i);
                    } else {
                        String errorMessage = "Usuario o contraseña incorrectos";
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Error")
                                .setMessage(errorMessage)
                                .setPositiveButton("Aceptar", null)
                                .show();
                        //Snackbar.make(btn_iniciar, "Usuario o contraseña incorrectos", Snackbar.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Snackbar.make(btn_iniciar, "Error de formato JSON en la respuesta", Snackbar.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(btn_iniciar, "Error de conexión", Snackbar.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("pass", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void guardarUsuario(JSONObject jsonObject) {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", Integer.parseInt(jsonObject.optString("id")));
        editor.putString("first_name", jsonObject.optString("first_name"));
        editor.putString("last_name", jsonObject.optString("last_name"));
        editor.putString("nickname", jsonObject.optString("nickname"));
        editor.putString("email", jsonObject.optString("email"));
        editor.putString("pass", jsonObject.optString("pass"));
        editor.putBoolean("sesion", jsonObject.optBoolean("success"));
        editor.apply();
    }

    private void recuperarUsuario(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        if(preferences.getBoolean("sesion",false)){
            Intent i = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(i);
        }

    }
}

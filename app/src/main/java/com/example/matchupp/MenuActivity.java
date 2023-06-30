package com.example.matchupp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.example.matchupp.Herramientas.HerramientasActivity;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int selectedTab=1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private LinearLayout heartLayout, chatsLayout, profileLayout;
    private LottieAnimationView heartAnimation, chatsAnimation, profileAnimation;
    private TextView heartTxt, chatTxt, profileTxt;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sharedPreferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        //Enlace de cada uno de los LinearLayouts necesarios
        heartLayout = findViewById(R.id.heartLayout);
        chatsLayout = findViewById(R.id.chatsLayout);
        profileLayout =findViewById(R.id.profileLayout);

        //Enlace de cada uno de las vistas de Lottie
        heartAnimation=findViewById(R.id.heartAnimation);
        chatsAnimation=findViewById(R.id.chatsAnimation);
        profileAnimation=findViewById(R.id.profileAnimation);

        //Enlace de cada uno de los campos de texto del menú
        heartTxt=findViewById(R.id.heartTxt);
        chatTxt=findViewById(R.id.chatsTxt);
        profileTxt=findViewById(R.id.profileTxt);

        heartLayout.setBackgroundResource(R.drawable.menu);
        heartTxt.setVisibility(View.VISIBLE);
        heartAnimation.playAnimation();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        heartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeartClick();
            }
        });

        chatsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChatsClick();
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileClick();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateDrawer();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.tool_open,R.string.tool_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_principal);
        }


    }
    public void onHeartClick(){
        if(selectedTab!=1){

            //Se colocan invisibles los textos
            chatTxt.setVisibility(View.GONE);
            profileTxt.setVisibility(View.GONE);


            //Se cancelan las animaciones
            chatsAnimation.cancelAnimation();
            profileAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            chatsLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            heartLayout.setBackgroundResource(R.drawable.menu);
            heartTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            heartLayout.startAnimation(scaleAnimation);
            heartAnimation.playAnimation();
            selectedTab=1;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        }
    }

    private void onChatsClick() {
        if(selectedTab!=2){

            //Se colocan invisibles los textos
            heartTxt.setVisibility(View.GONE);
            profileTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            heartAnimation.cancelAnimation();

            profileAnimation.cancelAnimation();

            //Se quitan los fondos de cada uno de los linear layout
            heartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            //Se destaca el elemento seleccionado
            chatsLayout.setBackgroundResource(R.drawable.menu);
            chatTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            chatsLayout.startAnimation(scaleAnimation);
            chatsAnimation.playAnimation();
            selectedTab=2;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ChatsFragment()).commit();
        }
    }

    private void onProfileClick() {
        if(selectedTab!=3){
            //Se colocan invisibles los textos
            chatTxt.setVisibility(View.GONE);
            heartTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            chatsAnimation.cancelAnimation();
            heartAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            chatsLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            heartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            profileLayout.setBackgroundResource(R.drawable.menu);
            profileTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            profileLayout.startAnimation(scaleAnimation);
            profileAnimation.playAnimation();

            selectedTab=3;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new PerfilFragment()).commit();
        }
    }
    private void cerrarSesion() {

        // Limpiar preferencias
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Lanzar la actividad de inicio de sesión
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_principal:
                onHeartClick();
                break;
            case R.id.nav_chat:
                onChatsClick();
                break;
            case R.id.nav_perfil:
                onProfileClick();
                break;
            case R.id.nav_herramientas:
                Intent intent = new Intent(this, HerramientasActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_logout:
                cerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateDrawer(){
        String first_name = sharedPreferences.getString("first_name", "Perfil");
        String last_name = sharedPreferences.getString("last_name", "MatchUPP");
        String email = sharedPreferences.getString("email", "ejemplo@micorreo.upp.edu.mx");
        View updateDrawer = navigationView.getHeaderView(0);
        TextView nombre = updateDrawer.findViewById(R.id.tvUsuario_drawer);
        TextView correo = updateDrawer.findViewById(R.id.tvcorreoUsurio_drawer);
        ImageView foto = updateDrawer.findViewById(R.id.IvfotoPerfil_drawer);

        nombre.setText(first_name + " " + last_name);
        correo.setText(email);
    }

}
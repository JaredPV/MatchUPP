package com.example.matchupp.Herramientas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.matchupp.HomeFragment;
import com.example.matchupp.MenuActivity;
import com.example.matchupp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HerramientasActivity extends AppCompatActivity {
    private int selectedTab=1;
    private FloatingActionButton fab_regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas);
        final LinearLayout calculatorLayout = findViewById(R.id.calculatorLayout);
        final LinearLayout converterLayout = findViewById(R.id.converterLayout);
        final LinearLayout tasksLayout = findViewById(R.id.tasksLayout);

        final LottieAnimationView calculatorAnimation = findViewById(R.id.calculatorAnimation);
        final LottieAnimationView converterAnimation = findViewById(R.id.converterAnimation);
        final LottieAnimationView tasksAnimation = findViewById(R.id.tasksAnimation);

        final TextView calculatorTxt = findViewById(R.id.calculatorTxt);
        final TextView converterTxt = findViewById(R.id.converterTxt);
        final TextView tasksTxt = findViewById(R.id.tasksTxt);

        fab_regresar = findViewById(R.id.fab_regresar);
        fab_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HerramientasActivity.this, MenuActivity.class));
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.frameLayoutTools, CalculadoraFragment.class,null)
                .commit();
        calculatorLayout.setBackgroundResource(R.drawable.menu);
        calculatorTxt.setVisibility(View.VISIBLE);
        ScaleAnimation anim = new ScaleAnimation(0.8f, 1.0f, 1.f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        anim.setDuration(200);
        anim.setFillAfter(true);
        calculatorLayout.startAnimation(anim);
        calculatorAnimation.playAnimation();
        calculatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 1) {

                    //Se colocan invisibles los textos
                    tasksTxt.setVisibility(View.GONE);
                    converterTxt.setVisibility(View.GONE);

                    //Se cancelan las animaciones
                    tasksAnimation.cancelAnimation();
                    calculatorAnimation.cancelAnimation();

                    //Se quitan los fondos de cada uno de los linear layout
                    tasksLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    converterLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    //Se destaca el elemento seleccionado
                    calculatorLayout.setBackgroundResource(R.drawable.menu);
                    calculatorTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1.f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    calculatorLayout.startAnimation(scaleAnimation);
                    calculatorAnimation.playAnimation();

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.frameLayoutTools, CalculadoraFragment.class,null)
                            .commit();

                    selectedTab = 1;

                }

            }
        });

        tasksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 2) {
                    //Se colocan invisibles los textos
                    calculatorTxt.setVisibility(View.GONE);
                    converterTxt.setVisibility(View.GONE);

                    //Se cancelan las animaciones
                    calculatorAnimation.cancelAnimation();
                    converterAnimation.cancelAnimation();

                    //Se quitan los fondos de cada uno de los linear layout
                    calculatorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    converterLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    //Se destaca el elemento seleccionado
                    tasksLayout.setBackgroundResource(R.drawable.menu);
                    tasksTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1.f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    tasksLayout.startAnimation(scaleAnimation);
                    tasksAnimation.playAnimation();

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.frameLayoutTools, TareasFragment.class,null)
                            .commit();

                    selectedTab = 2;
                }
            }
        });

        converterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 3) {
                    //Se colocan invisibles los textos
                    calculatorTxt.setVisibility(View.GONE);
                    tasksTxt.setVisibility(View.GONE);
                    //Se cancelan las animaciones
                    calculatorAnimation.cancelAnimation();
                    tasksAnimation.cancelAnimation();

                    //Se quitan los fondos de cada uno de los linear layout
                    calculatorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    tasksLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    //Se destaca el elemento seleccionado
                    converterLayout.setBackgroundResource(R.drawable.menu);
                    converterTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1.f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    converterLayout.startAnimation(scaleAnimation);
                    converterAnimation.playAnimation();

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.frameLayoutTools, ConvertidorFragment.class,null)
                            .commit();

                    selectedTab = 3;

                }
            }
        });
    }
}
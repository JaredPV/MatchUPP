package com.example.matchupp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MenuActivity extends AppCompatActivity {

    private int selectedTab=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Enlace de cada uno de los LinearLayouts necesarios
        final LinearLayout heartLayout = findViewById(R.id.heartLayout);
        final LinearLayout listaLayout = findViewById(R.id.listaLayout);
        final LinearLayout menuLayout = findViewById(R.id.menuLayout);
        final LinearLayout chatLayout=findViewById(R.id.chatLayout);

        //Enlace de cada uno de las vistas de Lottie
        final LottieAnimationView heartAnimation=findViewById(R.id.heartAnimation);
        final LottieAnimationView listaAnimation=findViewById(R.id.listaAnimation);
        final LottieAnimationView menuAnimation=findViewById(R.id.menuAnimation);
        final LottieAnimationView chatAnimation=findViewById(R.id.chatAnimation);

        //Enlace de cada uno de los campos de texto del menú
        final TextView heartTxt=findViewById(R.id.heartTxt);
        final TextView listaTxt=findViewById(R.id.listaTxt);
        final TextView menuTxt=findViewById(R.id.menuTxt);
        final TextView chatTxt=findViewById(R.id.chatTxt);



        listaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTab!=1){

                    //Se colocan invisibles los textos
                    heartTxt.setVisibility(View.GONE);

                    menuTxt.setVisibility(View.GONE);
                    chatTxt.setVisibility(View.GONE);

                    //Se cancelan las animaciones
                    heartAnimation.cancelAnimation();

                    menuAnimation.cancelAnimation();
                    chatAnimation.cancelAnimation();

                    //Se quitan los fondos de cada uno de los linear layout
                    heartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    chatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    //Se destaca el elemento seleccionado
                    listaLayout.setBackgroundResource(R.drawable.menu);
                    listaTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    listaLayout.startAnimation(scaleAnimation);
                    listaAnimation.playAnimation();

                    selectedTab=1;

                }

            }
        });

        heartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTab!=2){

                    //Se colocan invisibles los textos
                    menuTxt.setVisibility(View.GONE);
                    chatTxt.setVisibility(View.GONE);
                    listaTxt.setVisibility(View.GONE);


                    //Se cancelan las animaciones
                    menuAnimation.cancelAnimation();
                    chatAnimation.cancelAnimation();
                    listaAnimation.cancelAnimation();


                    //Se quitan los fondos de cada uno de los linear layout
                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    chatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    listaLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    //Se destaca el elemento seleccionado
                    heartLayout.setBackgroundResource(R.drawable.menu);
                    heartTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    heartLayout.startAnimation(scaleAnimation);
                    heartAnimation.playAnimation();

                    selectedTab=2;

                }

            }
        });

        chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(selectedTab!=3){



                    //Se colocan invisibles los textos
                    menuTxt.setVisibility(View.GONE);
                    listaTxt.setVisibility(View.GONE);
                    heartTxt.setVisibility(View.GONE);


                    //Se cancelan las animaciones
                    menuAnimation.cancelAnimation();
                    listaAnimation.cancelAnimation();
                    heartAnimation.cancelAnimation();


                    //Se quitan los fondos de cada uno de los linear layout
                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    listaLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    heartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    //Se destaca el elemento seleccionado
                    chatLayout.setBackgroundResource(R.drawable.menu);
                    chatTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    chatLayout.startAnimation(scaleAnimation);
                    chatAnimation.playAnimation();

                    selectedTab=3;

                }

            }
        });

        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTab!=4){



                    //Se colocan invisibles los textos
                    chatTxt.setVisibility(View.GONE);
                    heartTxt.setVisibility(View.GONE);
                    listaTxt.setVisibility(View.GONE);


                    //Se cancelan las animaciones
                    chatAnimation.cancelAnimation();
                    heartAnimation.cancelAnimation();
                    listaAnimation.cancelAnimation();


                    //Se quitan los fondos de cada uno de los linear layout
                    chatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    heartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    listaAnimation.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    //Se destaca el elemento seleccionado
                    menuLayout.setBackgroundResource(R.drawable.menu);
                    menuTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    menuLayout.startAnimation(scaleAnimation);
                    menuAnimation.playAnimation();

                    selectedTab=4;

                }

            }
        });

    }
}
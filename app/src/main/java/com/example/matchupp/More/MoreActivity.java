package com.example.matchupp.More;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.matchupp.R;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        if (savedInstanceState == null) {
            // Establecer el fragmento inicial ("MyGenderFragment")
            MyGenderFragment myGenderFragment = new MyGenderFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_more, myGenderFragment)
                    .commit();
        }
    }
}
package com.example.projetle1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PagePrincipale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_principale);

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);

        BottomNavigationMenu.setupBottomMenu(mBottomNavigationView,this);

    }
}
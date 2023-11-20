package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);
            finish();
        }

        BottomNavigationMenu.setupBottomMenu(mBottomNavigationView,this);

    }

}

//menu_pp
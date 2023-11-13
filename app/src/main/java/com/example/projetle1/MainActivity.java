package com.example.projetle1;

import android.annotation.SuppressLint;
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

    private TextView tvContenName;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.user_details);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        tvContenName=findViewById(R.id.content_name);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());

            mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                switch (item.getItemId()){
                    case R.id.home:
                        tvContenName.setText(R.string.tv_str_content_home);
                        return true;
                    case R.id.account:
                        tvContenName.setText(R.string.tv_str_account);
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getApplicationContext(), Connexion.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.message:
                        return true;
                }

                return false;
            });
        }
    }

}

//menu_pp
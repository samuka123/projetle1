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

    private TextView tvContenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
WQ                                                                                                                                          setContentView(R.layout.inscription);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        tvContenName=findViewById(R.id.content_name);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);
            finish();
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                tvContenName.setText(R.string.tv_str_content_home);
                return true;
            } else
                tvContenName.setText(R.string.tv_str_message);
                if (itemId == R.id.account) {
                tvContenName.setText(R.string.tv_str_account);
                return true;
            } else return itemId == R.id.message;
        });

    }

}

//menu_pp
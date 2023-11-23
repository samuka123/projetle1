package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projetle1.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);
            finish();
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
                return true;
            } else
            if (itemId == R.id.account) {
                replaceFragment(new CompteFragment());
                return true;
            } else
            if(itemId == R.id.message) {
                replaceFragment(new MessageFragment());
                return true;
            }
            return false;
        });


    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content,fragment);
        fragmentTransaction.commit();
    }
}
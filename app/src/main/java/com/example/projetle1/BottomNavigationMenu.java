package com.example.projetle1;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu extends AppCompatActivity {

    Button ButtonDonation;

    public static void setupBottomMenu(BottomNavigationView bottomNavigationView, final Activity activity) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                Intent intent = new Intent(activity, PagePrincipale.class);
                activity.startActivity(intent);
                activity.finish();
                return true;
            } else
                if (itemId == R.id.account) {
                return true;
            } else
                if(itemId == R.id.message) {
                    return true;
                }
            return false;
        });
    }
}

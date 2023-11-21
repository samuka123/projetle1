package com.example.projetle1;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.AssociationJavaClass.AssociationMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu extends AppCompatActivity {

    public static Button ButtonDonation;

    public static void setupBottomMenu(BottomNavigationView bottomNavigationView, final Activity activity, final Context context) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            Context appContext = context.getApplicationContext();

            if (itemId == R.id.home) {
                ButtonDonation = activity.findViewById(R.id.btn_donation);
                ButtonDonation.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AssociationMain.class);
                        context.startActivity(intent);
                    }

                });
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

package com.example.projetle1;
import android.app.Activity;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu {
    public static void setupBottomMenu(BottomNavigationView bottomNavigationView, final Activity activity, TextView tvContenName) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                tvContenName.setText(R.string.tv_str_content_home);
                return true;
            } else
                if (itemId == R.id.account) {
                    tvContenName.setText(R.string.tv_str_account);
                return true;
            } else
                if(itemId == R.id.message){
                tvContenName.setText(R.string.tv_str_message);
                    return true;
                }
            return false;
        });
    }
}

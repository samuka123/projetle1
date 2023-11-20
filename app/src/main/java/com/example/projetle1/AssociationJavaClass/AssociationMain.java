package com.example.projetle1.AssociationJavaClass;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.BottomNavigationMenu;
import com.example.projetle1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AssociationMain extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_list);


        List<AssociationItem> vAssociationArrayList = new ArrayList<>();
        vAssociationArrayList.add(new AssociationItem("Action contre la faim"));
        vAssociationArrayList.add(new AssociationItem("Medecins sans frontieres"));
        vAssociationArrayList.add(new AssociationItem("Les restos du coeur"));

        ListView vAssociationList = findViewById(R.id.association_list_id);
        vAssociationList.setAdapter(new AssociationItemAdapter(this, vAssociationArrayList));

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationMenu.setupBottomMenu(mBottomNavigationView,this);
    }


}

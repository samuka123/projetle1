package com.example.projetle1.AssociationJavaClass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.BuildConfig;
import com.example.projetle1.R;

import java.util.ArrayList;
import java.util.List;

public class AssociationMain extends AppCompatActivity{

     Button aAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_list);

        aAdd= findViewById(R.id.create_association_button);

        List<AssociationItem> vAssociationArrayList = new ArrayList<>();
        vAssociationArrayList.add(new AssociationItem("Action contre la faim", "Cette association combat la famine notament dans les pays pauvres et en guerre", "https://donner.actioncontrelafaim.org/agir/~mon-don?source=bouton-site&medium=menu", Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID+"/" + R.drawable.item_action)));
        vAssociationArrayList.add(new AssociationItem("Medecins sans frontieres", "Medecins sans frontieres apporte des soins aux personnes, ne pouvant pas payer leurs soins et victimes de phenomenes graves tel que les catastrophes naturelle ou les guerres", "https://soutenir.msf.fr/faire-un-don/~mon-don",Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID+"/" + R.drawable.item_medecin)));
        vAssociationArrayList.add(new AssociationItem("Les restos du coeur", "Les restos du coeur est une association distribuant de la nourriture aux personnes les plus pauvres en France", "https://dons.restosducoeur.org/particulier/~mon-don?gad_source=1&gclid=CjwKCAiAgeeqBhBAEiwAoDDhn56UOzfol7mVpt9WAWHs7AJtkGmySe0i741oc1ftfA0RqDLFfhH_VhoCDmcQAvD_BwE", Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID+"/" + R.drawable.item_restos)));

        ListView vAssociationList = findViewById(R.id.association_list_id);
        vAssociationList.setAdapter(new AssociationItemAdapter(this, vAssociationArrayList));

        vAssociationList.setClickable(true);

        /*vAssociationList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AssociationMain.this,  AssociationDetails.class);
                intent.putExtra("nom", vAssociationArrayList.get(i));
                intent.putExtra("image", 2);
                intent.putExtra("descripton", 3);
                intent.putExtra("url", 4);
                startActivity(intent);
            }
        });*/

        //Un appuie sur le bouton ADD ouvre la fenetre de creation d association
        aAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAssociation.class);
                startActivity(intent);
                finish();
            }
        });
    }


}

//mettre les fonctionnalites de la navigation barre

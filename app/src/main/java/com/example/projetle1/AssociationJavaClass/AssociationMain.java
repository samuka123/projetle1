package com.example.projetle1.AssociationJavaClass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.R;

import java.util.ArrayList;
import java.util.List;

public class AssociationMain extends AppCompatActivity{

    private Button aAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_list);

        aAdd= findViewById(R.id.create_association_button);

        List<AssociationItem> vAssociationArrayList = new ArrayList<>();
        vAssociationArrayList.add(new AssociationItem("Action contre la faim", "https://donner.actioncontrelafaim.org/agir/~mon-don?source=bouton-site&medium=menu"));
        vAssociationArrayList.add(new AssociationItem("Medecins sans frontieres", "https://soutenir.msf.fr/faire-un-don/~mon-don"));
        vAssociationArrayList.add(new AssociationItem("Les restos du coeur", "https://dons.restosducoeur.org/particulier/~mon-don?gad_source=1&gclid=CjwKCAiAgeeqBhBAEiwAoDDhn56UOzfol7mVpt9WAWHs7AJtkGmySe0i741oc1ftfA0RqDLFfhH_VhoCDmcQAvD_BwE"));

        ListView vAssociationList = findViewById(R.id.association_list_id);
        vAssociationList.setAdapter(new AssociationItemAdapter(this, vAssociationArrayList));

        //Un appuie sur le bouton ADD ouvre la fenetre de creation d association
        aAdd.setOnClickListener(v -> {
            Intent nextActivity = new Intent(getApplicationContext(), CreateAssociation.class);
            startActivity(nextActivity);
            finish();
        });
    }


}

//mettre les fonctionnalites de la navigation barre

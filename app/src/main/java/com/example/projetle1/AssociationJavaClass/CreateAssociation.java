package com.example.projetle1.AssociationJavaClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.R;

public class CreateAssociation extends AppCompatActivity {

     ImageButton aCloseButton;
     Button aButtonChooseImage;
     Button aButtonUpload;

     AssociationItem aAssociationObject;

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView aAddImage;
    private TextView aAddName;
    private TextView aAddDescription;
    private TextView aAddUrl;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_association);

        aAssociationObject= new AssociationItem();

        aCloseButton= findViewById(R.id.association_close_button);
        aButtonChooseImage = findViewById(R.id.button_add_association_image);
        aAddImage= findViewById(R.id.add_association_imageview);
        aAddName= findViewById(R.id.add_association_name);

        aButtonUpload = findViewById(R.id.add_association_upload);

        aAddDescription= findViewById(R.id.add_association_description);
        aAddUrl= findViewById(R.id.add_association_url);


        aCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(), AssociationMain.class);
                startActivity(nextActivity);
                finish();
            }
        });


        //aButtonChooseImage.setOnClickListener(v -> {
            //openGallery();
        //});

        aButtonUpload.setOnClickListener(v -> appToDatabase());
    }

    private void openGallery(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()==null) {
            this.aAssociationObject.setLogo(data.getData());
            this.aAddImage.setImageURI(this.aAssociationObject.getLogo());

            //Picasso.with(this).load(selectedImageUri).into(imageView); en ligne
        }
    }

    private void appToDatabase(){
        if(!(this.aAddDescription==null)){
            this.aAssociationObject.setDescription(aAddDescription.getText().toString());
        }

        if(!(this.aAddName==null || this.aAddUrl==null)){
            this.aAssociationObject.setName(aAddName.getText().toString());
            this.aAssociationObject.setUrl(aAddUrl.getText().toString());

            //Envoie des donnees dans la FireBase a modifier si pas bon
            //changer les authorisations pour que utilisateur puisse ecrire et lire dans la database
            //barre de chargement fonctionnalite

            this.aAssociationObject= new AssociationItem();

            this.aAddName.setText("");
            this.aAddDescription.setText("");
            this.aAddUrl.setText("");
            this.aAddImage.setImageResource(R.drawable.item_default);

            Toast.makeText(CreateAssociation.this, "L'association a ete cree", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CreateAssociation.this, "Les champs \"Nom de votre association\" et \"Courte description\" doivent etre remplies", Toast.LENGTH_SHORT).show();

        }
    }
}

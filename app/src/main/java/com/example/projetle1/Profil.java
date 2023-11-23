package com.example.projetle1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profil extends AppCompatActivity {

    CircleImageView imageProfil;
    EditText prenom,nom,email,adresse;
    Button updateprofil;

    FirebaseStorage mStorage;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        imageProfil = findViewById(R.id.img_profil);
        prenom = findViewById(R.id.prenom_profil);
        nom = findViewById(R.id.nom_profil);
        email = findViewById(R.id.email_profil);
        adresse = findViewById(R.id.adresse_profil);
        updateprofil = findViewById(R.id.btn_update_profil);

        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();


        imageProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*" );
                startActivityForResult(intent,33);
            }
        });

        updateprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfilUtilisateur();
            }
        });

    }

    private void updateProfilUtilisateur() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;
        if (data.getData() != null){
            Uri profilUri = data.getData();
            imageProfil.setImageURI(profilUri);

            final StorageReference reference = mStorage.getReference().child("image_profil").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));

            reference.putFile(profilUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Téléverser",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
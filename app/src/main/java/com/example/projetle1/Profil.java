
package com.example.projetle1;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Profil extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private EditText prenom, nom, email, adresse;
    private Button updateprofil;
    private ImageView imageProfil;

    Uri imageUri;
    private FirebaseStorage mStorage;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseFirestore mDB;

    private StorageReference storageReference;

    private DocumentReference documentReference;

    private DatabaseReference databaseReference;
    private All_Utilisateur utilisateur;

    private String utilisateurID;

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
        mDB = FirebaseFirestore.getInstance();

        utilisateur = new All_Utilisateur();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            utilisateurID = user.getUid();
            documentReference = mDB.collection("utilisateurInfo").document(utilisateurID);

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Map<String, Object> data = documentSnapshot.getData();


                    prenom.setText(String.valueOf(data.get("prenom")));
                    nom.setText(String.valueOf(data.get("nom")));
                    email.setText(String.valueOf(data.get("email")));
                    adresse.setText(String.valueOf(data.get("adresse")));

                    String imageUrl = String.valueOf(data.get("imageUrl"));

                    Log.d("Profil", "Image URL from Firestore: " + imageUrl);
                    if (!TextUtils.isEmpty(imageUrl)) {
                        Picasso.get().load(imageUrl).into(imageProfil);
                        Log.d("Profil", "Image loaded successfully");
                   }else{
                        Log.e("Profil", "Image URL is empty or null");
                    }
                }
            }
        });
        } else {
            Log.e("Profil", "FirebaseUser est null");
        }

        storageReference = FirebaseStorage.getInstance().getReference("images profil");
        databaseReference = mDatabase.getReference("All Utilisateurs");

        imageProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        updateprofil.setOnClickListener(v -> uploadData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).into(imageProfil);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erreur" + e, Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExt(Uri uri) {
        if (uri != null) {
            ContentResolver contentResolver = getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        } else {
            return "";
        }
    }

    private void uploadData() {
        String lPrenom = prenom.getText().toString();
        String lNom = nom.getText().toString();
        String lEmail = email.getText().toString();
        String lAdresse = adresse.getText().toString();

        if (!TextUtils.isEmpty(lPrenom) || !TextUtils.isEmpty(lNom) || !TextUtils.isEmpty(lEmail) || !TextUtils.isEmpty(lAdresse)) {
            if (imageUri != null) {
                final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri));

                UploadTask uploadTask = reference.putFile(imageUri);

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }
                        return reference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();

                            Map<String, String> profil = new HashMap<>();
                            profil.put("prenom", lPrenom);
                            profil.put("nom", lNom);
                            profil.put("email", lEmail);
                            profil.put("adresse", lAdresse);
                            profil.put("imageUrl", downloadUri.toString());

                            utilisateur.setPrenom(prenom.getText().toString());
                            utilisateur.setNom(nom.getText().toString());
                            utilisateur.setEmail(email.getText().toString());
                            utilisateur.setAdresse(adresse.getText().toString());
                            utilisateur.setUid(utilisateurID);
                            utilisateur.setUrl(downloadUri.toString());

                            databaseReference.child(utilisateurID).setValue(utilisateur);

                            documentReference.set(profil)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Profil.this, "Profil mis à jour", Toast.LENGTH_SHORT).show();

                                            Handler handler = new Handler();

                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(Profil.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            }, 1000);
                                        }
                                    });
                        } else {
                            Toast.makeText(Profil.this, "Échec de téléchargement de l'image", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Sélectionnez une image d'abord", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Remplissez toutes les informations", Toast.LENGTH_SHORT).show();
        }
    }
}

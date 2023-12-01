package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Inscription extends AppCompatActivity {
    EditText editTextEmail, editTextmdp, editTextmdpConfirmation, editTextNom, editTextPrenom;
    Button buttonInscription;
    FirebaseAuth mAuth;
    FirebaseFirestore mDb;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null ) {
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextmdp = findViewById(R.id.mdp);
        editTextmdpConfirmation = findViewById(R.id.mdp_confirmation);
        editTextNom = findViewById(R.id.nom);
        editTextPrenom = findViewById(R.id.prenom);
        buttonInscription = findViewById(R.id.btn_Inscription);

        textView = findViewById(R.id.ConnexionNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Connexion.class);
                startActivity(intent);
                finish();
            }
        });
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InscriptionForm();
            }
        });
    }

    private void InscriptionForm() {
        String email, mdp, mdpconfirmation, nom, prenom;
        email = String.valueOf(editTextEmail.getText());
        mdp = String.valueOf(editTextmdp.getText());
        mdpconfirmation = String.valueOf(editTextmdpConfirmation.getText());
        nom = String.valueOf(editTextNom.getText());
        prenom = String.valueOf(editTextPrenom.getText());

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom)) {
            Toast.makeText(Inscription.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mdp.length() < 6) {
            editTextmdp.setError("Le mot de passe doit avoir plus de 6 caractères");
            editTextmdp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mdp)) {
            Toast.makeText(Inscription.this, "Mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mdpconfirmation)) {
            editTextmdpConfirmation.setError("La confirmation de mot de passe est nécessaire");
            editTextmdpConfirmation.requestFocus();
            return;
        }

        if (!mdp.equals(mdpconfirmation)) {
            editTextmdpConfirmation.setError("Le mot de passe n'est pas identique");
            editTextmdpConfirmation.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Utilisateur créé avec succès
                            createUserData(nom, prenom, email);
                        } else {
                            Toast.makeText(Inscription.this, "Inscription échouée.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createUserData(String nom, String prenom, String email) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String utilisateurID = user.getUid();

            Map<String, Object> utilisateurInfo = new HashMap<>();
            utilisateurInfo.put("nom", nom);
            utilisateurInfo.put("prenom", prenom);
            utilisateurInfo.put("email", email);
            DocumentReference userRef = mDb.collection("utilisateurInfo").document(utilisateurID);
            userRef.set(utilisateurInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "L'utilisateur est enregistré",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Connexion.class));
                            finish();
                        }
                    });
        } else {
            Log.e("Inscription", "Utilisateur introuvable après la création.");
        }
    }
}
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
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Inscription.class);
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

    private void InscriptionForm(){
        String email, mdp,mdpconfirmation, nom, prenom;
        email = String.valueOf(editTextEmail.getText());
        mdp = String.valueOf(editTextmdp.getText());
        mdpconfirmation = String.valueOf(editTextmdpConfirmation.getText());
        nom = String.valueOf(editTextNom.getText());
        prenom = String.valueOf(editTextPrenom.getText());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Inscription.this, "Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mdp.length() < 6) {
            editTextmdp.setError("Le mot de passe doit avoir plus de 6 caracteres");
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

        Map<String,Object> utilisateurInfo = new HashMap<>();
        utilisateurInfo.put("nom",nom);
        utilisateurInfo.put("prenom",prenom);


        // Ajoutez une vérification pour db
        if (mDb == null) {
            Log.e("Inscription", "L'objet db est null !");
            return; // Arrêtez l'exécution si db est null
        }


        mDb.collection("utilisateurInfo").document("1")
                .set(utilisateurInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                                       Toast.makeText(getApplicationContext(), "The user has been registered ",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Connexion.class));
                        finish();
                    }
                });

        mAuth.createUserWithEmailAndPassword(email,mdp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Inscription.this, "Compte crée.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Connexion.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Inscription.this, "Inscription failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

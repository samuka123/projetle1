package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Connexion extends AppCompatActivity {

    EditText editTextEmail, editTextmdp;
    Button buttonLogin;
    FirebaseAuth mAuth;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextmdp = findViewById(R.id.mdp);
        buttonLogin = findViewById(R.id.btn_login);
        textView = findViewById(R.id.InscriptionNow);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Inscription.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(v -> {
            String email, mdp;
            email = String.valueOf(editTextEmail.getText());
            mdp =String.valueOf(editTextmdp.getText());

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Connexion.this,"Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(mdp)){
                Toast.makeText(Connexion.this, "Mot de passe", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, mdp)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Login succes", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Connexion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });
        });
    }
}
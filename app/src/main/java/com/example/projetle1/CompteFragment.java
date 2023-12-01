package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CompteFragment extends Fragment {

    private FirebaseAuth mAuth;
    private ImageView imgProfil;

    public CompteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compte, container, false);

        Button btnDeconnexion = view.findViewById(R.id.btn_deconnexion);
        Button btnProfil = view.findViewById(R.id.btn_profil);
        imgProfil = view.findViewById(R.id.img_profil_compte);


        if (btnDeconnexion != null && btnProfil != null) {

            btnProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Profil.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });


            btnDeconnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();

                    Intent intent = new Intent(getActivity(), Connexion.class);
                    startActivity(intent);

                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        } else {
            Log.e("CompteFragment", "btn_deconnexion or btn_profil is null");
        }

        // Charger l'image de profil de l'utilisateur s'il est connecté
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            loadUserProfileData(user.getUid());
        }

        return view;
    }

    // Méthode pour charger les données du profil de l'utilisateur
    // ...

    // Méthode pour charger les données du profil de l'utilisateur
    private void loadUserProfileData(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d("CompteFragment", "loadUserProfileData called for userId: " + userId);

        db.collection("utilisateurInfo")
                .document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String imageUrl = document.getString("imageUrl");
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                                Log.d("CompteFragment", "Got imageUrl from Firestore: " + imageUrl);
                                Picasso.get().load(imageUrl).into(imgProfil);
                            } else {
                                Log.d("CompteFragment", "No imageUrl found in Firestore.");
                            }
                        } else {
                            Log.d("CompteFragment", "Document does not exist in Firestore.");
                        }
                    } else {
                        Log.e("CompteFragment", "Error getting user profile data", task.getException());
                    }
                });
    }

}

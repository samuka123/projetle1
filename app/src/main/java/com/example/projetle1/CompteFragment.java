package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class CompteFragment extends Fragment {

    FirebaseAuth mAuth;
    public CompteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_compte, container, false);

        Button btnDeconnexion = view.findViewById(R.id.btn_deconnexion);

        Button btnProfil = view.findViewById(R.id.btn_profil);
        if (btnDeconnexion != null | btnProfil != btnProfil){
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
        }else{
            Log.e("CompteFragment", "btn_deconnexion is null");
        }
        return view;
    }
}
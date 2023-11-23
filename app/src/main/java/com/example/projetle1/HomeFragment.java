package com.example.projetle1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.projetle1.AssociationJavaClass.AssociationMain;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        Button btnAssociation = view.findViewById(R.id.btn_association);
        if (btnAssociation != null){
            btnAssociation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AssociationMain.class);
                    startActivity(intent);
                }
            });
        }else{
            Log.e("HomeFragment", "btn_association is null");
        }

        return view;
    }

}
package com.example.projetle1.AssociationJavaClass;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetle1.R;

import java.util.List;

public class AssociationItemAdapter extends BaseAdapter {

    private  Context acontext;
    private List<AssociationItem> aArrayAssociationList;
    private LayoutInflater ainflater;

    public AssociationItemAdapter(Context pcontext, List<AssociationItem> pArrayAssociationList){
        this.acontext= pcontext;
        this.aArrayAssociationList= pArrayAssociationList;
        this.ainflater= LayoutInflater.from(acontext);
     }

    @Override
    public int getCount() {
        return this.aArrayAssociationList.size();
    }

    @Override
    public AssociationItem getItem(int position) {
        return this.aArrayAssociationList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view= ainflater.inflate(R.layout.fragment_association_item,null);

        AssociationItem vcurrentItem= getItem(i);
        String vName= vcurrentItem.getName();
        Uri vLogo= vcurrentItem.getLogo();

        TextView vNameView= view.findViewById(R.id.fragment_association_name_id);
        ImageView vLogoView= view.findViewById(R.id.fragment_association_logo_id);

        vNameView.setText(vName);
        vLogoView.setImageURI(vLogo);

        return view;
    }
}

package com.example.projetle1.AssociationJavaClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projetle1.R;

import java.util.List;

public class AssociationItemAdapter extends BaseAdapter {

    private Context aContext;
    private List<AssociationItem> aAssociationArrayList;
    private LayoutInflater aInflater;

    public AssociationItemAdapter(Context pContext, List<AssociationItem> pAssociationArrayList){
        this.aContext = pContext;
        this.aAssociationArrayList= pAssociationArrayList;
        this.aInflater= LayoutInflater.from(this.aContext);
    }

    @Override
    public int getCount() {
        return this.aAssociationArrayList.size();
    }

    @Override
    public AssociationItem getItem(int pPosition) {
        return aAssociationArrayList.get(pPosition);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= this.aInflater.inflate(R.layout.activity_association_item, null);

        AssociationItem vCurrentItem= getItem(i);
        TextView vAssociationName= view.findViewById(R.id.association_name_id);
        vAssociationName.setText(vCurrentItem.getName());

        return view;
    }
}

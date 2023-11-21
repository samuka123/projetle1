package com.example.projetle1.AssociationJavaClass;

import android.graphics.Bitmap;

public class AssociationItem {

    private String aName;
    private String aDescription;
    private String aUrl;

    //temporaire
    private Bitmap aLogo;

    public AssociationItem(String pName){
        this.aName= pName;
        this.aDescription= "";
        this.aUrl= "";
        this.aLogo= null;//temporaire

    }

    public AssociationItem(String pName, String pDescription, String pUrl){
        this.aName= pName;
        this.aDescription= pDescription;
        this.aUrl= pUrl;
        this.aLogo= null;//temporaire
    }

    public String getName(){
        return this.aName;
    }
    public void setName(String pName){
        this.aName= pName;
    }

    public String getDescription(){
        return this.aDescription;
    }
    public void setDescription(String pDescription){
        this.aDescription= pDescription;
    }

    public String getUrl(){
        return this.aUrl;
    }
    public void setUrl(String pUrl){
        this.aUrl= pUrl;
    }
}

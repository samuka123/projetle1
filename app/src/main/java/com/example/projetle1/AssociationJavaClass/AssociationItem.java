package com.example.projetle1.AssociationJavaClass;

import android.net.Uri;

import com.example.projetle1.BuildConfig;
import com.example.projetle1.R;

public class AssociationItem {

    private String aName;
    private String aDescription;
    private String aUrl;
    private Uri aLogo;

    public AssociationItem(){
        this.aName= "";
        this.aDescription= "";
        this.aUrl= "";
        this.aLogo= Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID+"/" + R.drawable.item_default);
    }

    public AssociationItem(String pName, String pUrl){
        this.aName= pName;
        this.aDescription= "";
        this.aUrl= pUrl;
        this.aLogo= Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID+"/" + R.drawable.item_default);

    }

    public AssociationItem(String pName, String pDescription, String pUrl, Uri pLogo){
        this.aName= pName;
        this.aDescription= pDescription;
        this.aUrl= pUrl;
        this.aLogo= pLogo;
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

    public Uri getLogo(){
        return this.aLogo;
    }
    public void setLogo(Uri pLogo){
        this.aLogo= pLogo;
    }


    //pour convertir une String en lien Url
    public Uri convertToUrl(String pLink){
        return Uri.parse(pLink);
    }
}

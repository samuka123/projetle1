package com.example.projetle1.AssociationJavaClass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetle1.R;

public class AssociationDetails extends AppCompatActivity {

    private ImageButton aCloseButtonD;

    private TextView aNameD;

    private TextView aDescriptionD;

    private TextView aUrlD;

    private ImageView aLogoD;

   // ActivityDetailedBinding binding;
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_association_details);

       aCloseButtonD= findViewById(R.id.association_details_close_button);

       aNameD= findViewById(R.id.association_details_name);
       aDescriptionD= findViewById(R.id.association_details_description);
       aUrlD= findViewById(R.id.association_details_url);

       aLogoD= findViewById(R.id.association_details_logo);


       Intent intent= this.getIntent();

       if(intent!=null){
           String name= intent.getStringExtra("name");
           String imageString= intent.getStringExtra("image");
           String description= intent.getStringExtra("description");
           String url= intent.getStringExtra("url");

           Uri image= Uri.parse(imageString);

           aNameD.setText(name);
           aDescriptionD.setText(description);
           aUrlD.setText(url);
           aLogoD.setImageURI(image);
       }

       aCloseButtonD.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent nextActivity = new Intent(getApplicationContext(), AssociationMain.class);
               startActivity(nextActivity);
               finish();
           }
       });
   }


}

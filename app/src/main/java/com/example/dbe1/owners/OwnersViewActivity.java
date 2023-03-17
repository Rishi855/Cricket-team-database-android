package com.example.dbe1.owners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.dbe1.R;
import com.example.dbe1.customview.CustomViewForPerson;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class OwnersViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_view);

        Intent getTeamID = getIntent();
        int teamID = getTeamID.getIntExtra("sendTeamID",0);

        Intent next = new Intent(new Intent(getApplicationContext(), GetOwnerValueByUser.class));
        next.putExtra("sendTeamID",teamID);
        FloatingActionButton fb = findViewById(R.id.float_add_new_owner);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(next);
            }
        });

        OwnerDataBase odb = new OwnerDataBase(this);

        ArrayList<OwnerAttributeClass> listOwner = odb.fetchAllOwner();

        ArrayList<String> ownerSequence = new ArrayList<>();
        ArrayList<Integer> particularOwner = new ArrayList<>();
        ArrayList<Integer> playerSequenceid = new ArrayList<>();
        ArrayList<Integer> imageSequence = new ArrayList<>();

        Log.d("ownerhere","ok 3 "+listOwner.size());
        Log.d("ownerhere","ok 4 "+teamID);
        for(int i=0;i<listOwner.size();i++)
        {
            if(teamID==listOwner.get(i).team_id)
            {
                particularOwner.add(listOwner.get(i).owner_id);
                ownerSequence.add(listOwner.get(i).owner_name);
                playerSequenceid.add(listOwner.get(i).owner_id);
                imageSequence.add(R.drawable.custom_owner_icon);
            }
            Log.d("checkerror","ok in"+listOwner.get(i).owner_name);
        }

        ListView listViewOwner = findViewById(R.id.listViewOwner);
//        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ownerSequence);
        CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,ownerSequence,playerSequenceid);
        listViewOwner.setAdapter(adp);

    }
}
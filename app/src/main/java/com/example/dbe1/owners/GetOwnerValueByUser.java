package com.example.dbe1.owners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dbe1.R;

public class GetOwnerValueByUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_owner_value_by_user);

        Intent getTeamID = getIntent();
        int teamID = getTeamID.getIntExtra("sendTeamID",0);

        EditText ownerNameByUser = findViewById(R.id.ownerNameByUser);
        Button submit = findViewById(R.id.btnSubmitOwnerDetails);

        Intent next = new Intent(getApplicationContext(),OwnersViewActivity.class);
        next.putExtra("sendTeamID",teamID);
        OwnerDataBase odb = new OwnerDataBase(this);
        Log.d("ownerhere","ok 1");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if(ownerNameByUser.getText().toString().trim().isEmpty())
                    {
                        ownerNameByUser.setError("Must required");
                        ownerNameByUser.requestFocus();
                        return;
                    }
                    Log.d("ownerhere","ok 2");
                    String OwnerNameByUser = ownerNameByUser.getText().toString().trim().toLowerCase();
                    odb.addOwner(teamID,OwnerNameByUser);
                    next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(next);
                }
                catch (Exception e)
                {

                }
            }
        });

    }
}
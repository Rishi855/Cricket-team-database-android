package com.example.dbe1.teams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbe1.R;

public class GetTeamValueByUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_team_value_by_user);

        TeamDataBase db = new TeamDataBase(this);
        EditText teamNameByUser = findViewById(R.id.teamNameByUser);
        EditText teamSponsorByUser = findViewById(R.id.teamSponorByUser);
        Button btnAddTeamByUser = findViewById(R.id.btnAddTeamByUser);
        btnAddTeamByUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if (teamNameByUser.getText().toString().trim().isEmpty()) {
                        teamNameByUser.setError("Must required");
                        teamNameByUser.requestFocus();
                        return;
                    }
                    if (teamSponsorByUser.getText().toString().trim().isEmpty()) {
                        teamSponsorByUser.setError("Must required");
                        teamSponsorByUser.requestFocus();
                        return;
                    }
                    String TeamNameByUser = teamNameByUser.getText().toString().trim();
                    String TeamSponsorByUser = teamSponsorByUser.getText().toString().trim();
                    db.addTeam(TeamNameByUser, TeamSponsorByUser);
                    Intent intent = new Intent(getApplicationContext(), TeamsViewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(GetTeamValueByUser.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
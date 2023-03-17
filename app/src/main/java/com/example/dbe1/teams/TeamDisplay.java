package com.example.dbe1.teams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbe1.R;
import com.example.dbe1.coaches.CoachesViewActivity;
import com.example.dbe1.owners.OwnersViewActivity;
import com.example.dbe1.players.PlayersViewActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class TeamDisplay extends AppCompatActivity {

    Button btnTeamPlayer,btnTeamCoach,btnTeamOwner;
    TextView displayTeamId,displayTeamName,displayTeamSponsor;
    FloatingActionsMenu fab;
    FloatingActionButton btnTeamUpdate,btnTeamSubmit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);

        Log.d("checkerror","yes 5");
        Intent getId= getIntent();
        displayTeamId = findViewById(R.id.displayTeamId);
        displayTeamName = findViewById(R.id.displayTeamName);
        displayTeamSponsor = findViewById(R.id.displayTeamSponsor);
        fab = findViewById(R.id.fabMenu);
        fab.collapse();
        findViewById(R.id.teamDisplay).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                fab.collapse();
                return true;
            }
        });

        TeamDataBase db = new TeamDataBase(this);
        ArrayList<TeamAttributeClass> listTeams = db.fetchAllTeams();
        int id = getId.getIntExtra("sendTeamID",0);
        for(int i=0;i<listTeams.size();i++)
        {
            Log.d("checkerror","yes 5 "+listTeams.get(i).teamId);
            if (listTeams.get(i).teamId == id)
            {
                displayTeamId.setText(listTeams.get(i).teamId+"");
                displayTeamName.setText(listTeams.get(i).teamName);
                displayTeamSponsor.setText(listTeams.get(i).teamSposor);
                break;
            }
        }
        btnTeamPlayer = findViewById(R.id.btnTeamPlayer);
        btnTeamCoach = findViewById(R.id.btnTeamCoach);
        btnTeamOwner = findViewById(R.id.btnTeamOwner);
        btnTeamSubmit = findViewById(R.id.fabSave);
        btnTeamUpdate = findViewById(R.id.fabUpdate);

        btnTeamPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.collapse();
                Intent displayPlayers = new Intent(getApplicationContext(), PlayersViewActivity.class);
                displayPlayers.putExtra("sendTeamID",id);
                startActivity(displayPlayers);

            }
        });
        btnTeamCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.collapse();
                Intent displayCoaches = new Intent(getApplicationContext(),CoachesViewActivity.class);
                displayCoaches.putExtra("sendTeamID",id);
                Log.d("idcheck 2",id+"");
                startActivity(displayCoaches);
            }
        });
        btnTeamOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.collapse();
                Intent displayOwners = new Intent(getApplicationContext(), OwnersViewActivity.class);
                displayOwners.putExtra("sendTeamID",id);
                startActivity(displayOwners);
            }
        });

        btnTeamUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.collapse();
                displayTeamName.setEnabled(true);
                displayTeamSponsor.setEnabled(true);
                Toast.makeText(TeamDisplay.this, "Update mode is on", Toast.LENGTH_SHORT).show();
            }
        });
        btnTeamSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.collapse();
                if (displayTeamName.getText().toString().trim().isEmpty()) {
                    displayTeamName.setError("Must required");
                    displayTeamName.requestFocus();
                    return;
                }
                if (displayTeamSponsor.getText().toString().trim().isEmpty()) {
                    displayTeamSponsor.setError("Must required");
                    displayTeamSponsor.requestFocus();
                    return;
                }
                displayTeamName.setEnabled(false);
                displayTeamSponsor.setEnabled(false);
                String team_name = displayTeamName.getText().toString().trim().toLowerCase();
                String team_sponsor = displayTeamSponsor.getText().toString().trim().toLowerCase();
                db.updateData(id,team_name,team_sponsor);
                Toast.makeText(TeamDisplay.this, "Data updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
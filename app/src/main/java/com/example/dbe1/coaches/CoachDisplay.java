package com.example.dbe1.coaches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbe1.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class CoachDisplay extends AppCompatActivity {

    FloatingActionsMenu fabMenu;
    FloatingActionsMenu fabUpdate,fabSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_display);

        FloatingActionsMenu fabMenu;
        FloatingActionButton fabUpdate,fabSave;
        Log.d("checkerror7","sendCoachID"+"1");

        TextView textCoachTeamID= findViewById(R.id.textTeamID);
        Log.d("checkerror7","sendCoachID"+"3");
        TextView textCoachID = findViewById(R.id.testPlayerID);
        TextView textCoachName = findViewById(R.id.textJerseyNo);
        TextView textCoachSpecification= findViewById(R.id.textPlayerName);
        TextView textCoachExperience= findViewById(R.id.textSpecification);

        Log.d("checkerror7","sendCoachID"+"2");
        CoachDatabase cdb = new CoachDatabase(this);
        ArrayList<CoachAttributeCLass> allCoaches = cdb.fetchAllCoach();
        Intent getCoachID = getIntent();
        int sendCoachID = getCoachID.getIntExtra("sendCoachID",0);
        Log.d("checkerror",allCoaches.size()+"");
//
        for (int i=0;i<allCoaches.size();i++)
        {
            if(allCoaches.get(i).coach_id==sendCoachID)
            {
//                Log.d("idcoach",allCoaches.get(i).coach_id+"");
                textCoachID.setText("Coach ID: "+allCoaches.get(i).coach_id+"");
                textCoachTeamID.setText("Team ID: "+allCoaches.get(i).team_id+"");
                textCoachName.setText(""+allCoaches.get(i).coach_name);
                textCoachExperience.setText(""+allCoaches.get(i).experience+"");
                textCoachSpecification.setText(""+allCoaches.get(i).specification);
            }
       }
        fabUpdate = findViewById(R.id.fabUpdate);
        fabSave = findViewById(R.id.fabSave);
        fabMenu = findViewById(R.id.fabMenu);
        fabMenu.collapse();

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCoachName.setEnabled(true);
                textCoachExperience.setEnabled(true);
                textCoachSpecification.setEnabled(true);

                Toast.makeText(CoachDisplay.this, "Update mode is on", Toast.LENGTH_SHORT).show();
            }
        });
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fabMenu.collapse();
                    int CoachExperienceByUser = Integer.parseInt(textCoachExperience.getText().toString().trim().toLowerCase());
                    if (textCoachName.getText().toString().trim().isEmpty()) {
                        textCoachName.setError("Must required");
                        textCoachName.requestFocus();
                        return;
                    }
                    if (textCoachSpecification.getText().toString().trim().isEmpty()) {
                        textCoachSpecification.setError("Must required");
                        textCoachSpecification.requestFocus();
                        return;
                    }
                    if (textCoachExperience.getText().toString().trim().isEmpty()) {
                        textCoachExperience.setError("Must required");
                        textCoachExperience.requestFocus();
                        return;
                    }
                    textCoachName.setEnabled(false);
                    textCoachExperience.setEnabled(false);
                    textCoachSpecification.setEnabled(false);
                    String team_coach_name = textCoachName.getText().toString().trim().toLowerCase();
                    String team_coach_experience = textCoachExperience.getText().toString().trim().toLowerCase();
                    String team_coach_specification = textCoachSpecification.getText().toString().trim().toLowerCase();
                    cdb.updateData(sendCoachID, team_coach_name, team_coach_experience, team_coach_specification);
                    Toast.makeText(CoachDisplay.this, "Data updated", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(CoachDisplay.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
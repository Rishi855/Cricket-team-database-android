package com.example.dbe1.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbe1.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class PlayerDisplay extends AppCompatActivity {

    TextView textPlayerID,textTeamID,textJerseyNo,textPlayerName,
            textSpecification,textWeight,textHeight,textMatches,
            textRuns,textWickets,textCatches;
    FloatingActionsMenu fabMenu;
    FloatingActionButton fabUpdate,fabSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        Intent getId = getIntent();

        textPlayerID = findViewById(R.id.testPlayerID);
        textTeamID = findViewById(R.id.textTeamID);
        textJerseyNo = findViewById(R.id.textJerseyNo);
        textPlayerName = findViewById(R.id.textPlayerName);
        textSpecification = findViewById(R.id.textSpecification);
        textWeight = findViewById(R.id.textWeight);
        textHeight = findViewById(R.id.textHeight);
        textMatches = findViewById(R.id.textMatches);
        textRuns = findViewById(R.id.textRuns);
        textWickets = findViewById(R.id.textWickets);
        textCatches = findViewById(R.id.textCatches);

        PlayerDataBase db = new PlayerDataBase(this);
        int id = getId.getIntExtra("sendPlayerID",0);
        ArrayList<PlayerAttributeClass> listPlayers = db.fetchAllPlayers();
        for(int i=0;i<listPlayers.size();i++)
        {
            if (listPlayers.get(i).player_id == id)
            {
                String word;
                word = listPlayers.get(i).team_id+"";
                textTeamID.setText("Team ID: "+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_id+"";
                textPlayerID.setText("Player ID: "+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).jersey_no+"";
                textJerseyNo.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_name+"";
                textPlayerName.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_specification+"";
                textSpecification.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_weight+"";
                textWeight.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_height+"";
                textHeight.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_matches+"";
                textMatches.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_run+"";
                textRuns.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_wicket+"";
                textWickets.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                word = listPlayers.get(i).player_catches+"";
                textCatches.setText(""+word.substring(0, 1).toUpperCase() +word.substring(1));

                break;
            }
        }
        fabUpdate = findViewById(R.id.fabUpdate);
        fabSave = findViewById(R.id.fabSave);
        fabMenu = findViewById(R.id.fabMenu);
        fabMenu.collapse();

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textJerseyNo.setEnabled(true);
                textPlayerName.setEnabled(true);
                textSpecification.setEnabled(true);
                textWeight.setEnabled(true);
                textHeight.setEnabled(true);
                textMatches.setEnabled(true);
                textRuns.setEnabled(true);
                textWickets.setEnabled(true);
                textCatches.setEnabled(true);
                Toast.makeText(PlayerDisplay.this, "Update mode is on", Toast.LENGTH_SHORT).show();
            }
        });
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fabMenu.collapse();

                    if (textJerseyNo.getText().toString().trim().isEmpty()) {
                        textJerseyNo.setError("Must required");
                        textJerseyNo.requestFocus();
                        return;
                    }
                    if (textPlayerName.getText().toString().trim().isEmpty()) {
                        textPlayerName.setError("Must required");
                        textPlayerName.requestFocus();
                        return;
                    }
                    if (textSpecification.getText().toString().trim().isEmpty()) {
                        textSpecification.setError("Must required");
                        textSpecification.requestFocus();
                        return;
                    }
                    if (textWickets.getText().toString().trim().isEmpty()) {
                        textWickets.setError("Must required");
                        textWickets.requestFocus();
                        return;
                    }
                    if (textHeight.getText().toString().trim().isEmpty()) {
                        textHeight.setError("Must required");
                        textHeight.requestFocus();
                        return;
                    }
                    if (textMatches.getText().toString().trim().isEmpty()) {
                        textMatches.setError("Must required");
                        textMatches.requestFocus();
                        return;
                    }

                    if (textRuns.getText().toString().trim().isEmpty()) {
                        textRuns.setError("Must required");
                        textRuns.requestFocus();
                        return;
                    }
                    if (textWickets.getText().toString().trim().isEmpty()) {
                        textWickets.setError("Must required");
                        textWickets.requestFocus();
                        return;
                    }
                    if (textCatches.getText().toString().trim().isEmpty()) {
                        textCatches.setError("Must required");
                        textCatches.requestFocus();
                        return;
                    }
                    int JerseyNo = Integer.parseInt(textJerseyNo.getText().toString().trim().toLowerCase());
                    Float Weight = Float.parseFloat(textWeight.getText().toString().trim().toLowerCase());
                    Float Height = Float.parseFloat(textHeight.getText().toString().trim().toLowerCase());
                    int Matches = Integer.parseInt(textMatches.getText().toString().trim().toLowerCase());
                    int Runs = Integer.parseInt(textRuns.getText().toString().trim().toLowerCase());
                    int Wickets = Integer.parseInt(textWickets.getText().toString().trim().toLowerCase());
                    int Catches = Integer.parseInt(textCatches.getText().toString().trim().toLowerCase());
                    textJerseyNo.setEnabled(false);
                    textPlayerName.setEnabled(false);
                    textSpecification.setEnabled(false);
                    textWeight.setEnabled(false);
                    textHeight.setEnabled(false);
                    textMatches.setEnabled(false);
                    textRuns.setEnabled(false);
                    textWickets.setEnabled(false);
                    textCatches.setEnabled(false);
                    String PlayerName = textPlayerName.getText().toString().trim().toLowerCase();
                    String Specification = textSpecification.getText().toString().trim().toLowerCase();
                    db.updateData(id,JerseyNo,PlayerName,Specification,Weight,Height,Matches,Runs,Wickets,Catches);
                    Toast.makeText(PlayerDisplay.this, "Data updated", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(PlayerDisplay.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
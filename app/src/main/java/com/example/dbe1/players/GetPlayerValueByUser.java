package com.example.dbe1.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbe1.R;

public class GetPlayerValueByUser extends AppCompatActivity {

    EditText jerseyNo,playerName,specification,weight,height,matches,runs,wickets,catches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_player_value_by_user);

        Log.d("playerc","here 1");
        jerseyNo = findViewById(R.id.playerJerseyNoByUser);
        playerName = findViewById(R.id.playerNameByUser);
        specification = findViewById(R.id.playerSpecificationByUser);
        weight = findViewById(R.id.playerWeightByUser);
        height = findViewById(R.id.playerHeightByUser);
        matches = findViewById(R.id.playerMatchesPlayedByUser);
        runs = findViewById(R.id.playerRunsByUser);
        wickets = findViewById(R.id.playerWicketsByUser);
        catches = findViewById(R.id.playerCatchesByUser);

        Log.d("playerc","here 2");
        Intent i = getIntent();
        Intent sendTeamId = new Intent(getApplicationContext(),PlayersViewActivity.class);
        sendTeamId.putExtra("sendTeamID",i.getIntExtra("sendTeamID",0));

        PlayerDataBase pdb = new PlayerDataBase(this);
        Button btnsubmit = findViewById(R.id.btnSubmitPlayerDetails);
        Log.d("playerc","here 3");
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("playerc","here 4");

                try {
                    if (jerseyNo.getText().toString().trim().isEmpty()) {
                        jerseyNo.setError("Must required");
                        jerseyNo.requestFocus();
                        return;
                    }
                    int JerseyNO = Integer.parseInt(jerseyNo.getText().toString().trim().toLowerCase());

                    String PlayerName = playerName.getText().toString().trim().toLowerCase();
                    if (PlayerName.isEmpty()) {
                        playerName.setError("Must required");
                        playerName.requestFocus();
                        return;
                    }

                    String Specfication = specification.getText().toString().trim().toLowerCase();
                    if (Specfication.isEmpty()) {
                        specification.setError("Must required");
                        specification.requestFocus();
                        return;
                    }

                    if (weight.getText().toString().trim().isEmpty()) {
                        weight.setError("Must required");
                        weight.requestFocus();
                        return;
                    }
                    float Weight = Float.parseFloat(weight.getText().toString().trim().toLowerCase());

                    if (height.getText().toString().trim().isEmpty()) {
                        height.setError("Must required");
                        height.requestFocus();
                        return;
                    }
                    float Height = Float.parseFloat(height.getText().toString().trim().toLowerCase());

                    if (matches.getText().toString().trim().isEmpty()) {
                        matches.setError("Must required");
                        matches.requestFocus();
                        return;
                    }
                    int Matches = Integer.parseInt(matches.getText().toString().trim().toLowerCase());

                    if (runs.getText().toString().trim().isEmpty()) {
                        runs.setError("Must required");
                        runs.requestFocus();
                        return;
                    }
                    int Runs = Integer.parseInt(runs.getText().toString().trim().toLowerCase());

                    if (wickets.getText().toString().trim().isEmpty()) {
                        wickets.setError("Must required");
                        wickets.requestFocus();
                        return;
                    }
                    int Wickets = Integer.parseInt(wickets.getText().toString().trim().toLowerCase());

                    if (catches.getText().toString().trim().isEmpty()) {
                        catches.setError("Must required");
                        catches.requestFocus();
                        return;
                    }
//                    Intent intent = new Intent(this, Activity.class);
                    sendTeamId.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    int Catches = Integer.parseInt(catches.getText().toString().trim().toLowerCase());
                    pdb.addPlayer(i.getIntExtra("sendTeamID",0),JerseyNO,PlayerName,Specfication,Weight,Height,Matches,Runs,Wickets,Catches);
                    startActivity(sendTeamId);
                }
                catch (Exception e)
                {
                    Toast.makeText(GetPlayerValueByUser.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
package com.example.dbe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dbe1.coaches.CoachAttributeCLass;
import com.example.dbe1.coaches.CoachDatabase;
import com.example.dbe1.coaches.CoachDisplay;
import com.example.dbe1.customview.CustomViewForPerson;
import com.example.dbe1.players.PlayerAttributeClass;
import com.example.dbe1.players.PlayerDataBase;
import com.example.dbe1.players.PlayerDisplay;

import java.util.ArrayList;

public class PlayerCoachView extends AppCompatActivity {

    String search = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_coach_view);
        Intent get = getIntent();
        String change = get.getStringExtra("change");
        ListView listViewPlayer = findViewById(R.id.listViewPlayerCoach);
        TextView esearch = findViewById(R.id.esearch);
        Button btngo = findViewById(R.id.btngo);

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = esearch.getText().toString().trim().toLowerCase();
                if(change.equals("player"))
                {

                    PlayerDataBase db = new PlayerDataBase(getApplicationContext());
                    Log.d("checkHere","ok 4");
                    ArrayList<PlayerAttributeClass> listTeams = db.fetchAllPlayers();
                    Log.d("checkHere","ok 6");
                    ArrayList<String> playerSequence = new ArrayList<>();
                    ArrayList<Integer> playerSequenceid = new ArrayList<>();
                    ArrayList<Integer> imageSequence = new ArrayList<>();
                    Log.d("checkHere","ok 5");
                    for(int i=0;i<listTeams.size();i++)
                    {
                        if(search.equals(listTeams.get(i).player_name)) {
                            playerSequence.add("" + listTeams.get(i).player_name);
                            playerSequenceid.add(listTeams.get(i).player_id);
                            imageSequence.add(R.drawable.custom_player_icon);
                        }
                        else if(search.equals(""))
                        {
                            playerSequence.add("" + listTeams.get(i).player_name);
                            playerSequenceid.add(listTeams.get(i).player_id);
                            imageSequence.add(R.drawable.custom_player_icon);
                        }

                    }
                    Log.d("checkHere","ok 3");

                    CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,playerSequence,playerSequenceid);
                    listViewPlayer.setAdapter(adp);

                    listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.d("checkerror","yes 1");
                            Intent intent = new Intent(getApplicationContext(), PlayerDisplay.class);
                            intent.putExtra("sendPlayerID",playerSequenceid.get(i));
                            startActivity(intent);
                        }
                    });
                }
                else if(change.equals("coach"))
                {
                    Log.d("main1","here1");
                    CoachDatabase db = new CoachDatabase(getApplicationContext());
                    ArrayList<CoachAttributeCLass> listCoach = db.fetchAllCoach();
                    ArrayList<String> coachSequence = new ArrayList<>();
                    ArrayList<Integer> playerSequenceid = new ArrayList<>();
                    ArrayList<Integer> imageSequence = new ArrayList<>();
                    for(int i=0;i<listCoach.size();i++)
                    {
                        if(search.equals(listCoach.get(i).coach_name)) {
                            coachSequence.add("" + listCoach.get(i).coach_name);
                            playerSequenceid.add(listCoach.get(i).coach_id);
                            imageSequence.add(R.drawable.custom_coach_icon);
                        }
                        else if(search.equals(""))
                        {
                            coachSequence.add("" + listCoach.get(i).coach_name);
                            playerSequenceid.add(listCoach.get(i).coach_id);
                            imageSequence.add(R.drawable.custom_coach_icon);
                        }

                    }
                    Log.d("main1","here1");
                    CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,coachSequence,playerSequenceid);
                    listViewPlayer.setAdapter(adp);

                    listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.d("checkerror","yes 1");
                            Intent intent = new Intent(getApplicationContext(), CoachDisplay.class);
                            intent.putExtra("sendCoachID",playerSequenceid.get(i));
                            startActivity(intent);
                        }
                    });
                }
            }
        });

//        getdata(change,)
        if(change.equals("player") && search.equals(""))
        {

            PlayerDataBase db = new PlayerDataBase(this);
            Log.d("checkHere","ok 4");
            ArrayList<PlayerAttributeClass> listTeams = db.fetchAllPlayers();
            Log.d("checkHere","ok 6");
            ArrayList<String> playerSequence = new ArrayList<>();
            ArrayList<Integer> playerSequenceid = new ArrayList<>();
            ArrayList<Integer> imageSequence = new ArrayList<>();
            Log.d("checkHere","ok 5");
            for(int i=0;i<listTeams.size();i++)
            {
                playerSequence.add(""+listTeams.get(i).player_name);
                playerSequenceid.add(listTeams.get(i).player_id);
                imageSequence.add(R.drawable.custom_player_icon);

            }
            Log.d("checkHere","ok 3");

            CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,playerSequence,playerSequenceid);
            listViewPlayer.setAdapter(adp);

            listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("checkerror","yes 1");
                    Intent intent = new Intent(getApplicationContext(), PlayerDisplay.class);
                    intent.putExtra("sendPlayerID",playerSequenceid.get(i));
                    startActivity(intent);
                }
            });
        }
        else if(change.equals("coach") && search.equals(""))
        {
            Log.d("main1","here1");
            CoachDatabase db = new CoachDatabase(this);
            ArrayList<CoachAttributeCLass> listCoach = db.fetchAllCoach();
            ArrayList<String> coachSequence = new ArrayList<>();
            ArrayList<Integer> playerSequenceid = new ArrayList<>();
            ArrayList<Integer> imageSequence = new ArrayList<>();
            for(int i=0;i<listCoach.size();i++)
            {
                    coachSequence.add(""+listCoach.get(i).coach_name);
                    playerSequenceid.add(listCoach.get(i).coach_id);
                    imageSequence.add(R.drawable.custom_coach_icon);

            }
            Log.d("main1","here1");
            CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,coachSequence,playerSequenceid);
            listViewPlayer.setAdapter(adp);

            listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("checkerror","yes 1");
                    Intent intent = new Intent(getApplicationContext(), CoachDisplay.class);
                    intent.putExtra("sendCoachID",playerSequenceid.get(i));
                    startActivity(intent);
                }
            });
        }




    }
}
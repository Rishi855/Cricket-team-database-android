package com.example.dbe1.teams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dbe1.R;
import com.example.dbe1.customview.CustomViewForPerson;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class TeamsViewActivity extends AppCompatActivity {

    ListView listViewTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_view);
        Log.d("errorcheck","ok 1");

        FloatingActionButton fb = findViewById(R.id.float_add_new_team);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GetTeamValueByUser.class));
            }
        });
        FloatingActionsMenu fab = findViewById(R.id.fabMenuTeam);
//        fab.collapse();
//        findViewById(R.id.listViewTeam).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d("errorcheck","ok 4");
//                fab.collapse();
//                return true;
//            }
//        });
        Log.d("errorcheck","ok 5");

        Log.d("errorcheck","ok 2");
        TeamDataBase db = new TeamDataBase(this);
        ArrayList<TeamAttributeClass> listTeams = db.fetchAllTeams();
        ArrayList<Integer> playerSequenceid = new ArrayList<>();
        ArrayList<Integer> imageSequence = new ArrayList<>();

        ArrayList<String> teamSequence = new ArrayList<>();
        for(int i=0;i<listTeams.size();i++)
        {
            teamSequence.add(listTeams.get(i).teamName);
            playerSequenceid.add(listTeams.get(i).teamId);
            imageSequence.add(R.drawable.custom_team_icon);
            Log.d("checkerror","ok in"+listTeams.get(i).teamName);
        }
        listViewTeam = findViewById(R.id.listViewTeam);
//        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamSequence);
        CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,teamSequence,playerSequenceid);
        listViewTeam.setAdapter(adp);
        listViewTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("checkerror","yes 1");
                fab.collapse();
                Intent intent = new Intent(getApplicationContext(), TeamDisplay.class);
                intent.putExtra("sendTeamID",listTeams.get(i).teamId);
                Log.d("checkerror","yes 2 "+listTeams.get(i).teamId);
                startActivity(intent);
            }
        });


    }
}
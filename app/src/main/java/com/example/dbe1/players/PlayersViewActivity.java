package com.example.dbe1.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dbe1.R;
import com.example.dbe1.customview.CustomViewForPerson;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class PlayersViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_view);
//        Log.d("checkerror","yes 2");
        FloatingActionButton fb = findViewById(R.id.float_add_new_player);
        Intent getValue = getIntent();
        Intent getPlayer = new Intent(getApplicationContext(),GetPlayerValueByUser.class);
        int id = getValue.getIntExtra("sendTeamID",0);
        getPlayer.putExtra("sendTeamID",id);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("playerc","here 4");
                startActivity(getPlayer);
            }
        });

        FloatingActionsMenu fab = findViewById(R.id.fabMenuPlayer);
        fab.collapse();
        findViewById(R.id.playerDisplay).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                fab.collapse();
                return true;
            }
        });
        ListView listViewPlayer = findViewById(R.id.listViewPlayer);
        PlayerDataBase db = new PlayerDataBase(this);
        Log.d("checkHere","ok 4");
        ArrayList<PlayerAttributeClass> listTeams = db.fetchAllPlayers();
        Log.d("checkHere","ok 6");
        ArrayList<String> playerSequence = new ArrayList<>();
        ArrayList<Integer> playerSequenceid = new ArrayList<>();
        ArrayList<Integer> imageSequence = new ArrayList<>();
        Log.d("checkHere","ok 5");
        ArrayList<Integer> particularPlayerID = new ArrayList<>();
        for(int i=0;i<listTeams.size();i++)
        {
            if(listTeams.get(i).team_id==id)
            {
                particularPlayerID.add(listTeams.get(i).player_id);
                playerSequence.add(""+listTeams.get(i).player_name);
                playerSequenceid.add(listTeams.get(i).player_id);
                imageSequence.add(R.drawable.custom_player_icon);
            }
        }
        Log.d("checkHere","ok 3");
//        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerSequence);

        CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,playerSequence,playerSequenceid);
        listViewPlayer.setAdapter(adp);

        listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("checkerror","yes 1");
                Intent intent = new Intent(getApplicationContext(),PlayerDisplay.class);
                intent.putExtra("sendPlayerID",particularPlayerID.get(i));
                startActivity(intent);
            }
        });
    }
}

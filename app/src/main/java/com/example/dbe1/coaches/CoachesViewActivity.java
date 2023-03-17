package com.example.dbe1.coaches;

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

import java.util.ArrayList;

public class CoachesViewActivity extends AppCompatActivity {

    ListView listViewCoach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches_view);

        Intent getTeamId = getIntent();
        Intent next = new Intent(new Intent(getApplicationContext(), GetCoachValueByUser.class));
        int id = getTeamId.getIntExtra("sendTeamID",0);
//        int id = 1;
        next.putExtra("sendTeamID",id);

        FloatingActionButton fb = findViewById(R.id.float_add_new_coach);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(next);
            }
        });

        CoachDatabase db = new CoachDatabase(this);
        ArrayList<CoachAttributeCLass> listCoach = db.fetchAllCoach();
        ArrayList<String> coachSequence = new ArrayList<>();
        ArrayList<Integer> playerSequenceid = new ArrayList<>();
        ArrayList<Integer> imageSequence = new ArrayList<>();
        Log.d("idcheck 3",id+"");
        ArrayList<Integer> particularTeamCoach = new ArrayList<>();
        for(int i=0;i<listCoach.size();i++)
        {
            if(id==listCoach.get(i).team_id)
            {
                particularTeamCoach.add(listCoach.get(i).coach_id);
                coachSequence.add(""+listCoach.get(i).coach_name);
                playerSequenceid.add(listCoach.get(i).coach_id);
                imageSequence.add(R.drawable.custom_coach_icon);
            }
            Log.d("checkerror","ok in"+listCoach.get(i).coach_name);
        }
        listViewCoach = findViewById(R.id.listViewCoach);
//        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coachSequence);
        CustomViewForPerson adp = new CustomViewForPerson(getApplicationContext(),imageSequence,coachSequence,playerSequenceid);
        listViewCoach.setAdapter(adp);
//
        listViewCoach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("checkerror","yes 1");
                Intent intent = new Intent(getApplicationContext(), CoachDisplay.class);
                intent.putExtra("sendCoachID",particularTeamCoach.get(i));
                Log.d("checkerror","yes 2 "+particularTeamCoach.get(i));
                startActivity(intent);
            }
        });
    }
}
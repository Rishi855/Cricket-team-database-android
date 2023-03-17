package com.example.dbe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dbe1.teams.TeamsViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent change = new Intent(getApplicationContext(), PlayerCoachView.class);
        Button team = findViewById(R.id.mainteam);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TeamsViewActivity.class));
            }
        });
        Button player = findViewById(R.id.mainplayer);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change.putExtra("change","player");
                startActivity(change);
            }
        });
        Button coach = findViewById(R.id.maincoach);
        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change.putExtra("change","coach");
                startActivity(change);
            }
        });

        Button info = findViewById(R.id.maininfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Information.class));

            }
        });

    }
}
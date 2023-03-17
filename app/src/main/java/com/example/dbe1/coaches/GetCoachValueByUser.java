package com.example.dbe1.coaches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dbe1.R;

public class GetCoachValueByUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coach_value_by_user);

        EditText coachNameByUser = findViewById(R.id.coachNameByUser);
        EditText  coachExperienceByUser = findViewById(R.id.coachExperienceByUser);
        EditText coachSpecificationByUser = findViewById(R.id.coachSpecificationByUser);
        Intent getTeamID = getIntent();
        CoachDatabase cdb = new CoachDatabase(this);
        Intent next = new Intent(getApplicationContext(),CoachesViewActivity.class);
        Button btnSubmitCoachDetails = findViewById(R.id.btnSubmitOwnerDetails);
        btnSubmitCoachDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    String CoachNameByUser = coachNameByUser.getText().toString().trim().toLowerCase();
                    if(CoachNameByUser.isEmpty())
                    {
                        coachNameByUser.setError("Must required");
                        coachNameByUser.requestFocus();
                        return;
                    }
                    if(coachExperienceByUser.getText().toString().trim().isEmpty())
                    {
                        coachExperienceByUser.setError("Must required");
                        coachExperienceByUser.requestFocus();
                        return;
                    }
                    int CoachExperienceByUser = Integer.parseInt(coachExperienceByUser.getText().toString().trim().toLowerCase());
                    String CoachSpecificationByUser = coachSpecificationByUser.getText().toString().trim().toLowerCase();
                    if(CoachSpecificationByUser.isEmpty())
                    {
                        coachSpecificationByUser.setError("Must required");
                        coachSpecificationByUser.requestFocus();
                        return;
                    }


                    int team_id = getTeamID.getIntExtra("sendTeamID",0);
                    next.putExtra("sendTeamID",team_id);
                    Log.d("idcheck 4",team_id+"");
//                    Toast.makeText(GetCoachValueByUser.this, "tost"+team_id, Toast.LENGTH_SHORT).show();
                    cdb.addCoach(team_id,CoachNameByUser,CoachExperienceByUser,CoachSpecificationByUser);
                    next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(next);
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}
package com.example.teleinfo.administration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.teleinfo.R;
import com.example.teleinfo.settings.ActivityFragmentView;

public class __ActivityMainAdministration extends AppCompatActivity {

    LinearLayout inviteTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_activity_main);

        inviteTeacher = (LinearLayout)findViewById(R.id.inviteTeacher);

        inviteTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), _ActivityFragmentViewAdministration.class);
                intent.putExtra("stringTag", "administrationLinearLayoutTeachers");
                startActivity(intent);

            }
        });



    }
}
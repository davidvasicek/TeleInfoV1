package com.example.teleinfo.krmitko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teleinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class KrmitkoMainActivity extends AppCompatActivity {

    TextView txt1;
    TextView txt2;
    Button btn1;

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;

    int lastSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krmitko_activity_main);

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("test");




        txt1 = (TextView)findViewById(R.id.textView);
        txt2 = (TextView)findViewById(R.id.textView2);
        btn1 = (Button)findViewById(R.id.button);

        btn1.setEnabled(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child("int").setValue(lastSyncData-1);

                btn1.setEnabled(false);

                btn1.setEnabled(false);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                btn1.setEnabled(true);
                            }
                        });
                    }
                }, 2000);


            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lastSyncData = dataSnapshot.child("int").getValue(int.class);;
                txt2.setText(""+lastSyncData);

                if(lastSyncData>0){

                    btn1.setEnabled(true);

                }
                else{
                    btn1.setEnabled(false);
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {



                // TODO
                //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

            }
        });



    }
}
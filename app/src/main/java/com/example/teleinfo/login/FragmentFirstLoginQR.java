package com.example.teleinfo.login;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.databinding.GuideFragmentRunGuideBinding;
import com.example.teleinfo.dialogs.ColorSelectBottomSheetDialog;
import com.example.teleinfo.guide._MainActivityGuide;
import com.example.teleinfo.rozvrh.DinningRoomMenuObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirstLoginQR extends Fragment implements BottomSheetDialogQRCodeReading.QRCodeParseListener, BottomSheetDialogCredentialsLogin.CredentialsListener{

    // https://androiddvlpr.com/zxing-android-example/


    Button activityLoginWithKeyButtcconLogin;
    TextView activityLoginWithKxeyccTextViewKeyRequest;
    TextView activityLoginWithKeyTextViewLostKey;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    public FragmentFirstLoginQR() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.login_fragment_first_login_qr, container, false);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/DavidVasicek/AccessKey");


        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/DavidVasicek/");



        mDatabaseReference.child("titlesBeforeName").setValue("Ing.");
        mDatabaseReference.child("Name").setValue("David");
        mDatabaseReference.child("Surname").setValue("Vašíček");
        mDatabaseReference.child("titlesAfterTheName").setValue("");
        mDatabaseReference.child("shortcut").setValue("Vaš");
        mDatabaseReference.child("email").setValue("vasicek@teleinformatika.eu");
        mDatabaseReference.child("phoneNumber").setValue("731 531 389");
        mDatabaseReference.child("officeNumber").setValue("A 1006");

        mDatabaseReference.child("registeredDevices/64564564564564/DeviceID").setValue("64564564564564");
        mDatabaseReference.child("registeredDevices/64564564564564/Brand").setValue("Xiaomi");
        mDatabaseReference.child("registeredDevices/64564564564564/Token").setValue("hkjfdhkgdfáýgídfžáýbdfýháýdsfžýáýh");


        activityLoginWithKeyButtcconLogin = (Button)rootView.findViewById(R.id.activityLoginWithKeyButtcconLogin);
        activityLoginWithKxeyccTextViewKeyRequest = (TextView)rootView.findViewById(R.id.activityLoginWithKxeyccTextViewKeyRequest);
        activityLoginWithKeyTextViewLostKey = (TextView)rootView.findViewById(R.id.activityLoginWithKeyTextViewLostKey);




        activityLoginWithKeyButtcconLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogQRCodeReading bottomSheetDialogQRCodeReading = new BottomSheetDialogQRCodeReading();
                bottomSheetDialogQRCodeReading.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        activityLoginWithKeyTextViewLostKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogCredentialsLogin bottomSheetDialogCredentialsLogin = new BottomSheetDialogCredentialsLogin();
                bottomSheetDialogCredentialsLogin.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });




        return rootView;
    }


    @Override
    public void applyQRCodeParseListener(String message) {
        activityLoginWithKxeyccTextViewKeyRequest.setText(message);
        login();
    }

    @Override
    public void applyCredentialsListener(String username, String password) {

        activityLoginWithKxeyccTextViewKeyRequest.setText(username);
        login();
    }





    boolean login(){



        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue(String.class).compareTo("aaabbbccc") == 0){

                    Log.e(TAG, "___________________" + " anooooooooo");
                    // otevři průvodce nastavením android aplikací (zabezpečení, vzhled, notifikace - notifikace stavu baterií, notifikace offlinosti senzorů, notifikace událostí
                    // notifikace narozenin a svátků.......)

                    Intent myIntent = new Intent(getContext(), _MainActivityGuide.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(myIntent);

                }else{

                    Log.e(TAG, "___________________" + "neeeeeeee");
                    Toast.makeText(getContext(),"klíč je nenení správný",Toast.LENGTH_LONG).show();

                }




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return true;
    }
}










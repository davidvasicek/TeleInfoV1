package com.example.teleinfo.login;

import static android.content.ContentValues.TAG;
import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;
import static com.example.teleinfo.parameters.MainParameters.AUTH_PIN_OR_PASSWORD;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_PIN;
import static com.example.teleinfo.parameters.MainParameters.NUMBER_OF_DEFAULT_PINS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.TIME_OF_LAST_BLOCKED_PIN;
import static com.example.teleinfo.parameters.MainParameters.TIME_OF_LAST_BLOCKED_READER;
import static com.example.teleinfo.parameters.MainParameters.USER_EMAIL_LOGGED;
import static com.example.teleinfo.parameters.MainParameters.USER_PASSWORD_LOGGED;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.MainActivity;
import com.example.teleinfo.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FragmentLoginWithPin extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    TextView textViewBtn1;
    TextView textViewBtn2;
    TextView textViewBtn3;
    TextView textViewBtn4;
    TextView textViewBtn5;
    TextView textViewBtn6;
    TextView textViewBtn7;
    TextView textViewBtn8;
    TextView textViewBtn9;
    TextView textViewBtn0;
    TextView textViewErrorMessage;
    TextView textViewErrorNumber;
    TextView textViewErrorUnits;
    TextView textViewLoginStatus;
    TextView textViewNameOfUser;
    TextView textViewLoginWithQRcode;
    TextView textViewLoginWithCredentials;
    ImageView imageViewSuccessStatus;
    ImageView imageViewBtnDelete;
    AVLoadingIndicatorView aVLoadingIndicatorViewLogging;
    LinearLayout linearLayoutPinEnter;
    LinearLayout linearLayoutPins;








    StringBuilder mCodeNew;



    int mPressButtonCount = 0;
    int numberOfBadAttempts = 0;


    private LinearLayout linearLayoutsPin[];


    private static final long START_TIME_IN_MILLIS = 30000;

    private CountDownTimer mCountDownTimer;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    public FragmentLoginWithPin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.login_fragment_pin, container, false);

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/");

        textViewBtn1 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn1);
        textViewBtn2 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn2);
        textViewBtn3 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn3);
        textViewBtn4 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn4);
        textViewBtn5 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn5);
        textViewBtn6 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn6);
        textViewBtn7 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn7);
        textViewBtn8 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn8);
        textViewBtn9 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn9);
        textViewBtn0 = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewBtn0);
        textViewErrorMessage = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewErrorMessage);
        textViewErrorNumber = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewErrorNumber);
        textViewErrorUnits = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewErrorUnits);
        textViewLoginStatus = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewLoginStatus);
        textViewNameOfUser = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewNameOfUser);
        textViewLoginWithQRcode = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewLoginWithQRcode);
        textViewLoginWithCredentials = (TextView)rootView.findViewById(R.id.loginFragmentPinTextViewLoginWithCredentials);
        imageViewSuccessStatus = (ImageView)rootView.findViewById(R.id.loginFragmentPinImageViewSuccessStatus);
        imageViewBtnDelete = (ImageView)rootView.findViewById(R.id.loginFragmentPinImageViewBtnDelete);
        aVLoadingIndicatorViewLogging = (AVLoadingIndicatorView)rootView.findViewById(R.id.loginFragmentPinAVLoadingIndicatorViewLogging);
        linearLayoutPinEnter = (LinearLayout)rootView.findViewById(R.id.loginFragmentPinLinearLayoutPinEnter);
        linearLayoutPins = (LinearLayout)rootView.findViewById(R.id.loginFragmentPinLinearLayoutPins);

        linearLayoutPinEnter.setVisibility(View.VISIBLE);
        imageViewSuccessStatus.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
        textViewErrorNumber.setVisibility(View.GONE);
        textViewErrorUnits.setVisibility(View.GONE);
        textViewLoginStatus.setVisibility(View.GONE);
        textViewNameOfUser.setVisibility(View.GONE);
        aVLoadingIndicatorViewLogging.setVisibility(View.GONE);

        linearLayoutsPin = new LinearLayout[NUMBER_OF_DEFAULT_PINS];

        LinearLayout.LayoutParams lpSections = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT, // Height of TextView
                1f);
        lpSections.setMargins(30, 0, 30, 0);

        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {

            linearLayoutsPin[i] = new LinearLayout(getContext());

            linearLayoutsPin[i].setLayoutParams(lpSections);
            linearLayoutsPin[i].setOrientation(LinearLayout.HORIZONTAL);

            linearLayoutPins.addView(linearLayoutsPin[i]);
        }

        setPinIndicator(0);

        Long lastTime = mSharedPreferences.getLong(TIME_OF_LAST_BLOCKED_PIN, 0);

        if(System.currentTimeMillis()-lastTime >30000){

            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            //initFingerprint();

        }else{

            mTimeLeftInMillis = 30000-((System.currentTimeMillis()-lastTime));


            startTimer();
        }


        //nastavení listeneru
        textViewBtn1.setOnClickListener(mOnKeyClickListener);
        textViewBtn2.setOnClickListener(mOnKeyClickListener);
        textViewBtn3.setOnClickListener(mOnKeyClickListener);
        textViewBtn4.setOnClickListener(mOnKeyClickListener);
        textViewBtn5.setOnClickListener(mOnKeyClickListener);
        textViewBtn6.setOnClickListener(mOnKeyClickListener);
        textViewBtn7.setOnClickListener(mOnKeyClickListener);
        textViewBtn8.setOnClickListener(mOnKeyClickListener);
        textViewBtn9.setOnClickListener(mOnKeyClickListener);
        textViewBtn0.setOnClickListener(mOnKeyClickListener);
        imageViewBtnDelete.setOnClickListener(mOnDeleteButtonClickListener);

        mCodeNew = new StringBuilder(100);


        return rootView;
    }

    public void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                mTimeLeftInMillis = millisUntilFinished;

                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorNumber.setVisibility(View.VISIBLE);
                textViewErrorUnits.setVisibility(View.VISIBLE);

                linearLayoutPinEnter.setVisibility(View.GONE);
                //textViewFingerprintTextStatus.setText("kokot");
                updateCountDownText();

                //textViewFingerprintTextStatus.setVisibility(View.GONE);
                //imageViewFingerprintStatus.setVisibility(View.GONE);




            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFinish() {

                Log.e("mylog", "??????????????: oooooooooooo");

                numberOfBadAttempts=0;

                textViewErrorMessage.setVisibility(View.GONE);
                textViewErrorNumber.setVisibility(View.GONE);
                textViewErrorUnits.setVisibility(View.GONE);

                textViewErrorMessage.setText("");
                textViewErrorNumber.setText("");
                textViewErrorUnits.setText("");


                linearLayoutPinEnter.setVisibility(View.VISIBLE);


                Log.e("klmmmmmmmmmmmmmm", "konec textViewFingerprintTextStatusu");
                //mTimerRunning = false;
                //mButtonStartPause.setText("Start");
                //mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        //mTimerRunning = true;
        //mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        Log.e("mylog", "??????????????: ooooooooooo " + timeLeftFormatted);

        textViewErrorMessage.setText("Zadávání pinu je zablokováno\nK obnovení dojde za");
        textViewErrorNumber.setText(timeLeftFormatted);
        textViewErrorUnits.setText("sekund");


    }




    private View.OnClickListener mOnKeyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {

                if(mPressButtonCount >= NUMBER_OF_DEFAULT_PINS){

                    return;

                }else{

                    mPressButtonCount++;
                }

                String string = ((TextView) v).getText().toString(); // Získej číslici tlačítka z jeho popisu

                // Ověř, že číslice je pouze jednomístná
                if (string.length() != 1) {
                    return;
                }


                //připoj číslici
                mCodeNew.append(string);



                // Zkontroluj, jestli počet stisknutých číslic v StrinBuilderu mCode je větší než 1
                // zobraz na obrazovce tlačítko pro mazání znaků
                if(mPressButtonCount >= 1){

                    imageViewBtnDelete.setVisibility(View.VISIBLE);
                }

                setPinIndicator(mPressButtonCount);



                if(mPressButtonCount >= NUMBER_OF_DEFAULT_PINS){

                    Log.e("mylog", "??????????????: " + mSharedPreferences.getString(AUTH_PIN_OR_PASSWORD, ""));


                    if(mCodeNew.toString().compareTo(mSharedPreferences.getString(AUTH_PIN_OR_PASSWORD, "")) == 0){

                        imageViewSuccessStatus.setImageResource(R.drawable.success);
                        imageViewBtnDelete.setVisibility(View.GONE);
                        imageViewSuccessStatus.setVisibility(View.VISIBLE);

                        login();

                    }else{





                        imageViewSuccessStatus.setImageResource(R.drawable.failure);

                        imageViewSuccessStatus.setVisibility(View.VISIBLE);

                        imageViewSuccessStatus.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // imageViewFingertprintStatus.setImageResource(R.drawable.ic_fingerprint);
                                // imageViewFingertprintStatus.getDrawable().setColorFilter(getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP );
                                //textViewReply.setText("");
                                imageViewSuccessStatus.setVisibility(View.GONE);


                            }
                        }, 600);

                        mPressButtonCount = 0;
                        setPinIndicator(mPressButtonCount);
                        mCodeNew = new StringBuilder(100);
                        imageViewBtnDelete.setVisibility(View.GONE);

                        numberOfBadAttempts++;




                        if(numberOfBadAttempts == 4){


                            mEditor.putLong(TIME_OF_LAST_BLOCKED_PIN, System.currentTimeMillis());
                            mEditor.commit();

                            mTimeLeftInMillis = START_TIME_IN_MILLIS;
                            startTimer();
                            startTimer();

                        }else{

                            textViewErrorMessage.setVisibility(View.VISIBLE);
                            textViewErrorNumber.setVisibility(View.VISIBLE);
                            textViewErrorUnits.setVisibility(View.GONE);

                            textViewErrorMessage.setText("Chybný Pin\n\nPočet zbývajících pokusů");
                            textViewErrorNumber.setText(4-numberOfBadAttempts + "");
                            textViewErrorUnits.setText("");

                            textViewErrorMessage.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textViewErrorMessage.setVisibility(View.GONE);
                                    textViewErrorNumber.setVisibility(View.GONE);                                }
                            }, 1500);

                        }

                    }


                }else{

                    imageViewSuccessStatus.setVisibility(View.GONE);

                }



            }
        }
    };


    private View.OnClickListener mOnDeleteButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {





            mPressButtonCount--;
            setPinIndicator(mPressButtonCount);

            if(mPressButtonCount<0){
                return;
            }

            if (mPressButtonCount != -1) {

                mCodeNew.delete(mCodeNew.length()-1, mCodeNew.length());
            }

            if(mPressButtonCount < 1){

                imageViewBtnDelete.setVisibility(View.INVISIBLE);

            }



            if(mPressButtonCount >= NUMBER_OF_DEFAULT_PINS){

                imageViewSuccessStatus.setVisibility(View.GONE);

            }else{

                imageViewSuccessStatus.setVisibility(View.GONE);

            }

           /* if(mCode.length() >= NUMBER_OF_DEFAULT_PINS){
                Log.e("?????????", "tadz" );




            }else{

                Log.e("?????????", "a ted" );

                textViewOK.setVisibility(View.GONE);
                imageViewSuccessStatus.setVisibility(View.GONE);

            }*/


        }
    };

    public void setPinIndicator(int numberSelectedNumbers){



            for(int i = 0; i<NUMBER_OF_DEFAULT_PINS; i++){

                if(i<numberSelectedNumbers){

                   // linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_primary));
                    linearLayoutsPin[i].setBackgroundColor(Color.parseColor("#546E7A"));

                }
                else{

                   // linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                    linearLayoutsPin[i].setBackgroundColor(Color.parseColor("#EEEEEE"));



                }
            }


    }



    boolean login() {

        String username = mSharedPreferences.getString(USER_EMAIL_LOGGED, "");
        String password = mSharedPreferences.getString(USER_PASSWORD_LOGGED, "");

        textViewNameOfUser.setVisibility(View.VISIBLE);
        textViewLoginStatus.setVisibility(View.VISIBLE);
        aVLoadingIndicatorViewLogging.setVisibility(View.VISIBLE);

        textViewLoginStatus.setText("Ověřuji uživatele");
        textViewLoginStatus.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

        textViewNameOfUser.setText(username);
        textViewLoginWithCredentials.setVisibility(View.GONE);


        String email = username.replace("@","_").replace(".","_");
        Log.e(TAG, "___________________" + email);


        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/" + email + "/adminstračníInfo/AccessKey");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue(String.class).compareTo(password) == 0){

                    Log.e(TAG, "___________________" + " anooooooooo");
                    // otevři průvodce nastavením android aplikací (zabezpečení, vzhled, notifikace - notifikace stavu baterií, notifikace offlinosti senzorů, notifikace událostí
                    // notifikace narozenin a svátků.......)

                    mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/" + email + "/adminstračníInfo");

                    mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            Log.e(TAG, "___________________" + dataSnapshot.child("PairTimeTable").getValue(String.class) );
                            Log.e(TAG, "___________________" + dataSnapshot.child("role").getValue(int.class) );


                            Intent myIntent = new Intent(getContext(), MainActivity.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(myIntent);
                            getActivity().finish();


                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }else{

                    Log.e(TAG, "___________________" + "neeeeeeee");
                    Toast.makeText(getContext(),"klíč nenení správný",Toast.LENGTH_LONG).show();

                    textViewNameOfUser.setVisibility(View.GONE);
                    textViewLoginStatus.setVisibility(View.VISIBLE);
                    aVLoadingIndicatorViewLogging.setVisibility(View.GONE);

                    textViewLoginStatus.setText("Ověření se nazdařilo\n\nEmail nebo heslo není správné");
                    textViewLoginStatus.setTextColor(getContext().getResources().getColor(R.color.red700colorAccent));
                    textViewNameOfUser.setText("");
                    textViewLoginWithCredentials.setVisibility(View.VISIBLE);

                }




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return true;
    }


}
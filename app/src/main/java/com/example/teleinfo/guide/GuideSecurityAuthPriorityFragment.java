package com.example.teleinfo.guide;


import static com.example.teleinfo.parameters.MainParameters.AUTH_PREFERENCE;
import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_AUTH;
import static com.example.teleinfo.parameters.MainParameters.PIN_AUTH;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;


public class GuideSecurityAuthPriorityFragment extends Fragment {

    String numberOfPage = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    Button buttonNext;

    LinearLayout guide_fragmentSecurityAuthPriorityLayoutPinSettings;


    RadioGroup radioGroup;
    RadioButton radioButtonCredentials;
    RadioButton radioButtonFingerprint;
    RadioButton radioButtonPin;

    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int counter);
    }

    private GuideSecurityFingerprintFragment.OnGuideOptionClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (GuideSecurityFingerprintFragment.OnGuideOptionClickListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement SettingOptionsFragment.OnOptionClickListener");
        }
    }

    public GuideSecurityAuthPriorityFragment(){

    }

    public static GuideSecurityAuthPriorityFragment newInstance(int counter, int totalCount) {

        GuideSecurityAuthPriorityFragment myFragment = new GuideSecurityAuthPriorityFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_security_auth_priority, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        guide_fragmentSecurityAuthPriorityLayoutPinSettings = (LinearLayout)rootView.findViewById(R.id.guide_fragmentSecurityAuthPriorityLayoutPinSettings);
        buttonNext = (Button)rootView.findViewById(R.id.login_fragmentRegisterButtonLogin);

        radioGroup = rootView.findViewById(R.id.dfsdfsdf);
        radioButtonCredentials = (RadioButton)rootView.findViewById(R.id.radioButtonCredentials);
        radioButtonFingerprint = (RadioButton)rootView.findViewById(R.id.radioButtonFingerprint);
        radioButtonPin = (RadioButton)rootView.findViewById(R.id.radioButtonPin);

        String authPreference = mSharedPreferences.getString(AUTH_PREFERENCE, "CREDENTIALS");

        Boolean authFingerPrint = mSharedPreferences.getBoolean(FINGERPRINT_AUTH, true);
        Boolean authPinPrint = mSharedPreferences.getBoolean(PIN_AUTH, true);

        Log.i("Inmetry", "authFingerPrint: " + authFingerPrint);

        Log.i("Inmetry", "authPinPrint: " + authPinPrint);


        radioButtonCredentials.setChecked(true);

        // pokud nebude zapnute overovani otiskem, nezobrazuj radiobutton
        if(authFingerPrint){
            radioButtonFingerprint.setVisibility(View.VISIBLE);
        }else{
            radioButtonFingerprint.setVisibility(View.GONE);
        }

        // pokud nebude zapnute overovani pinem, nezobrazuj radiobutton
        if(authPinPrint){
            radioButtonPin.setVisibility(View.VISIBLE);
        }else{
            radioButtonPin.setVisibility(View.GONE);
        }

       /* if(authPreference.compareTo("CREDENTIALS") == 0){

            radioButtonCredentials.setChecked(true);
        }

        if(authPreference.compareTo("FINGERPRINT") == 0){

            radioButtonFingerprint.setChecked(true);
        }

        if(authPreference.compareTo("PIN") == 0){

            radioButtonPin.setChecked(true);
        }*/

        radioButtonCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "crede", Toast.LENGTH_SHORT).show();

                mEditor.putString(AUTH_PREFERENCE, "CREDENTIALS" );
                mEditor.commit();

            }
        });

        radioButtonFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(), "finger", Toast.LENGTH_SHORT).show();

                mEditor.putString(AUTH_PREFERENCE, "FINGERPRINT" );
                mEditor.commit();
            }
        });


        radioButtonPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "pin", Toast.LENGTH_SHORT).show();

                mEditor.putString(AUTH_PREFERENCE, "PIN" );
                mEditor.commit();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onGuideOptionClickListener("changeToFinish", +1);

            }
        });

        return rootView;
    }



}
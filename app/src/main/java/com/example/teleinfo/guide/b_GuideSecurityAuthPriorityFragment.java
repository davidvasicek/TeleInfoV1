package com.example.teleinfo.guide;


import static com.example.teleinfo.parameters.MainParameters.AUTH_PRIORITY;
import static com.example.teleinfo.parameters.MainParameters.DEVICE_IS_PAIRED;
import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_HARDWARE_IS_DETECTED;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_CREDENTIALS;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_FINGERPRINT;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_PIN;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class b_GuideSecurityAuthPriorityFragment extends Fragment {

    String numberOfPage = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    RadioButton radioButtonFingerprint;
    RadioButton radioButtonPin;
    RadioButton radioButtonCredentials;
    RadioGroup radioGroupMainGroup;
    Button buttonPrevious;
    Button buttonNext;


    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int keyOptions, String valueOptions);
    }

    private c_a_GuideSecurityFingerprintFragment.OnGuideOptionClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (c_a_GuideSecurityFingerprintFragment.OnGuideOptionClickListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement SettingOptionsFragment.OnOptionClickListener");
        }
    }

    public b_GuideSecurityAuthPriorityFragment(){
    }

    public static b_GuideSecurityAuthPriorityFragment newInstance(int counter, int totalCount) {

        b_GuideSecurityAuthPriorityFragment myFragment = new b_GuideSecurityAuthPriorityFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_auth_priority, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        radioButtonFingerprint = (RadioButton)rootView.findViewById(R.id.guideFragmentAuthPriorityRadioButtonFingerprint);
        radioButtonPin = (RadioButton)rootView.findViewById(R.id.guideFragmentAuthPriorityRadioButtonPin);
        radioButtonCredentials = (RadioButton)rootView.findViewById(R.id.guideFragmentAuthPriorityRadioButtonCredentials);
        radioGroupMainGroup = (RadioGroup)rootView.findViewById(R.id.guideFragmentAuthPriorityRadioGroupMainGroup);
        buttonPrevious = (Button)rootView.findViewById(R.id.guideFragmentAuthPriorityButtonPrevious);
        buttonNext = (Button)rootView.findViewById(R.id.guideFragmentAuthPriorityButtonNext);


        buttonPrevious.setVisibility(View.GONE);

        String authPreference = mSharedPreferences.getString(AUTH_PRIORITY, "CREDENTIALS");

        Boolean fingerprintHardwareIsDetected = mSharedPreferences.getBoolean(FINGERPRINT_HARDWARE_IS_DETECTED, false);

        if(fingerprintHardwareIsDetected){

            radioButtonFingerprint.setVisibility(View.VISIBLE);
            radioButtonFingerprint.setChecked(true);

        }else{

            radioButtonFingerprint.setVisibility(View.GONE);
            radioButtonPin.setChecked(true);
        }



        radioButtonCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "crede", Toast.LENGTH_SHORT).show();

                mEditor.putString(AUTH_PRIORITY, "CREDENTIALS" );
                mEditor.commit();

            }
        });

        radioButtonFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(), "finger", Toast.LENGTH_SHORT).show();

            }
        });


        radioButtonPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "pin", Toast.LENGTH_SHORT).show();

                mEditor.putString(AUTH_PRIORITY, "PIN" );
                mEditor.commit();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButtonFingerprint.isChecked()){



                    mCallback.onGuideOptionClickListener("changeToFingerprintFragment", LOGIN_BY_FINGERPRINT, "");

                }

                if(radioButtonPin.isChecked()){

                    //mEditor.putInt(AUTH_PRIORITY, LOGIN_BY_PIN );
                    //mEditor.commit();

                    mCallback.onGuideOptionClickListener("changeToPinFragment", LOGIN_BY_PIN, "");

                }

                if(radioButtonCredentials.isChecked()){

                    //mEditor.putInt(AUTH_PRIORITY, LOGIN_BY_CREDENTIALS );
                    //mEditor.commit();

                    mCallback.onGuideOptionClickListener("changeToCredentialsFragment", LOGIN_BY_CREDENTIALS, "");

                }




            }
        });

        return rootView;
    }



}
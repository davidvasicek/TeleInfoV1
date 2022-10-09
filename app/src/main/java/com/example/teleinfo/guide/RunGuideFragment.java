package com.example.teleinfo.guide;

import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_HARDWARE_IS_DETECTED;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;


public class RunGuideFragment extends Fragment {

    TextView textViewSubtitle;
    TextView textViewMessage;
    Button buttonRunGuide;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int counter);
    }

    private OnGuideOptionClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnGuideOptionClickListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement SettingOptionsFragment.OnOptionClickListener");
        }
    }


    public RunGuideFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_run_guide, container, false);

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        textViewSubtitle = (TextView)rootView.findViewById(R.id.welcomeScreenSlideSecurityTextViewSubtitle);
        textViewMessage = (TextView)rootView.findViewById(R.id.guide_fragmentRunGuideTextViewMessage);
        buttonRunGuide = (Button)rootView.findViewById(R.id.guide_fragmentRunGuideButtonRunGuide);

        buttonRunGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mSharedPreferences.getBoolean(FINGERPRINT_HARDWARE_IS_DETECTED, true )){

                    mCallback.onGuideOptionClickListener("changeToFingerprintFragment", +1);

                }else{

                    mCallback.onGuideOptionClickListener("changeToPinFragment", +1);
                }



            }
        });

        return rootView;
    }




}
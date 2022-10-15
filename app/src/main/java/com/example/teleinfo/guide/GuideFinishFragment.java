package com.example.teleinfo.guide;


import static android.content.ContentValues.TAG;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.MainActivity;
import com.example.teleinfo.R;


public class GuideFinishFragment extends Fragment {

    String numberOfPage = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    Button guideFragmentFinishButtonContinueToApp;

    boolean status;

    public GuideFinishFragment(){

    }

    public static GuideFinishFragment newInstance(boolean status) {

        GuideFinishFragment myFragment = new GuideFinishFragment();

        Bundle args = new Bundle();
        args.putBoolean("status", status);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_finish, container, false);

        if (getArguments() != null) {

            status = getArguments().getBoolean("status");
            Log.e(TAG, "___________________ kokoktecek" + status );


        }
        guideFragmentFinishButtonContinueToApp = (Button)rootView.findViewById(R.id.guideFragmentFinishButtonContinueToApp);

        guideFragmentFinishButtonContinueToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(getContext(), MainActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(myIntent);
                getActivity().finish();
            }
        });

        return rootView;
    }



}
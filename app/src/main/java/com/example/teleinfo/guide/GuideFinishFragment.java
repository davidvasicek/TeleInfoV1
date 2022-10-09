package com.example.teleinfo.guide;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;


public class GuideFinishFragment extends Fragment {

    String numberOfPage = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    LinearLayout guide_fragmentSecurityAuthPriorityLayoutPinSettings;


    RadioGroup radioGroup;
    RadioButton radioButtonCredentials;
    RadioButton radioButtonFingerprint;
    RadioButton radioButtonPin;

    public GuideFinishFragment(){

    }

    public static GuideFinishFragment newInstance(int counter, int totalCount) {

        GuideFinishFragment myFragment = new GuideFinishFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_finish, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }



        return rootView;
    }



}
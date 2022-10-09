package com.example.teleinfo.guide;


import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;


public class GuidePermissions extends Fragment {

    String numberOfPage = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;





    public GuidePermissions(){

    }


    public static GuidePermissions newInstance(int counter, int totalCount) {

        GuidePermissions myFragment = new GuidePermissions();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_permissions, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();






        return rootView;
    }



}
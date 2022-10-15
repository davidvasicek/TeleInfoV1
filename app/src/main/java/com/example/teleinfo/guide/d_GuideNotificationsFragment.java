package com.example.teleinfo.guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;


public class d_GuideNotificationsFragment extends Fragment {


    Button buttonPrevious;
    Button buttonNext;



    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int counter);
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

    public d_GuideNotificationsFragment(){
    }

    public static d_GuideNotificationsFragment newInstance(int counter, int totalCount) {

        d_GuideNotificationsFragment myFragment = new d_GuideNotificationsFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_notifications, container, false);

        buttonPrevious = (Button)rootView.findViewById(R.id.guideFragmentNotificationsButtonPrevious);
        buttonNext = (Button)rootView.findViewById(R.id.guideFragmentNotificationsButtonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToFinish", -1, null);
            }
        });

        return rootView;
    }



}
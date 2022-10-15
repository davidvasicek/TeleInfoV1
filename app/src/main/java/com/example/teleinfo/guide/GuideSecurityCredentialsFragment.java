package com.example.teleinfo.guide;



import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;

public class GuideSecurityCredentialsFragment extends Fragment {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    TextView textViewNumberOfPage;




    Button buttonNext;
    Button buttonPrevious;




    String numberOfPage = "";



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

    public GuideSecurityCredentialsFragment(){

    }

    public static GuideSecurityCredentialsFragment newInstance(int counter, int totalCount) {
        GuideSecurityCredentialsFragment myFragment = new GuideSecurityCredentialsFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_security_credentials, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        buttonNext = (Button)rootView.findViewById(R.id.guide_fragmentSecurityFingerprintButtonNext);
        buttonPrevious = (Button)rootView.findViewById(R.id.guide_fragmentSecurityFingerprinButtonPrevious);

        textViewNumberOfPage = (TextView)rootView.findViewById(R.id.guide_fragmentSecurityFingerprintTextViewNumberOfPage);
        textViewNumberOfPage.setText(numberOfPage);





        buttonNext.setEnabled(false);
        buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToPinFragment", +1);
            }
        });





        return rootView;
    }







}
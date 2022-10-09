package com.example.teleinfo.guide;


import static com.example.teleinfo.parameters.MainParameters.NUMBER_OF_DEFAULT_PINS;
import static com.example.teleinfo.parameters.MainParameters.PIN;
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
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;
import com.example.teleinfo.login.NewPinFragmentDialog;


public class GuideSecurityPinFragment extends Fragment implements NewPinFragmentDialog.OnNewPinListener {

    Button buttonSetPin;
    TextView textViewPinStatus;

    LinearLayout linearLayoutPinSettings;
    Switch switchEnable;

    Button buttonNext;

    TextView textViewNumberOfPage;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    int numberOfPins;

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

    public GuideSecurityPinFragment(){

    }

    public static GuideSecurityPinFragment newInstance(int counter, int totalCount) {
        GuideSecurityPinFragment myFragment = new GuideSecurityPinFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide__fragment_security_pin, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        buttonSetPin = (Button)rootView.findViewById(R.id.guide_fragmentSecurityPinButtonSetPin);
        linearLayoutPinSettings = (LinearLayout)rootView.findViewById(R.id.guide_fragmentSecurityPinLinearLayoutPinSettings);
        switchEnable = (Switch)rootView.findViewById(R.id.guide_fragmentSecurityPinSwitchEnable);
        textViewPinStatus = (TextView)rootView.findViewById(R.id.guide_fragmentSecurityPinTextViewPinStatus);

        buttonNext = (Button)rootView.findViewById(R.id.guide_fragmentSecurityPinButtonNext);

        textViewNumberOfPage = (TextView)rootView.findViewById(R.id.guide_fragmentSecurityPinTextViewNumberOfPage);

        textViewNumberOfPage.setText(numberOfPage);

        textViewPinStatus.setText("Pin nenataven");
        textViewPinStatus.setTextColor(getContext().getResources().getColor(R.color.red700text_primary));

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        numberOfPins = NUMBER_OF_DEFAULT_PINS;

        switchEnable.setChecked(mSharedPreferences.getBoolean(PIN_AUTH, true));
        switchEnable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?

                if(switchEnable.isChecked()){

                    mEditor.putBoolean(PIN_AUTH, true );
                    mEditor.commit();

                    Log.i("Inmetry", "zapínám ověřování pinem: ");

                    linearLayoutPinSettings.setVisibility(View.VISIBLE);

                }else{

                    mEditor.putBoolean(PIN_AUTH, false );
                    mEditor.commit();

                    Log.i("Inmetry", "vypínám ověřování pinem: ");


                    linearLayoutPinSettings.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonSetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewPinFragmentDialog newPinFragmentDialog = new NewPinFragmentDialog(numberOfPins);
                newPinFragmentDialog.setTargetFragment(GuideSecurityPinFragment.this, 1);
                newPinFragmentDialog.show(getFragmentManager(), "exampleBottomSheet");

            }
        });



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onGuideOptionClickListener("changeToAuthPrioritFragment", +1);

            }
        });

        return rootView;
    }


    @Override
    public void onNewPinListener(String pin) {

        mEditor.putString(PIN, pin );
        mEditor.commit();

       // mEditor.putInt(NUMBER_OF_PINS, numberOfPins);
       // mEditor.commit();

        Log.i("Inmetry", "PIN: " + pin);

        buttonSetPin.setText("Změnit Pin");

        textViewPinStatus.setText("Pin Nataven");
        textViewPinStatus.setTextColor(getContext().getResources().getColor(R.color.green500text_primary));

    }

}
package com.example.teleinfo.guide;


import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_PIN;
import static com.example.teleinfo.parameters.MainParameters.NUMBER_OF_DEFAULT_PINS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;
import com.example.teleinfo.login.NewPinFragmentDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class c_b_GuideSecurityPinFragment extends Fragment {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;







    TextView textViewInstructions;
    TextView textViewBtn1;
    TextView textViewBtn2;
    TextView textViewBtn3;
    TextView textViewBtn4;
    TextView textViewBtn5;
    TextView textViewBtn6;
    TextView textViewBtn7;
    TextView textViewBtn8;
    TextView textViewBtn9;
    TextView textViewOK;
    TextView textViewBtn0;
    ImageView imageViewSuccessStatus;
    ImageView imageViewBtnDelete;
    LinearLayout linearLayoutShowPins;
    LinearLayout linearLayoutPins;
    LinearLayout linearLayoutShowPins1;
    LinearLayout linearLayoutPins1;
    Button buttonResetPin;
    Button buttonShowPin;
    Button buttonPrevious;
    Button buttonNext;






    private LinearLayout linearLayoutsPin[];
    private LinearLayout linearLayoutsPin1[];


    StringBuilder mCodeNew;
    StringBuilder mCodeRepeat;

    boolean mBoolCodeNew;
    boolean mBoolCodeRepeat;

    int mPressButtonCount = 0;



    private LinearLayout testicekSekce[];
    private LinearLayout testicekCara[];
    private TextView testicekText[];
    private TextView testicekText1[];

    boolean isRepeatePassword = false;



    List<String> wrongPins;







    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int keyOptions, String valueOptions);
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

    public c_b_GuideSecurityPinFragment(){

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_auth_by_pin, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");


        }



        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        wrongPins = new ArrayList<>();
        generateWrongPins();


















        textViewInstructions = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewInstructions);
        textViewBtn1 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn1);
        textViewBtn2 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn2);
        textViewBtn3 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn3);
        textViewBtn4 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn4);
        textViewBtn5 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn5);
        textViewBtn6 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn6);
        textViewBtn7 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn7);
        textViewBtn8 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn8);
        textViewBtn9 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn9);
        textViewOK = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewOK);
        textViewBtn0 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByPinTextViewBtn0);
        imageViewSuccessStatus = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByPinImageViewSuccessStatus);
        imageViewBtnDelete = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByPinImageViewBtnDelete);
        linearLayoutShowPins = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByPinLinearLayoutShowPins);
        linearLayoutPins = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByPinLinearLayoutPins);
        linearLayoutShowPins1 = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByPinLinearLayoutShowPins1);
        linearLayoutPins1 = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByPinLinearLayoutPins1);
        buttonResetPin = (Button)rootView.findViewById(R.id.guideFragmentAuthByPinButtonResetPin);
        buttonShowPin = (Button)rootView.findViewById(R.id.guideFragmentAuthByPinButtonShowPin);
        buttonPrevious = (Button)rootView.findViewById(R.id.guideFragmentAuthByPinButtonPrevious);
        buttonNext = (Button)rootView.findViewById(R.id.guideFragmentAuthByPinButtonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToNotificationsFragment", LOGIN_BY_PIN, mCodeNew.toString());
            }
        });
        buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
        buttonNext.setEnabled(false);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToAuthPriorityFragment", -1, null);
            }
        });



        textViewOK.setVisibility(View.GONE);
        imageViewSuccessStatus.setVisibility(View.GONE);
        linearLayoutPins1.setVisibility(View.GONE);


        testicekSekce = new LinearLayout[NUMBER_OF_DEFAULT_PINS];
        testicekText = new TextView[NUMBER_OF_DEFAULT_PINS];
        testicekText1 = new TextView[NUMBER_OF_DEFAULT_PINS];

        testicekCara = new LinearLayout[NUMBER_OF_DEFAULT_PINS];


        linearLayoutsPin = new LinearLayout[NUMBER_OF_DEFAULT_PINS];
        linearLayoutsPin1 = new LinearLayout[NUMBER_OF_DEFAULT_PINS];

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


            testicekText[i] = new TextView(getContext());

            testicekText[i].setLayoutParams(lpSections);
            testicekText[i].setText("*");
            testicekText[i].setTextColor(getContext().getResources().getColor(R.color.text_primary));
            testicekText[i].setTextSize(14);
            testicekText[i].setGravity(Gravity.CENTER | Gravity.BOTTOM);
            testicekText[i].setVisibility(View.GONE);

            linearLayoutShowPins.addView(testicekText[i]);


            testicekText1[i] = new TextView(getContext());

            testicekText1[i].setLayoutParams(lpSections);
            testicekText1[i].setText("*");
            testicekText1[i].setTextColor(getContext().getResources().getColor(R.color.text_primary));
            testicekText1[i].setTextSize(14);
            testicekText1[i].setGravity(Gravity.CENTER | Gravity.BOTTOM);
            testicekText1[i].setVisibility(View.GONE);

            linearLayoutShowPins1.addView(testicekText1[i]);
        }

        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {

            linearLayoutsPin1[i] = new LinearLayout(getContext());

            linearLayoutsPin1[i].setLayoutParams(lpSections);
            linearLayoutsPin1[i].setOrientation(LinearLayout.HORIZONTAL);


            linearLayoutPins1.addView(linearLayoutsPin1[i]);
        }

        setPinIndicator(0);


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
        mCodeRepeat = new StringBuilder(100);


        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isRepeatePassword) {

                    linearLayoutPins1.setVisibility(View.VISIBLE);
                    textViewOK.setVisibility(View.GONE);
                    isRepeatePassword = true;

                    mPressButtonCount = 0;

                    textViewInstructions.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                    textViewInstructions.setText("Zadejte znovu Váš pin");
                    linearLayoutPins1.setVisibility(View.VISIBLE);

                    setPinIndicator(mPressButtonCount);

                }else{




                }





            }
        });



        buttonShowPin.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    showPin();


                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {

                        testicekText[i].setVisibility(View.GONE);
                        testicekText1[i].setVisibility(View.GONE);
                    }
                }

                return false;
            }
        });

        buttonResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar snackbar = Snackbar.make(rootView,"Pro resetování pinu dlouho přidržte",Snackbar.LENGTH_SHORT);
                snackbar.show();

            }
        });



        buttonResetPin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                mCodeNew = new StringBuilder(100);
                mCodeRepeat = new StringBuilder(100);

                mPressButtonCount = 0;

                setPinIndicator(mPressButtonCount);

                isRepeatePassword = false;

                setPinIndicator(mPressButtonCount);

                textViewInstructions.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                textViewInstructions.setText("Zadejte váš nový pin kód");

                textViewOK.setVisibility(View.GONE);
                imageViewBtnDelete.setVisibility(View.GONE);
                linearLayoutPins1.setVisibility(View.GONE);


                return false;
            }
        });









        return rootView;
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

                if(!isRepeatePassword){

                    mCodeNew.append(string);

                }else{

                    mCodeRepeat.append(string);
                }

                // Zkontroluj, jestli počet stisknutých číslic v StrinBuilderu mCode je větší než 1
                // zobraz na obrazovce tlačítko pro mazání znaků
                if(mPressButtonCount >= 1){

                    imageViewBtnDelete.setVisibility(View.VISIBLE);
                }

                setPinIndicator(mPressButtonCount);



                if(mPressButtonCount >= NUMBER_OF_DEFAULT_PINS){

                    if(!isRepeatePassword){


                        textViewOK.setVisibility(View.VISIBLE);
                        imageViewSuccessStatus.setVisibility(View.GONE);

                        for (int i = 0; i < wrongPins.size(); i++) {

                            if(mCodeNew.toString().equals(wrongPins.get(i))){

                                textViewInstructions.setTextColor(getContext().getResources().getColor(R.color.red700colorAccent));
                                textViewInstructions.setText("Váš pin je příliš slabý. Resetujte pin");

                                textViewOK.setVisibility(View.GONE);
                                imageViewSuccessStatus.setVisibility(View.VISIBLE);

                                break;

                            }

                        }









                    }else{

                        Log.e("?????????", "a ........" );

                        Log.e("?????????", "a ........ " + mCodeRepeat );
                        Log.e("?????????", "a ........ " + mCodeNew);

                        if(mCodeNew.toString().equals(mCodeRepeat.toString())){
                            Log.e("?????????", "a tecvcvcvcvd" );
                            textViewOK.setVisibility(View.GONE);
                            imageViewBtnDelete.setVisibility(View.GONE);
                            imageViewSuccessStatus.setVisibility(View.GONE);

                            textViewInstructions.setTextColor(getContext().getResources().getColor(R.color.green500text_primaryDark));
                            textViewInstructions.setText("Pin nastaven");



                            buttonNext.setEnabled(true);
                            buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_primary));

                        }
                    }



                }else{

                    textViewOK.setVisibility(View.GONE);
                    imageViewSuccessStatus.setVisibility(View.GONE);

                }





            }
        }
    };


    private View.OnClickListener mOnDeleteButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            if(!isRepeatePassword){

                textViewInstructions.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                textViewInstructions.setText("Zadejte váš nový pin kód");

            }

            mPressButtonCount--;
            setPinIndicator(mPressButtonCount);

            if(mPressButtonCount<0){
                return;
            }

            if (mPressButtonCount != -1) {

                if(!isRepeatePassword){

                    mCodeNew.delete(mCodeNew.length()-1, mCodeNew.length());

                }else{

                    mCodeRepeat.delete(mCodeRepeat.length()-1, mCodeRepeat.length());
                }
            }

            if(mPressButtonCount < 1){

                imageViewBtnDelete.setVisibility(View.INVISIBLE);

            }



            if(mPressButtonCount >= NUMBER_OF_DEFAULT_PINS){

                textViewOK.setVisibility(View.VISIBLE);
                imageViewSuccessStatus.setVisibility(View.GONE);

            }else{

                textViewOK.setVisibility(View.GONE);
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

        if(!isRepeatePassword){

            for(int i = 0; i<NUMBER_OF_DEFAULT_PINS; i++){

                if(i<numberSelectedNumbers){

                    linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_primary));
                }
                else{

                    linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                }
            }

        }else{

            char p, rp;

            for(int i = 0; i<NUMBER_OF_DEFAULT_PINS; i++){

                if(i<numberSelectedNumbers){

                    p = mCodeRepeat.charAt(i);
                    rp = mCodeNew.charAt(i);


                    if (p == rp) {

                        linearLayoutsPin1[i].setBackgroundColor(getActivity().getResources().getColor(R.color.green500text_primary));

                    } else {

                        linearLayoutsPin1[i].setBackgroundColor(getActivity().getResources().getColor(R.color.red700text_primary));
                    }
                }
                else{

                    linearLayoutsPin1[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                }
            }
        }
    }

    public void showPin(){

        for(int i = 0; i<NUMBER_OF_DEFAULT_PINS; i++){

            if(mCodeNew.length()>0){

                if(i<mCodeNew.length()){

                    testicekText[i].setText(mCodeNew.charAt(i) + "");
                    testicekText[i].setVisibility(View.VISIBLE);
                }else{

                    testicekText[i].setVisibility(View.INVISIBLE);
                }

            }



            if(isRepeatePassword){

                if(mCodeRepeat.length()>0){

                    if(i<mCodeRepeat.length()){

                        testicekText1[i].setText(mCodeRepeat.charAt(i) + "");
                        testicekText1[i].setVisibility(View.VISIBLE);
                    }else{

                        testicekText1[i].setVisibility(View.INVISIBLE);
                    }


                }

            }
        }

    }


    void generateWrongPins(){

        StringBuilder s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 1; i <= NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(i);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(i);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(0);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(1);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(2);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(3);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(4);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(5);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(6);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(7);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(8);
        }
        wrongPins.add(s.toString());

        s = new StringBuilder(NUMBER_OF_DEFAULT_PINS);
        for (int i = 0; i < NUMBER_OF_DEFAULT_PINS; i++) {
            s.append(9);
        }
        wrongPins.add(s.toString());


    }


}
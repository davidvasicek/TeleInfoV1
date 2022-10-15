package com.example.teleinfo.login;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.teleinfo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

public class NewPinFragmentDialog extends BottomSheetDialogFragment {

    TextView textViewTitle;
    TextView textViewInstructions;
    Button textViewButton1;
    TextView textViewButton2;
    TextView textViewButton3;
    TextView textViewButton4;
    TextView textViewButton5;
    TextView textViewButton6;
    TextView textViewButton7;
    TextView textViewButton8;
    TextView textViewButton9;
    TextView textViewButton0;
    ImageView imageViewSuccessStatus;
    ImageView imageViewButtonDelete;
    TextView button1;
    TextView button2;
    TextView button3;
    TextView button4;
    TextView button5;
    TextView button6;
    TextView button7;
    TextView button8;
    TextView button9;
    TextView button0;
    ImageView buttonDelete;
    Button buttonCancel;
    LinearLayout linearLayoutPins;
    LinearLayout linearLayoutPins1;
    TextView textViewOK;

    LinearLayout testicek;
    LinearLayout testicek1;





    private LinearLayout linearLayoutsPin[];
    private LinearLayout linearLayoutsPin1[];

    int numberOfPins;

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


    Button buttonResetPin;
    Button buttonShowPin;


    public interface OnNewPinListener {
        void onNewPinListener(String pin);
    }

    private OnNewPinListener mCallback;


    @Override
    public void onAttach(Context context) {

        if(getTargetFragment() == null){

            try{
                mCallback = (OnNewPinListener) context;
            }catch (ClassCastException e){

                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }

        }else{

            try{
                mCallback = (OnNewPinListener) getTargetFragment();
            }catch (ClassCastException e){

                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }

        super.onAttach(context);
    }



    public NewPinFragmentDialog(int numberOfPins){

        this.numberOfPins = numberOfPins;
    }

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_fragment_new_pin_dialog, container, false);


        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        textViewTitle = (TextView) rootView.findViewById(R.id.loginWithPinDialogTextViewTitle);
        textViewInstructions = (TextView) rootView.findViewById(R.id.loginWithPinDialogTextViewInstructions);
        textViewButton1 = (Button) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton1);
        textViewButton2 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton2);
        textViewButton3 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton3);
        textViewButton4 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton4);
        textViewButton5 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton5);
        textViewButton6 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton6);
        textViewButton7 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton7);
        textViewButton8 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton8);
        textViewButton9 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton9);
        textViewButton0 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton0);
        imageViewSuccessStatus = (ImageView) rootView.findViewById(R.id.login_fragmentNewPinImageViewSuccessStatus);
        imageViewButtonDelete = (ImageView) rootView.findViewById(R.id.login_fragmentNewPinImageViewButtonDelete);
        button1 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton1);
        button2 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton2);
        button3 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton3);
        button4 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton4);
        button5 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton5);
        button6 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton6);
        button7 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton7);
        button8 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton8);
        button9 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton9);
        button0 = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewButton0);
        buttonDelete = (ImageView) rootView.findViewById(R.id.login_fragmentNewPinImageViewButtonDelete);
        buttonCancel = (Button) rootView.findViewById(R.id.loginWithPinDialogButtonCancel);
        linearLayoutPins = (LinearLayout) rootView.findViewById(R.id.login_fragmentNewPinLinearLayoutPins);
        linearLayoutPins1 = (LinearLayout) rootView.findViewById(R.id.login_fragmentNewPinLinearLayoutPins1);
        testicek = (LinearLayout) rootView.findViewById(R.id.testicek);
        testicek1 = (LinearLayout) rootView.findViewById(R.id.testicek1);

        buttonResetPin = (Button) rootView.findViewById(R.id.login_fragmentNewPinButtonResetPin);
        buttonShowPin = (Button) rootView.findViewById(R.id.login_fragmentNewPinButtonShowPin);


        textViewOK = (TextView) rootView.findViewById(R.id.login_fragmentNewPinTextViewOK);

        textViewOK.setVisibility(View.GONE);
        imageViewSuccessStatus.setVisibility(View.GONE);
        linearLayoutPins1.setVisibility(View.GONE);


        testicekSekce = new LinearLayout[numberOfPins];
        testicekText = new TextView[numberOfPins];
        testicekText1 = new TextView[numberOfPins];

        testicekCara = new LinearLayout[numberOfPins];


        linearLayoutsPin = new LinearLayout[numberOfPins];
        linearLayoutsPin1 = new LinearLayout[numberOfPins];

        LinearLayout.LayoutParams lpSections = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f); // Height of TextView
        lpSections.setMargins(15, 0, 15, 0);

        for (int i = 0; i < numberOfPins; i++) {

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

            testicek.addView(testicekText[i]);


            testicekText1[i] = new TextView(getContext());

            testicekText1[i].setLayoutParams(lpSections);
            testicekText1[i].setText("*");
            testicekText1[i].setTextColor(getContext().getResources().getColor(R.color.text_primary));
            testicekText1[i].setTextSize(14);
            testicekText1[i].setGravity(Gravity.CENTER | Gravity.BOTTOM);
            testicekText1[i].setVisibility(View.GONE);

            testicek1.addView(testicekText1[i]);
        }

        for (int i = 0; i < numberOfPins; i++) {

            linearLayoutsPin1[i] = new LinearLayout(getContext());

            linearLayoutsPin1[i].setLayoutParams(lpSections);
            linearLayoutsPin1[i].setOrientation(LinearLayout.HORIZONTAL);


            linearLayoutPins1.addView(linearLayoutsPin1[i]);
        }

        setPinIndicator(0);


        //nastavení listeneru
        textViewButton1.setOnClickListener(mOnKeyClickListener);
        textViewButton2.setOnClickListener(mOnKeyClickListener);
        textViewButton3.setOnClickListener(mOnKeyClickListener);
        textViewButton4.setOnClickListener(mOnKeyClickListener);
        textViewButton5.setOnClickListener(mOnKeyClickListener);
        textViewButton6.setOnClickListener(mOnKeyClickListener);
        textViewButton7.setOnClickListener(mOnKeyClickListener);
        textViewButton8.setOnClickListener(mOnKeyClickListener);
        textViewButton9.setOnClickListener(mOnKeyClickListener);
        textViewButton0.setOnClickListener(mOnKeyClickListener);
        imageViewButtonDelete.setOnClickListener(mOnDeleteButtonClickListener);

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

                    textViewInstructions.setText("Zadejte znovu Váš pin");
                    testicek1.setVisibility(View.VISIBLE);

                    setPinIndicator(mPressButtonCount);

                }else{


                    mCallback.onNewPinListener(mCodeNew.toString());
                    dismiss();

                }





            }
        });



        buttonShowPin.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                   if (event.getAction() == MotionEvent.ACTION_DOWN) {

                           showPin();


                   } else if (event.getAction() == MotionEvent.ACTION_UP) {

                       for (int i = 0; i < numberOfPins; i++) {

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

                textViewInstructions.setText("Zadejte váš nový pin kód");

                textViewOK.setVisibility(View.GONE);
                buttonDelete.setVisibility(View.GONE);
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

                if(mPressButtonCount >= numberOfPins){

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

                    imageViewButtonDelete.setVisibility(View.VISIBLE);
                }

                setPinIndicator(mPressButtonCount);



                if(mPressButtonCount >= numberOfPins){

                    if(!isRepeatePassword){

                        textViewOK.setVisibility(View.VISIBLE);
                        imageViewSuccessStatus.setVisibility(View.GONE);

                    }else{

                        Log.e("?????????", "a ........" );

                        Log.e("?????????", "a ........ " + mCodeRepeat );
                        Log.e("?????????", "a ........ " + mCodeNew);

                        if(mCodeNew.toString().equals(mCodeRepeat.toString())){
                            Log.e("?????????", "a tecvcvcvcvd" );
                            textViewOK.setVisibility(View.VISIBLE);
                            imageViewSuccessStatus.setVisibility(View.GONE);
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

                imageViewButtonDelete.setVisibility(View.INVISIBLE);

            }



            if(mPressButtonCount >= numberOfPins){

                textViewOK.setVisibility(View.VISIBLE);
                imageViewSuccessStatus.setVisibility(View.GONE);

            }else{

                textViewOK.setVisibility(View.GONE);
                imageViewSuccessStatus.setVisibility(View.GONE);

            }

           /* if(mCode.length() >= numberOfPins){
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

            for(int i = 0; i<numberOfPins; i++){

                if(i<numberSelectedNumbers){

                    linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_primary));
                }
                else{

                    linearLayoutsPin[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                }
            }

        }else{

            char p, rp;

            for(int i = 0; i<numberOfPins; i++){

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

        for(int i = 0; i<numberOfPins; i++){

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


}
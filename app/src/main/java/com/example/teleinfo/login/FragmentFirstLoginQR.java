package com.example.teleinfo.login;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.dialogs.ColorSelectBottomSheetDialog;
import com.example.teleinfo.rozvrh.DinningRoomMenuObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirstLoginQR extends Fragment implements BottomSheetDialogQRCodeReading.QRCodeParseListener{

    // https://androiddvlpr.com/zxing-android-example/


    Button activityLoginWithKeyButtcconLogin;
    TextView activityLoginWithKxeyccTextViewKeyRequest;
    TextView activityLoginWithKeyTextViewLostKey;

    public FragmentFirstLoginQR() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.login_fragment_first_login_qr, container, false);

        activityLoginWithKeyButtcconLogin = (Button)rootView.findViewById(R.id.activityLoginWithKeyButtcconLogin);
        activityLoginWithKxeyccTextViewKeyRequest = (TextView)rootView.findViewById(R.id.activityLoginWithKxeyccTextViewKeyRequest);
        activityLoginWithKeyTextViewLostKey = (TextView)rootView.findViewById(R.id.activityLoginWithKeyTextViewLostKey);




        activityLoginWithKeyButtcconLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogQRCodeReading bottomSheetDialogQRCodeReading = new BottomSheetDialogQRCodeReading();
                bottomSheetDialogQRCodeReading.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        activityLoginWithKeyTextViewLostKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogCredentialsLogin bottomSheetDialogCredentialsLogin = new BottomSheetDialogCredentialsLogin();
                bottomSheetDialogCredentialsLogin.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });




        return rootView;
    }


    @Override
    public void applyQRCodeParseListener(String message) {
        activityLoginWithKxeyccTextViewKeyRequest.setText(message);
    }
}










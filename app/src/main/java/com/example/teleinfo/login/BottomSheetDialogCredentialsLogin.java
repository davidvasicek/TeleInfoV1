package com.example.teleinfo.login;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

public class BottomSheetDialogCredentialsLogin extends BottomSheetDialogFragment {

    View contentView;
    private CredentialsListener credentialsListener;

    Button buttonLogin;
    EditText editTextUserName, editTextPassword;

    String userName, password;


    public BottomSheetDialogCredentialsLogin() {

    }

    @Override
    public void onAttach(Context context) {

        if(getParentFragmentManager() == null){

            try{
                credentialsListener = (CredentialsListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }else{
            try{
                credentialsListener = (CredentialsListener) getParentFragment();
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }

    public interface CredentialsListener {
        void applyCredentialsListener(String username, String password);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        contentView = View.inflate(getContext(), R.layout.login_bottom_sheet_dialog_credentials_login, null);
        dialog.setContentView(contentView);

        buttonLogin = (Button)contentView.findViewById(R.id.loginWithCredentialsActivityButtonLogin);
        editTextUserName  = (EditText)contentView.findViewById(R.id.loginWithCredentialsActivityEditTextEmailInput);
        editTextPassword = (EditText)contentView.findViewById(R.id.loginWithCredentialsActivityEditTextPasswordInput);

        userName = editTextUserName.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        Snackbar snackbar = Snackbar.make(contentView,"Error: " + "ffffffffff",Snackbar.LENGTH_SHORT);
        snackbar.show();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(userName)){

                    Toast.makeText(getContext(),"Email je prázdný",Toast.LENGTH_LONG).show();
                    return;
                }

                if(!userName.contains("@") && !userName.contains(".") || userName.contains(" ")){

                    Toast.makeText(getContext(),"Email je ve špatném tvaru",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(getContext(),"Email je prázdný",Toast.LENGTH_LONG).show();
                    return;
                }


                credentialsListener.applyCredentialsListener(userName, password);
                dismiss();

            }
        });


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }



}

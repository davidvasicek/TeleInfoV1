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
import android.util.Log;
import android.view.View;
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
import com.google.zxing.Result;

public class BottomSheetDialogQRCodeReading extends BottomSheetDialogFragment {


    private CodeScanner mCodeScanner;
    View contentView;
    private QRCodeParseListener qrCodeParseListener;


    public BottomSheetDialogQRCodeReading() {

    }

    @Override
    public void onAttach(Context context) {

        if(getParentFragmentManager() == null){

            try{
                qrCodeParseListener = (QRCodeParseListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }else{
            try{
                qrCodeParseListener = (QRCodeParseListener) getParentFragment();
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }

    public interface QRCodeParseListener {
        void applyQRCodeParseListener(String message);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        contentView = View.inflate(getContext(), R.layout.login_bottom_sheet_dialog_qr_code_reading, null);
        dialog.setContentView(contentView);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 123);
        } else {
           startScanning();
        }


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }

    private void startScanning() {
        CodeScannerView scannerView = contentView.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(getContext(), scannerView);
        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();
                        qrCodeParseListener.applyQRCodeParseListener(result.getText());
                        dismiss();
                    }
                });
            }
        });
     /*   scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Camera permission granted", Toast.LENGTH_LONG).show();
                startScanning();
            } else {
                Toast.makeText(getContext(), "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

}

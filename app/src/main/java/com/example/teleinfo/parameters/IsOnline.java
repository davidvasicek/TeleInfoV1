package com.example.teleinfo.parameters;

import static com.example.teleinfo.parameters.MainParameters.ERROR_CODE_NO_CONNECTIVITY;
import static com.example.teleinfo.parameters.MainParameters.ERROR_CODE_NO_WIFI_NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;


public class IsOnline {

    Context mContext;

    public IsOnline(Context context) {
        this.mContext = context;
    }

    public int isOnline(){

        Boolean isConnected = false,
                isWiFi = false,
                isMobile = false;

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            isConnected = activeNetwork.isConnectedOrConnecting();
        }

        if (isConnected) {

            if(isCennectivity()) {

                return IS_ONLINE;

            }else{

                return ERROR_CODE_NO_CONNECTIVITY;

            }

        }else{

            return ERROR_CODE_NO_WIFI_NO_DATA;
        }

    }

    public boolean isCennectivity() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

}


package com.example.teleinfo.administration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 10.01.2018.
 */

public class ObjectAndroidDevices {

    public String Device_ID;
    public String Device_Name;
    public String Token;
    public String key;
    public long timeStamp;
    public boolean accessStatus;

    public ObjectAndroidDevices(){

    }

    public ObjectAndroidDevices(String Device_ID, String Device_Name, String Token, String key, long timeStamp, boolean accessStatus) {

        this.Device_ID = Device_ID;
        this.Device_Name = Device_Name;
        this.Token = Token;
        this.key = key;
        this.timeStamp = timeStamp;
        this.accessStatus = accessStatus;
    }


}

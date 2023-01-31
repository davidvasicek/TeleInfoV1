package com.example.teleinfo.fcm;




import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.teleinfo.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    boolean sss = true;

    public void onMessageReceived(RemoteMessage remoteMessage) {


        notify(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());


    }

    public void notify(String title, String message) {


        SharedPreferences mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        //mEditor.putString(PAIRING_STATUS, "waitingForUserVerification");
        //mEditor.commit();

        //mSharedPreferences.edit().putBoolean().commit()

        if(sss){

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "notification_channel")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(123, builder.build());

        }


    }








    /*

    private static final String TAG = "MyFirebaseMsgService";


    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


            String title = "";
            int resourceId = 0;



            Log.e(TAG, "__fcm__ " + remoteMessage.getData().get("body"));

            //sendNotification(title, remoteMessage.getData().get("body"),resourceId);

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }*/

   /* public void onNewToken(String token) {

        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }*/

/*
    private void sendRegistrationToServer(String token) {

       // String Device_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);



        //if(System.currentTimeMillis()/1000 < 1582416000) {

            //getSharedPreferences("_", MODE_PRIVATE).edit().putString("token", token).apply();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("FCM/DeviceInfo");

            //AndroidDevicesObject androidDevicesObject = new AndroidDevicesObject();

            //androidDevicesObject.Device_ID = Device_ID;
            //androidDevicesObject.Device_Name = android.os.Build.BRAND + " " + android.os.Build.MODEL;
            //androidDevicesObject.Token = token;

            myRef.setValue(token);

       // }





    }
*/
    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */



//    private void sendNotification(String title, String messageBody, int resourceId) {
//
//        String NOTIFICATION_CHANNEL_ID = "10001" ;
//        String default_notification_channel_id = "default" ;
//
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), default_notification_channel_id ) ;
//        mBuilder.setContentTitle(title) ;
//        mBuilder.setContentText(messageBody) ;
//        mBuilder.setLargeIcon(BitmapFactory. decodeResource (getResources() , resourceId)) ;
//        mBuilder.setSmallIcon(R.drawable.logo3);
//        mBuilder.setAutoCancel( true ) ;
//        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
//            int importance = NotificationManager. IMPORTANCE_HIGH ;
//            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
//            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
//            assert mNotificationManager != null;
//            mNotificationManager.createNotificationChannel(notificationChannel) ;
//        }
//        assert mNotificationManager != null;
//        mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
//
//
//
//
//
//
//
//
//
//
//
//
////        Intent intent = new Intent(this, MainActivity.class);
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
////                PendingIntent.FLAG_ONE_SHOT);
////
////        String channelId = "loklo";
////        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
////        NotificationCompat.Builder notificationBuilder =
////                new NotificationCompat.Builder(this, channelId)
////                        .setSmallIcon(R.mipmap.ic_plant)
////                        .setContentTitle(title)
////                        .setContentText(messageBody)
////                        .setAutoCancel(true)
////                        .setSound(defaultSoundUri)
////                        .setContentIntent(pendingIntent);
////
////        NotificationManager notificationManager =
////                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////
////        // Since android Oreo notification channel is needed.
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            NotificationChannel channel = new NotificationChannel(channelId,
////                    "Channel human readable title",
////                    NotificationManager.IMPORTANCE_DEFAULT);
////            notificationManager.createNotificationChannel(channel);
////        }
////
////        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//}
}

package com.agea.altoque.push;


import android.content.Context;
import android.content.Intent;
import com.infobip.push.AbstractPushReceiver;
import com.infobip.push.PushNotification;


public class MyPushReceiver extends AbstractPushReceiver {

    @Override
    public void onRegistered(Context context) {
//        Toast.makeText(context, "Successfully registered.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRegistrationRefreshed(Context context) {
//        Toast.makeText(context, "Registration is refreshed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNotificationReceived(PushNotification notification, Context context)
    {
        Intent imageDownloader = new Intent(context, CustomIntentServicePush.class);
        imageDownloader.putExtra("notification", notification);
        context.startService(imageDownloader);

    }

    @Override
    protected void onNotificationOpened(PushNotification notification, Context context) {

        /*

        MainActivity.flagPush=true;
        MainActivity.titlePush = notification.getTitle();
        MainActivity.descPush = notification.getMessage();


        Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
        notifyIntent.setClass(context, MainActivity.class);
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notifyIntent);
        */




        /*
        Intent popUpIntent = new Intent(context, PopUpActivity.class);
        popUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        popUpIntent.putExtra("title", notification.getTitle());
        popUpIntent.putExtra("description", notification.getMessage());
        context.startActivity(popUpIntent);
        */


    }

    @Override
    public void onUnregistered(Context context) {
//        Toast.makeText(context, "Successfully unregistered.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int reason, Context context) {
//        Toast.makeText(context, "Error occured: " + reason, Toast.LENGTH_SHORT).show();
        //Log.w(LOG, "Error relacionado con PUSH: " + reason);
    }
}

package com.agea.altoque.push;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.agea.altoque.R;
import com.agea.altoque.screen.story.MainActivity;
import com.infobip.push.PushNotification;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 Lucas * * *
 */
public class CustomIntentServicePush extends IntentService
{
    public CustomIntentServicePush()
    {
        super(CustomIntentServicePush.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();

        if (extras != null && !extras.isEmpty())
        {
            PushNotification pushNotification = intent.getParcelableExtra("notification");
            displayNotification(pushNotification);
        }
    }

    private void displayNotification(PushNotification pushNotification)
    {
        Context context = getApplicationContext();
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String bigText = "";
        String bigContentTitle = "";
        String summaryText = "";
        String imgUrl = "";

        final int sound = pushNotification.makeSound() ? Notification.DEFAULT_SOUND : 0;
        final int vibrate = pushNotification.vibrate() ? Notification.DEFAULT_VIBRATE : 0;
        final int light = pushNotification.showLights() ? Notification.DEFAULT_LIGHTS : 0;

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setContentIntent(contentIntent)
                .setLargeIcon(applicationIconBitmap(context))
                .setSmallIcon(R.mipmap.altoque_launcher) //this is your custom made status bar icon
                .setContentTitle(pushNotification.getTitle())
                .setContentText(pushNotification.getMessage())
                .setDefaults(sound|vibrate|light)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        try
        {
            final JSONObject additionalInfo = new JSONObject(pushNotification.getAdditionalInfo());
            imgUrl = additionalInfo.optString("imgUrl");
            bigText = additionalInfo.optString("bigText");
            bigContentTitle = additionalInfo.optString("bigContentTitle");
            summaryText = additionalInfo.optString("summaryText");

            //
            // final JSONArray actions = additionalInfo.optJSONArray("actions");

            //iterate through actions
            /*
            for (int i = 0 ; i < actions.length(); i++)
            {
                if (actions.getString(i).equals("delete")){
                    notificationBuilder.addAction(android.R.drawable.ic_delete, actions.getString(i), contentIntent);
                    //TODO set pendingIntent for action
                }

                if (actions.getString(i).equals("add"))
                {
                    notificationBuilder.addAction(android.R.drawable.ic_input_add, actions.getString(i), contentIntent);
                    //TODO set pendingIntent for action
                }
            }
            */

        } catch (JSONException ex)
        {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        }


        if (!TextUtils.isEmpty(imgUrl))
        {
            Bitmap remote_picture;

            // Create the style object with BigPictureStyle subclass.
            NotificationCompat.BigPictureStyle notificationStyle = new NotificationCompat.BigPictureStyle();
            notificationStyle.setBigContentTitle(bigContentTitle);
            notificationStyle.setSummaryText(summaryText);

            try
            {
                remote_picture = Picasso.with(context).load(imgUrl).get();
                notificationStyle.bigPicture(remote_picture);
            }
            catch (IOException e)
            {
                notificationStyle.bigPicture(applicationIconBitmap(context));
            }

            notificationBuilder.setStyle(notificationStyle);

        }
        else if (!TextUtils.isEmpty(bigText))
        {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
        }

        mNotificationManager.notify(1, notificationBuilder.build()); //TODO replace 1 with dynamic notificationID, or leave it like this to be constant
    }

    int applicationIcon(final Context context)
    {
        try
        {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).icon;
        }
        catch (PackageManager.NameNotFoundException ex)
        {
            return -1;
        }
    }

    Bitmap applicationIconBitmap(final Context context)
    {
        return BitmapFactory.decodeResource(context.getResources(), applicationIcon(context));
    }
}

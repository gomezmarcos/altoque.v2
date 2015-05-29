package com.agea.altoque;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.agea.altoque.helpers.Secret;
import com.infobip.push.PushNotificationBuilder;
import com.infobip.push.PushNotificationManager;

public class ApplicationContextProvider extends Application {
    private static Context sContext;
    private PushNotificationManager manager;
    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        sContext = getApplicationContext();
        initPush();
    }

    private void initPush()
    {
        manager = new PushNotificationManager(sContext);
        manager.setDebugModeEnabled(false); // Enable debug mode to view log
        manager.initialize(Secret.SENDER_ID, Secret.INFOBIP_APPLICATION_ID, Secret.INFOBIP_APPLICATION_SECRET);

        if (!manager.isRegistered())
        {
            manager.register();
        }
        else
        {
            if (!Secret.INFOBIP_APPLICATION_ID.equals(manager.getApplicationId()))
            {
                manager.unregister();
                manager.register();
            }
        }

        PushNotificationBuilder builder = new PushNotificationBuilder(getApplicationContext());
        builder.setTickerText(getString(R.string.push_statusbar_text));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder.setIconDrawableId(R.mipmap.push_5);
        }
        else
        {
            builder.setIconDrawableId(R.mipmap.push_statusbar_icon);
        }
    }

}
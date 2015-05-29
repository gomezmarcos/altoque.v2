package com.agea.altoque.settings.weather_screen;

import com.agea.altoque.model.WeatherLocations;

/**
 * Created by lbais on 17/04/2015.
 */
public class WeatherSettingsController {
    private SettingsWeatherActivity activity;

    public WeatherSettingsController(SettingsWeatherActivity activity)
    {
        this.activity = activity;
    }

    public void radioPressed(String name)
    {
        if (activity.getLocations() != null)
        {
            for (int i = 0; i<activity.getLocations().size(); i++)
            {
                WeatherLocations location = (WeatherLocations) activity.getLocations().get(i);

                if (location.getName().equals(name))
                {
                    location.setState(true);
                    activity.setCity(location.getName());
                }
                else
                {
                    location.setState(false);
                }
            }
        }

        if (activity.getmAdapter() != null)
        {
            activity.getmAdapter().notifyDataSetChanged();
        }


    }
}

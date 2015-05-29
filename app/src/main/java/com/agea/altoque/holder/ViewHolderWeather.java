package com.agea.altoque.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.agea.altoque.R;
import com.agea.altoque.model.Weather;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by marcos on 26/05/15.
 */
public class ViewHolderWeather extends RecyclerView.ViewHolder{

    Context context;
    TextView conditions, name, temp, date;
    LinearLayout background_weather;
    //TextView date;

    public ViewHolderWeather(View itemView)
    {
        super(itemView);
        context = itemView.getContext();
        conditions = (TextView)itemView.findViewById(R.id.conditions);
        name = (TextView)itemView.findViewById(R.id.name);
        temp = (TextView)itemView.findViewById(R.id.temp);
        date = (TextView)itemView.findViewById(R.id.date);
        background_weather = (LinearLayout) itemView.findViewById(R.id.background_weather);
    }

    public void populate(Weather weather)
    {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:MM");
        date.setText(weather.getDate().toString(fmt) + " HS");
        name.setText(weather.getItem().getName());
        conditions.setText(weather.getItem().getConditions());
        temp.setText(weather.getItem().getTemp());
        background_weather.setBackground(context.getDrawable(weather.getBackgroundDrawableIdFromIcon(weather.getItem().getIcon())));
        //Set background background_weather
        //FORECAST & ICONS
    }

}

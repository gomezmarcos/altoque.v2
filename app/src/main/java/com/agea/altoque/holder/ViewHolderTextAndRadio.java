package com.agea.altoque.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.agea.altoque.R;
import com.agea.altoque.settings.weather_screen.WeatherSettingsController;

/**
 * Created by lbais on 16/04/2015.
 */

public class ViewHolderTextAndRadio extends RecyclerView.ViewHolder {

    private TextView text;
    private RadioButton radio;

    public ViewHolderTextAndRadio(View itemView, Context context, final WeatherSettingsController weatherSettingsController)
    {
        super(itemView);
        radio = (RadioButton) itemView.findViewById(R.id.radioButton);
        text = (TextView) itemView.findViewById(R.id.text);

        radio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                weatherSettingsController.radioPressed(text.getText().toString());
            }
        });

    }

    public void setItemData(String name)
    {
        text.setText(name);
    }
    public void setRadioState(boolean state)
    {
        radio.setChecked(state);
    }



}

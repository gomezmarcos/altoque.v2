package com.agea.altoque.settings.zodiac_screen;
import android.content.SharedPreferences;
import android.view.View;
import com.agea.altoque.R;

/**
 * Created by lbais on 17/04/2015.
 */
public class ZodiacSettingsController implements View.OnClickListener
{
    private SettingsZodiacActivity activity;
    private SharedPreferences.Editor editor;

    public ZodiacSettingsController(SettingsZodiacActivity activity, SharedPreferences.Editor editor)
    {
        this.activity = activity;
        this.editor = editor;
    }

    @Override
    public void onClick(View v)
    {
        int signo = -1;
        switch (v.getId())
        {
            case R.id.aries:
            {
                editor.putInt("horoscopo_position",0);
                break;
            }
            case R.id.tauro:
            {
                editor.putInt("horoscopo_position",1);
                break;
            }
            case R.id.geminis:
            {
                editor.putInt("horoscopo_position",2);
                break;
            }
            case R.id.cancer:
            {
                editor.putInt("horoscopo_position",3);
                break;
            }
            case R.id.leo:
            {
                editor.putInt("horoscopo_position",4);
                break;
            }
            case R.id.virgo:
            {
                editor.putInt("horoscopo_position",5);
                break;
            }
            case R.id.libra:
            {
                editor.putInt("horoscopo_position",6);
                break;
            }
            case R.id.escorpio:
            {
                editor.putInt("horoscopo_position",7);
                break;
            }
            case R.id.sagitario:
            {
                editor.putInt("horoscopo_position",8);
                break;
            }
            case R.id.capricornio:
            {
                editor.putInt("horoscopo_position",9);
                break;
            }
            case R.id.acuario:
            {
                editor.putInt("horoscopo_position",10);
                break;
            }
            case R.id.piscis:
            {
                editor.putInt("horoscopo_position",11);
                break;
            }
        }
        editor.commit();

        activity.setGreyIcons();
        v.setBackgroundColor(activity.getResources().getColor(R.color.red_alert));

        activity.refreshSigno();
        activity.setHoroscopo();
    }
}

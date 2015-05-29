package com.agea.altoque.settings.content_screen;

import android.content.SharedPreferences;

/**
 * Created by lbais on 17/04/2015.
 */
public class ContentSettingsController
{
    private SettingsContentActivity activity;
    private SharedPreferences.Editor editor;

    public ContentSettingsController(SettingsContentActivity activity, SharedPreferences.Editor editor)
    {
        this.activity = activity;
        this.editor = editor;
    }


    public void checkBoxPressed(int pos, boolean state)
    {
        switch(pos)
        {
            case 0:
            {
                editor.putBoolean("content_0", state);
                break;
            }
            case 1:
            {
                editor.putBoolean("content_1", state);
                break;
            }
            case 2:
            {
                editor.putBoolean("content_2", state);
                break;
            }
            case 3:
            {
                editor.putBoolean("content_3", state);
                break;
            }
            case 4:
            {
                editor.putBoolean("content_4", state);
                break;
            }

        }
        editor.commit();

    }

}

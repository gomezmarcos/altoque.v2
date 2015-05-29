package com.agea.altoque.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.agea.altoque.settings.content_screen.ContentSettingsController;
import com.agea.altoque.R;

/**
 * Created by lbais on 16/04/2015.
 */
public class ViewHolderTextAndCheckbox extends RecyclerView.ViewHolder {

    private TextView text;
    private CheckBox check;
    public ViewHolderTextAndCheckbox(View itemView, Context context, final ContentSettingsController contentSettingsController)
    {
        super(itemView);
        check = (CheckBox) itemView.findViewById(R.id.checkBox);
        text = (TextView) itemView.findViewById(R.id.text);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentSettingsController.checkBoxPressed(getPosition(), check.isChecked());
            }
        });

    }



    public void setItemData(String name)
    {
        text.setText(name);
    }
    public void setCheckState(boolean state)
    {
        check.setChecked(state);
    }



}

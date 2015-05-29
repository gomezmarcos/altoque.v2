package com.agea.altoque.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.agea.altoque.R;
import com.agea.altoque.model.Story;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by marcos on 26/05/15.
 */
public class ViewHolderOnlyDescription extends RecyclerView.ViewHolder{

    Context context;
    TextView description;
    TextView date;

    public ViewHolderOnlyDescription(View itemView) {
        super(itemView);

        context = itemView.getContext();
        description = (TextView)itemView.findViewById(R.id.description);
        date = (TextView)itemView.findViewById(R.id.date);

    }

    public void populate(Story story)
    {
        description.setText(Html.fromHtml(story.getDescription()));
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:MM");
        date.setText(story.getDate().toString(fmt) + " HS");
    }

}

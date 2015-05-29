package com.agea.altoque.holder;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannedString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agea.altoque.R;
import com.agea.altoque.model.Story;
import com.agea.altoque.utils.ImageSizeTransformer;
import com.squareup.picasso.Picasso;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by marcos on 26/05/15.
 */
public class ViewHolderWithImageAndDescription extends RecyclerView.ViewHolder{

    Context context;

    TextView description = null;
    ImageView image = null;
    TextView date = null;

    String IMAGE_URL = "http://api-editoriales.clarin.com/files/altoque/";




    public ViewHolderWithImageAndDescription(View itemView) {
        super(itemView);

        context = itemView.getContext();
        description = (TextView)itemView.findViewById(R.id.description);
        image = (ImageView)itemView.findViewById(R.id.image);
        date = (TextView)itemView.findViewById(R.id.date);

    }

    public void populate(Story story){

        //description.setText(Html.fromHtml(story.getDescription()));

        //description.setText(SpannableString.valueOf(story.getDescription()));
        SpannedString spannedString = new SpannedString(Html.fromHtml(story.getDescription()));
        description.setText(spannedString);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:MM");
        date.setText(story.getDate().toString(fmt) + " HS");

        String imageName = story.getImages().get(0);
        String url = imageName == null || imageName.isEmpty() ? "" : IMAGE_URL+ imageName;
        Picasso.with(context).load(url).into(image);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){

            int dip = ImageSizeTransformer.toPixels(200, context.getResources().getDisplayMetrics());

            RelativeLayout.LayoutParams imageViewParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dip);
            image.setLayoutParams(imageViewParams);
        }


        }





}

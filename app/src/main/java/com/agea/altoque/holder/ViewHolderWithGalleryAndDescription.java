package com.agea.altoque.holder;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import com.agea.altoque.utils.GalleryAdapter;
import com.agea.altoque.utils.ImageSizeTransformer;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by marcos on 26/05/15.
 */
public class ViewHolderWithGalleryAndDescription extends RecyclerView.ViewHolder{

    Context context;

    TextView description = null;
    ViewPager gallery = null;
    TextView date = null;
    GalleryAdapter adapter;
    CirclePageIndicator c;

    public ViewHolderWithGalleryAndDescription(View itemView, FragmentManager manager) {
        super(itemView);
        context = itemView.getContext();
        description = (TextView)itemView.findViewById(R.id.description);
        gallery = (ViewPager)itemView.findViewById(R.id.gallery);
        date = (TextView)itemView.findViewById(R.id.date);
        c = (CirclePageIndicator)itemView.findViewById(R.id.indicator);
        adapter = new GalleryAdapter(manager);
        gallery.setAdapter(adapter);
        c.setViewPager(gallery);

    }

    public void populate(Story story){

        //description.setText(Html.fromHtml(story.getDescription()));
        adapter.setImages(story.getImages());
        adapter.notifyDataSetChanged();
        c.notifyDataSetChanged();
        //description.setText(SpannableString.valueOf(story.getDescription()));
        SpannedString spannedString = new SpannedString(Html.fromHtml(story.getDescription()));
        description.setText(spannedString);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:MM");
        date.setText(story.getDate().toString(fmt) + " HS");




        }





}

package com.agea.altoque.utils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.agea.altoque.R;
import com.agea.altoque.holder.ViewHolderWithImageAndDescription;
import com.squareup.picasso.Picasso;

/**
 * Created by marcos on 27/05/15.
 */
public class ImageFragment extends Fragment{

    private String imageUrl;
    String IMAGE_URL = "http://api-editoriales.clarin.com/files/altoque/";

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = container.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.layout_fragment_image, container, false);
        ImageView image = (ImageView)v.findViewById(R.id.image);


        String url = imageUrl == null || imageUrl.isEmpty() ? "" : IMAGE_URL+ imageUrl;
        Log.e("Url en ImageFragment: ", url);
        Picasso.with(context).load(url).into(image);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){

            int dip = ImageSizeTransformer.toPixels(200, context.getResources().getDisplayMetrics());

            RelativeLayout.LayoutParams imageViewParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dip);
            image.setLayoutParams(imageViewParams);
        }

        return v;
    }
}

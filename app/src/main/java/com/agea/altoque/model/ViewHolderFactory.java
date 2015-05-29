package com.agea.altoque.model;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agea.altoque.R;
import com.agea.altoque.holder.ViewHolderOnlyDescription;
import com.agea.altoque.holder.ViewHolderWithGalleryAndDescription;
import com.agea.altoque.holder.ViewHolderWithImageAndDescription;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcos on 26/05/15.
 */
public class ViewHolderFactory {

    public static FragmentManager FRAGMENT_MANAGER = null;

    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder vh = null;
        View v = null;

        switch (InformationTypeEnum.getEnum(viewType)) {
            case STORY_GALLERY_AND_DESCRIPTION:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_full_story_with_gallery, parent, false);
                vh = new ViewHolderWithGalleryAndDescription(v,FRAGMENT_MANAGER); break;
            case STORY_IMAGE_AND_DESCRIPTION: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_full_story_with_image, parent, false);
                vh = new ViewHolderWithImageAndDescription(v); break;
            }
            case STORE_ONLY_TEXT:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_full_story, parent, false);
                vh = new ViewHolderOnlyDescription(v); break;
        }
        return vh;
    }

    public static void populate(RecyclerView.ViewHolder holder, Information information) {

        switch (InformationTypeEnum.getEnum(information.getViewType()))
        {
            case STORY_GALLERY_AND_DESCRIPTION:
            {
                ViewHolderWithGalleryAndDescription viewHolder = (ViewHolderWithGalleryAndDescription) holder;
                viewHolder.populate((Story) information);
                break;
            }
            case STORY_IMAGE_AND_DESCRIPTION:
            {
                ViewHolderWithImageAndDescription viewHolder =  (ViewHolderWithImageAndDescription) holder;
                viewHolder.populate((Story) information); break;
            }
            case STORE_ONLY_TEXT:
            {
                ViewHolderOnlyDescription viewHolder =  (ViewHolderOnlyDescription) holder;
                viewHolder.populate((Story) information); break;
            }
        }
    }
}
